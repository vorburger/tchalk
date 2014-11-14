package ch.vobos.tchalk.core.services;

import java.util.function.Consumer;

import ch.vobos.tchalk.core.domain.Yo;

public interface TchalkClientServices {

	void registerReceiver(Consumer<Yo> callback);
	
}
