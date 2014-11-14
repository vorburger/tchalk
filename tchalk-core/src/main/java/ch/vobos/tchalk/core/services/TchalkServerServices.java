package ch.vobos.tchalk.core.services;

import java.util.function.Consumer;

import ch.vobos.tchalk.core.domain.NewYo;
import ch.vobos.tchalk.core.domain.simpletypes.YoID;

public interface TchalkServerServices {

	void say(NewYo yo, Consumer<YoID> callback);
	
}
