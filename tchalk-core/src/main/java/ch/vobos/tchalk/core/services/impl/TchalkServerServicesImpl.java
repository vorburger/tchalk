package ch.vobos.tchalk.core.services.impl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import org.eclipse.jdt.annotation.NonNull;

import ch.vobos.tchalk.core.domain.Invite;
import ch.vobos.tchalk.core.domain.NewYo;
import ch.vobos.tchalk.core.domain.UserPublicProfile;
import ch.vobos.tchalk.core.domain.Yo;
import ch.vobos.tchalk.core.domain.simpletypes.Channel;
import ch.vobos.tchalk.core.domain.simpletypes.Timestamp;
import ch.vobos.tchalk.core.domain.simpletypes.YoID;
import ch.vobos.tchalk.core.services.EventBus;
import ch.vobos.tchalk.core.services.TchalkServerServices;
import ch.vobos.tchalk.core.services.UUIDService;
import ch.vobos.tchalk.core.services.UserServices;

public class TchalkServerServicesImpl implements TchalkServerServices {

	private final EventBus<Yo> yoBus;
	private final UserServices users = new UserServicesImpl();
	private final UUIDService uuid = new UUIDServiceImpl();
	
	public TchalkServerServicesImpl(EventBus<@NonNull Yo> yoBus) {
		super();
		this.yoBus = yoBus;
	}

	@Override
	public void say(NewYo inYo, Consumer<YoID> callback) throws InterruptedException, ExecutionException {
		// TODO validate yo.from token
		YoID id = new YoID(uuid.newUUID());
		
		// TODO must first verify here that inYo.from actually has access to this Channel!
		
		// TODO how to abstract this.. chain this more nicely..
		CompletableFuture<UserPublicProfile> userPublicProfileFuture = new CompletableFuture<>();
		users.getUserPublicProfile(inYo.from, profile -> { userPublicProfileFuture.complete(profile); });
		// TODO how to handle errors with Futures more transparently in general.. note throws InterruptedException, ExecutionException above - not great?
		UserPublicProfile userPublicProfile = userPublicProfileFuture.get();

		Timestamp ts = new Timestamp("TODO");
		Yo outYo = new Yo(id, userPublicProfile, inYo.channel, inYo.text, ts /*, inYo.replyTo */);
		yoBus.publish(outYo);
		
		callback.accept(id);
	}

	@Override
	public void invite(Invite invite, Consumer<Channel> callback) {
		// TODO validate yo.from token

		Channel channel;
		if (!invite.channel.isPresent()) {
			channel = new Channel(uuid.newUUID());
		} else {
			// TODO must first verify here that invite.from actually has access to this Channel!
			channel = invite.channel.get();
		}
		
		// TODO invite.userPublicID ... ask user if he wants to participate.. WTF??
		
		callback.accept(channel);
	}

}
