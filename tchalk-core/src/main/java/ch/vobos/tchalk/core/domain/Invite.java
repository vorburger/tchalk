package ch.vobos.tchalk.core.domain;

import java.util.Optional;

import org.eclipse.jdt.annotation.NonNull;

import ch.vobos.tchalk.core.domain.simpletypes.Channel;
import ch.vobos.tchalk.core.domain.simpletypes.Token;
import ch.vobos.tchalk.core.domain.simpletypes.UserPublicID;

public class Invite {

	public final Token from;
	
	public final UserPublicID userPublicID;
	
	public final Optional<Channel> channel;

	public Invite(Token from, UserPublicID userPublicID, Optional<@NonNull Channel> channel) {
		super();
		this.from = from;
		this.userPublicID = userPublicID;
		this.channel = channel;
	}
	
}
