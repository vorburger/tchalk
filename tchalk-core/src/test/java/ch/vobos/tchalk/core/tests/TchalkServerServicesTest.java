package ch.vobos.tchalk.core.tests;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.CompletableFuture;

import org.junit.Test;

import ch.vobos.tchalk.core.domain.NewYo;
import ch.vobos.tchalk.core.domain.Yo;
import ch.vobos.tchalk.core.domain.simpletypes.Channel;
import ch.vobos.tchalk.core.domain.simpletypes.Token;
import ch.vobos.tchalk.core.domain.simpletypes.YoID;
import ch.vobos.tchalk.core.services.TchalkClientServices;
import ch.vobos.tchalk.core.services.TchalkServerServices;
import ch.vobos.tchalk.core.services.impl.TchalkServerServicesImpl;

/**
 * Test.
 * 
 * @see ServerIntegrationTest for a similar test (with Vert.x)
 */
public class TchalkServerServicesTest {

	TestEventBus<Yo> testEventBus = new TestEventBus<>();
	TchalkServerServices serverServices = new TchalkServerServicesImpl(testEventBus);
	TchalkClientServices clientServices = new TchalkClientServicesTestImpl(testEventBus);
	
	@Test
	public void testTchalkServerServiceSay() throws Exception {
		CompletableFuture<Yo> receiver1 = new CompletableFuture<>();
		clientServices.registerReceiver(yo -> { receiver1.complete(yo); });

		CompletableFuture<Yo> receiver2 = new CompletableFuture<>();
		clientServices.registerReceiver(yo -> { receiver2.complete(yo); });

		Token fromToken = new Token("...");
		Channel testChannel = new Channel("TestChannel");
		NewYo newYo = new NewYo(fromToken, testChannel, "hello, world");
		
		CompletableFuture<YoID> yoId = new CompletableFuture<>();
		serverServices.say(newYo, id -> { yoId.complete(id); });
		
		assertEquals("hello, world", receiver1.get().text);
		assertEquals("hello, world", receiver2.get().text);
		// TODO assertEquals more stuff.. like yoId.get().toString());
	}

}
