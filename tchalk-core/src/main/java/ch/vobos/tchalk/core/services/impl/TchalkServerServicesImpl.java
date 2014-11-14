package ch.vobos.tchalk.core.services.impl;

import java.util.function.Consumer;

import ch.vobos.tchalk.core.domain.NewYo;
import ch.vobos.tchalk.core.domain.simpletypes.YoID;
import ch.vobos.tchalk.core.services.TchalkServerServices;

public class TchalkServerServicesImpl implements TchalkServerServices {

	@Override
	public void say(NewYo yo, Consumer<YoID> callback) {
		YoID id = new YoID("...");
		callback.accept(id);
	}

}
