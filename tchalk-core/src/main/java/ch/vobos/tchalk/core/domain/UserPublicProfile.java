package ch.vobos.tchalk.core.domain;

import org.eclipse.jdt.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class UserPublicProfile {

	/**
	 * Typically end-user “real” name, obtained from OAuth provider. If not
	 * available, then by default just the same as the UserPublicID value (e.g.
	 * phone number).
	 */
	public final String humanName;

	// TODO PictureID & Picture getPicture(PictureID) in TchalkServerServices

	@JsonCreator
	public UserPublicProfile(@JsonProperty("humanName") String humanName) {
		super();
		this.humanName = humanName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + humanName.hashCode();
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
		UserPublicProfile other = (UserPublicProfile) obj;
		if (!humanName.equals(other.humanName))
			return false;
		return true;
	}	

}
