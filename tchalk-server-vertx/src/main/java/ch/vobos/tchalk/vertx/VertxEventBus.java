package ch.vobos.tchalk.vertx;

import ch.vobos.tchalk.core.domain.Yo;
import ch.vobos.tchalk.core.domain.simpletypes.Channel;
import ch.vobos.tchalk.core.services.EventBus;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class VertxEventBus implements EventBus<Yo> {

    public static final String CHANNEL_PREFIX = "tchalk.channel:";
    public static final String PERSISTENCE_SAVE = "tchalk.yo.sink";
    public static final String PERSISTENCE_SOURCE = "tchalk.yo.source";

    private final Vertx vertx;
    
    public VertxEventBus(Vertx vertx) {
	super();
	this.vertx = vertx;
    }

    @Override
    public void publish(Yo event) {
	// TODO There may be a more efficient way for this conversion?
	String jsonString = Json.encode(event);
	JsonObject jsonObject = new JsonObject(jsonString);

	vertx.eventBus().publish(CHANNEL_PREFIX + event.channel.toString(), jsonObject);

        vertx.eventBus().send(PERSISTENCE_SAVE, jsonObject);
    }

    @Override
    public void history(Channel channel, int limit, Consumer<List<Yo>> listConsumer) {
        vertx.eventBus().send(PERSISTENCE_SOURCE,
                new JsonObject().putString("channel", channel.toString()).putNumber("limit", limit),
                (Message<JsonObject> m) -> {
                    System.out.println(m.body());
                    List<Yo> list = Arrays.asList(Json.decodeValue(m.body().getArray("value").encode(), Yo[].class));
                    listConsumer.accept(list);
                });
    }
}
