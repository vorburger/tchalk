package ch.vobos.tchalk.core.tests;

import java.util.ArrayList;
import java.util.List;

import ch.vobos.tchalk.core.services.EventBus;

public class TestEventBus<EventT> implements EventBus<EventT> {

	public interface Observer<EventT> {
		void notify(EventT event);
	}
	
	private final List<Observer<EventT>> observers = new ArrayList<>();
	
	@Override
	public void publish(EventT event) {
		for (Observer<EventT> observer : observers) {
			observer.notify(event);
		}
	}

	public void addObserver(Observer<EventT> observer) {
		observers.add(observer);
	}
	
}
