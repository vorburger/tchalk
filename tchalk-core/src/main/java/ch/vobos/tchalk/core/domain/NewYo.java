package ch.vobos.tchalk.core.domain;

import org.eclipse.jdt.annotation.Nullable;

import ch.vobos.tchalk.core.domain.simpletypes.Channel;
import ch.vobos.tchalk.core.domain.simpletypes.Token;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewYo {

	public final Token from;
	
	public final Channel channel;
	
	public final String text;
	
//	public final Optional<@NonNull YoID> replyTo;

//	@SuppressWarnings("null")
//	public NewYo(Token from, Channel channel, String text) {
//		this(from, channel, text, Optional.empty());
//	}
	
	@JsonCreator
	public NewYo(@JsonProperty("from") Token from, @JsonProperty("channel") Channel channel, @JsonProperty("text")  String text/*, Optional<@NonNull YoID> replyTo*/) {
		super();
		this.from = from;
		this.channel = channel;
		this.text = text;
// TODO	this.replyTo = replyTo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + channel.hashCode();
		result = prime * result + from.hashCode();
// TODO	result = prime * result + replyTo.hashCode();
		result = prime * result + text.hashCode();
		return result;
	}

	@Override
	public boolean equals(@Nullable Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewYo other = (NewYo) obj;
		if (!channel.equals(other.channel))
			return false;
		else if (!from.equals(other.from))
			return false;
// TODO
//		else if (!replyTo.equals(other.replyTo))
//			return false;
		else if (!text.equals(other.text))
			return false;
		return true;
	}
}
