package ch.vorburger.vertxchat;

import java.io.IOException;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.platform.Verticle;

public class MySecondSendingVerticle extends Verticle {

	
	@Override
	public void start() {
		vertx.setPeriodic(500, new Handler<Long>() {
			@Override
			public void handle(Long ms) {
				
				vertx.eventBus().send("hello", (String)"firstMessage", (Message<String> m) -> {
					System.out.println(m.body());
				});
				
			}
		});
		
	}
	
	public static void main(String[] args) throws IOException {
		MyFirstVertx.startEmbeddedVertx(new MySecondSendingVerticle());
	}
}
