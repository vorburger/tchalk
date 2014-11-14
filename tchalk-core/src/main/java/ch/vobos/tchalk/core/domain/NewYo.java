package ch.vobos.tchalk.core.domain;

import java.util.Optional;

import org.eclipse.jdt.annotation.NonNull;

import ch.vobos.tchalk.core.domain.simpletypes.Channel;
import ch.vobos.tchalk.core.domain.simpletypes.Token;
import ch.vobos.tchalk.core.domain.simpletypes.YoID;

public class NewYo {

	public final Token from;
	
	public final Channel channel;
	
	public final String text;
	
	public final Optional<@NonNull YoID> replyTo;

	@SuppressWarnings("null")
	public NewYo(Token from, Channel channel, String text) {
		this(from, channel, text, Optional.empty());
	}
	
	public NewYo(Token from, Channel channel, String text, Optional<@NonNull YoID> replyTo) {
		super();
		this.from = from;
		this.channel = channel;
		this.text = text;
		this.replyTo = replyTo;
	}
	
}
