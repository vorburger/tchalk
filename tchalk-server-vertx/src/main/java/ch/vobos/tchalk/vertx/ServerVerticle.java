package ch.vobos.tchalk.vertx;

import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;
import org.vertx.java.platform.Verticle;

import ch.vobos.tchalk.core.domain.NewYo;
import ch.vobos.tchalk.core.services.TchalkServerServices;
import ch.vobos.tchalk.core.services.impl.TchalkServerServicesImpl;

public class ServerVerticle extends Verticle {

    public static final String TCHALK_SERVER = "tchalk.server";

    public void start() {
	TchalkServerServices tchalkServer = new TchalkServerServicesImpl(new VertxEventBus(vertx));

	vertx.eventBus().registerHandler(TCHALK_SERVER, (Message<JsonObject> m) -> {
	    String json = m.body().encode();
	    // TODO distinguish say and invite messages
	    NewYo newYo = Json.decodeValue(json, NewYo.class);
	    
	    try {
		tchalkServer.say(newYo, yoID -> {
		    // TODO this isn't great.. 'value' should be automatic, toString() shouldn't be needed..
		    JsonObject jsonReply = new JsonObject().putString("value", yoID.toString());
		    m.reply(jsonReply);
		});
	    } catch (Exception e) {
		final String errMsg = "say() failed: " + e.getMessage();
		container.logger().error(errMsg, e);
		m.fail(-123, errMsg);
	    }
	    
	});
	
        new WebServer(vertx);
    }
}
