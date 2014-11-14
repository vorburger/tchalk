package ch.vobos.tchalk.core.services;

import java.util.function.Consumer;

import ch.vobos.tchalk.core.domain.UserPublicProfile;
import ch.vobos.tchalk.core.domain.simpletypes.Token;

public interface UserServices {

	void getUserPublicProfile(Token user, Consumer<UserPublicProfile> callback);
	
}
