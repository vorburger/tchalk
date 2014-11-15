package ch.vobos.tchalk.core.domain;

import ch.vobos.tchalk.core.domain.simpletypes.Channel;
import ch.vobos.tchalk.core.domain.simpletypes.Timestamp;
import ch.vobos.tchalk.core.domain.simpletypes.YoID;

public class Yo {

	public final YoID id;

	public final UserPublicProfile userPublicProfile;

	public final Channel channel;

	public final String text;

	public final Timestamp timestamp;

//	public final Optional<YoID> replyTo;

	public Yo(YoID id, UserPublicProfile userPublicProfile, Channel channel,
			String text, Timestamp timestamp /*, Optional<@NonNull YoID> replyTo */) {
		super();
		this.id = id;
		this.userPublicProfile = userPublicProfile;
		this.channel = channel;
		this.text = text;
		this.timestamp = timestamp;
//		this.replyTo = replyTo;
	}

}
