package ch.vobos.tchalk.core.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import ch.vobos.tchalk.core.domain.simpletypes.Channel;
import org.eclipse.jdt.annotation.NonNull;

import ch.vobos.tchalk.core.services.EventBus;

public class TestEventBus<@NonNull EventT> implements EventBus<@NonNull EventT> {

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

    @Override
    public void history(Channel channel, int limit, Consumer<List<EventT>> target) {

    }

    public void addObserver(Observer<@NonNull EventT> observer) {
	observers.add(observer);
    }


}
