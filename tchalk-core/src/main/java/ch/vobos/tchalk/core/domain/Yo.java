package ch.vobos.tchalk.core.domain;

import org.eclipse.jdt.annotation.Nullable;

import ch.vobos.tchalk.core.domain.simpletypes.Channel;
import ch.vobos.tchalk.core.domain.simpletypes.Timestamp;
import ch.vobos.tchalk.core.domain.simpletypes.YoID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Yo {

	public final YoID id;

	public final UserPublicProfile userPublicProfile;

	public final Channel channel;

	public final String text;

	public final Timestamp timestamp;

//	public final Optional<YoID> replyTo;

	@JsonCreator
	public Yo(
			@JsonProperty("id") YoID id,
			@JsonProperty("userPublicProfile") UserPublicProfile userPublicProfile,
			@JsonProperty("channel") Channel channel,
			@JsonProperty("text") String text,
			@JsonProperty("timestamp") Timestamp timestamp
			// , Optional<@NonNull YoID> replyTo
			) {
		super();
		this.id = id;
		this.userPublicProfile = userPublicProfile;
		this.channel = channel;
		this.text = text;
		this.timestamp = timestamp;
//		this.replyTo = replyTo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + channel.hashCode();
		result = prime * result + id.hashCode();
		result = prime * result + text.hashCode();
		result = prime * result + timestamp.hashCode();
		result = prime * result + userPublicProfile.hashCode();
		// TODO replyTo ..
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
		Yo other = (Yo) obj;
		if (!channel.equals(other.channel))
			return false;
		if (!id.equals(other.id))
			return false;
		if (!text.equals(other.text))
			return false;
		if (!timestamp.equals(other.timestamp))
			return false;
		if (!userPublicProfile.equals(other.userPublicProfile))
			return false;
		// TODO replyTo ..
		return true;
	}

}
