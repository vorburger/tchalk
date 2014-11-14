package ch.vobos.tchalk.core.services;

public interface EventBus<T> {

	void publish(T event);
	
}
