package ch.vobos.tchalk.vertx.integration.java;

import static org.junit.Assert.assertEquals;
import static org.vertx.testtools.VertxAssert.assertNotNull;
import static org.vertx.testtools.VertxAssert.assertTrue;
import static org.vertx.testtools.VertxAssert.testComplete;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.json.impl.Json;
import org.vertx.testtools.TestVerticle;

import ch.vobos.tchalk.core.domain.NewYo;
import ch.vobos.tchalk.core.domain.Yo;
import ch.vobos.tchalk.core.domain.simpletypes.Channel;
import ch.vobos.tchalk.core.domain.simpletypes.Token;
import ch.vobos.tchalk.core.domain.simpletypes.YoID;
import ch.vobos.tchalk.vertx.ServerVerticle;
import ch.vobos.tchalk.vertx.VertxEventBus;

/**
 * Integration Test.
 * 
 * NOTE: This test doesn't work well in-IDE, because it relies on the presence of a built
 * target/mods/ch.vobos.tchalk~tchalk-server-vertx~1.0-SNAPSHOT.  Therefore, it is best to
 * run this as 'mvn clean verify'.
 * 
 * @see ch.vobos.tchalk.core.tests.TchalkServerServicesTest for a similiar test (non-Vert.X)
 */
public class ServerIntegrationTest extends TestVerticle {

    @Test
    public void testServer() throws Exception {
	Channel testChannel = new Channel("TestChannel");

	CompletableFuture<Yo> receiver1 = new CompletableFuture<>();
	vertx.eventBus().registerHandler(VertxEventBus.CHANNEL_PREFIX + testChannel.toString(),
		(Message<JsonObject> m) -> { receiver1.complete(Json.decodeValue(m.body().toString(), Yo.class)); } );
	
	// TODO receiver2
	
	Token fromToken = new Token("...");
	NewYo newYo = new NewYo(fromToken, testChannel, "hello, world");

	CompletableFuture<YoID> yoIdFuture = new CompletableFuture<>();
	vertx.eventBus().send(ServerVerticle.TCHALK_SERVER, new JsonObject(Json.encode(newYo)),
		(Message<JsonObject> m) -> { yoIdFuture.complete(new YoID(m.body().getString("value"))); } );

// TODO no response??
	yoIdFuture.get(3, TimeUnit.SECONDS);

	assertEquals("hello, world", receiver1.get(3, TimeUnit.SECONDS).text);
	// TODO assertEquals("hello, world", receiver2.get(3, TimeUnit.SECONDS).text);
	// TODO assertEquals more stuff.. like yoId.get(3, TimeUnit.SECONDS).toString());
	
	/*
	 * If we get here, the test is complete. You must always call `testComplete()` at the end. Remember that testing is
	 * *asynchronous* so we cannot assume the test is complete by the time the test method has finished executing like in
	 * standard synchronous tests
	 */
	testComplete();
    }

    @Override
    public void start() {
	// Make sure we call initialize() - this sets up the assert stuff so
	// assert functionality works correctly
	initialize();
	// Deploy the module - the System property `vertx.modulename` will
	// contain the name of the module so you
	// don't have to hard coded it in your tests
	String moduleName = System.getProperty("vertx.modulename");
	System.out.println(moduleName);
	container.deployModule(moduleName, new AsyncResultHandler<String>() {
	    @Override
	    public void handle(AsyncResult<String> asyncResult) {
		// Deployment is asynchronous and this this handler will
		// be called when it's complete (or failed)
		String msg = asyncResult.cause() != null ? asyncResult.cause().toString() : "Not succeeded, but no cause?!";
		assertTrue(msg, asyncResult.succeeded());
		assertNotNull("deploymentID should not be null", asyncResult.result());
		// If deployed correctly then start the tests!
		startTests();
	    }
	});
    }

}
