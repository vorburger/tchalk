package ch.vobos.tchalk.core.services;

import ch.vobos.tchalk.core.domain.simpletypes.Channel;
import ch.vobos.tchalk.core.domain.simpletypes.Timestamp;

import java.util.List;
import java.util.function.Consumer;

public interface EventBus<T> {
    // TODO This @SuppressWarnings is an Eclipse bug (I think)
    void publish(@SuppressWarnings("null") T event);

    void history(Channel channel, int limit, Consumer<List<T>> target);
}
