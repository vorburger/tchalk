package ch.vobos.tchalk.core.services;

public interface EventBus<T> {

    // TODO This @SuppressWarnings is an Eclipse bug (I think)
    void publish(@SuppressWarnings("null") T event);

}
