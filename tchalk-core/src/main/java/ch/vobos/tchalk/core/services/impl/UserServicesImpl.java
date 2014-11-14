package ch.vobos.tchalk.core.services.impl;

import java.util.function.Consumer;

import org.eclipse.jdt.annotation.NonNull;

import ch.vobos.tchalk.core.domain.UserPublicProfile;
import ch.vobos.tchalk.core.domain.simpletypes.Token;
import ch.vobos.tchalk.core.services.UserServices;

public class UserServicesImpl implements UserServices {

	@Override
	public void getUserPublicProfile(Token user, Consumer<@NonNull UserPublicProfile> callback) {
		// TODO This is just short-term, stupid - we'll want to look this up somehow instead of course:
		UserPublicProfile profile = new UserPublicProfile(user.toString());
		callback.accept(profile);

	}

}
