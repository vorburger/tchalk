package ch.vobos.tchalk.core.domain;


public class UserPublicProfile {

	/**
	 * Typically end-user “real” name, obtained from OAuth provider. If not
	 * available, then by default just the same as the UserPublicID value (e.g.
	 * phone number).
	 */
	public final String humanName;

	// TODO PictureID & Picture getPicture(PictureID) in TchalkServerServices

	public UserPublicProfile(String humanName) {
		super();
		this.humanName = humanName;
	}	

}
