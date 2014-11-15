package ch.vobos.tchalk.vertx;

import org.vertx.java.core.Vertx;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;

import ch.vobos.tchalk.core.domain.Yo;
import ch.vobos.tchalk.core.services.EventBus;

public class VertxEventBus implements EventBus<Yo> {

    public static final String CHANNEL_PREFIX = "tchalk.channel:";
    
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
    }

}