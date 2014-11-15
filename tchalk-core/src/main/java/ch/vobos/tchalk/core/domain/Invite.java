package ch.vobos.tchalk.core.domain;

import org.eclipse.jdt.annotation.Nullable;

import ch.vobos.tchalk.core.domain.simpletypes.Token;
import ch.vobos.tchalk.core.domain.simpletypes.UserPublicID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Invite {

	public final Token from;
	
	public final UserPublicID userPublicID;
	
//	public final Optional<Channel> channel;

	@JsonCreator
	public Invite(@JsonProperty("from") Token from, @JsonProperty("userPublicID") UserPublicID userPublicID /*, Optional<@NonNull Channel> channel */) {
		super();
		this.from = from;
		this.userPublicID = userPublicID;
// TODO	this.channel = channel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + from.hashCode();
		result = prime * result + userPublicID.hashCode();
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
		Invite other = (Invite) obj;
		if (!from.equals(other.from))
			return false;
		else if (!userPublicID.equals(other.userPublicID))
			return false;
		return true;
	}
	
}
