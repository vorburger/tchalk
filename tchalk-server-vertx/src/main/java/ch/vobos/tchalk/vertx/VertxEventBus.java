package ch.vobos.tchalk.vertx;

import org.vertx.java.core.Vertx;

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
	vertx.eventBus().publish(CHANNEL_PREFIX + event.channel.toString(), event);
    }

}