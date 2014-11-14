package ch.vobos.tchalk.core.services;

import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import ch.vobos.tchalk.core.domain.Invite;
import ch.vobos.tchalk.core.domain.NewYo;
import ch.vobos.tchalk.core.domain.simpletypes.Channel;
import ch.vobos.tchalk.core.domain.simpletypes.YoID;

public interface TchalkServerServices {

	void say(NewYo yo, Consumer<YoID> callback) throws InterruptedException, ExecutionException;
	
	void invite(Invite invite, Consumer<Channel> callback);
	
}
