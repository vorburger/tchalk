package ch.vobos.tchalk.vertx;

import ch.vobos.tchalk.core.domain.Yo;
import ch.vobos.tchalk.core.domain.simpletypes.Channel;

import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;
import org.vertx.java.platform.Verticle;

import ch.vobos.tchalk.core.domain.NewYo;
import ch.vobos.tchalk.core.services.TchalkServerServices;
import ch.vobos.tchalk.core.services.impl.TchalkServerServicesImpl;

import java.util.List;

public class ServerVerticle extends Verticle {

    public static final String TCHALK_SERVER = "tchalk.server";

    public void start() {
        container.deployModule("io.vertx~mod-redis~1.1.4", new JsonObject().putString("address", "tchalk.redis"));
        container.deployModule("ch.vobos.tchalk~tchalk-persistence~0.0.1");

        TchalkServerServices tchalkServer = new TchalkServerServicesImpl(new VertxEventBus(vertx));

        vertx.eventBus().registerHandler(TCHALK_SERVER, (Message<JsonObject> m) -> {

            final String cmd = m.body().getString("command");
            if (cmd == null) {
            	container.logger().error("Missing 'command' field: " + m.body().toString());
            	return;
            }
            
			switch (cmd) {
                case "yo":
                    String arg = m.body().getObject("arg").encode();
                    // TODO distinguish say and invite messages
                    NewYo newYo = Json.decodeValue(arg, NewYo.class);

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
                    break;
                    
                case "history":
                    tchalkServer.history(new Channel(m.body().getObject("arg").getString("channel")),
                            (List<Yo> list) -> {
                                m.reply(new JsonArray(Json.encode(list)));
                            });
                    break;
                    
                default:
                	container.logger().error("Unknown 'command' field: " + m.body().toString());
            }

        });

        new WebServer(vertx);
    }
}
