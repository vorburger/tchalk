package ch.vorburger.vertxchat;

import java.io.IOException;

import javax.xml.stream.events.StartDocument;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.VertxFactory;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.platform.Verticle;

public class MyFirstVertx extends Verticle {

	@Override
	public void start() {
//		vertx.createNetServer().connectHandler(new Handler<NetSocket>() {
//		      public void handle(final NetSocket socket) {
//		        Pump.createPump(socket, socket).start();
//		      }
//		    }).listen(1234);
		
		HttpServer server = vertx.createHttpServer();
		JsonObject config = new JsonObject().putString("prefix", "/eventbus");
		JsonArray noPermitted = new JsonArray();
		noPermitted.add(new JsonObject());
		vertx.createSockJSServer(server).bridge(config, noPermitted, noPermitted);
		
		vertx.eventBus().registerHandler("hello", new Handler<Message<String>>() {
			@Override
			public void handle(Message<String> msg) {
				// msg.reply("echo " + msg.body());
				vertx.eventBus().publish("hello", "echo " + msg.body());
				System.out.println(msg.body());
			}
		});
		
		server.listen(8080);
	}

	public static void startEmbeddedVertx(Verticle verticle) throws IOException {
		Vertx vertx = VertxFactory.newVertx();
		verticle.setVertx(vertx);
		verticle.start();
		// Prevent the JVM from exiting
	    System.in.read();
	}
	
	public static void main(String[] args) throws Exception {
		startEmbeddedVertx(new MyFirstVertx());
	}
	
}
