package ch.vobos.tchalk.core.services;

import ch.vobos.tchalk.core.domain.simpletypes.Channel;

import java.util.List;
import java.util.function.Consumer;

import org.eclipse.jdt.annotation.NonNull;

public interface EventBus<@NonNull T> {

    void publish(T event);

    void history(Channel channel, int limit, Consumer<List<T>> target);
}
