package ch.vobos.tchalk.core.tests;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.CompletableFuture;

import org.junit.Test;

import ch.vobos.tchalk.core.domain.NewYo;
import ch.vobos.tchalk.core.domain.simpletypes.Channel;
import ch.vobos.tchalk.core.domain.simpletypes.Token;
import ch.vobos.tchalk.core.domain.simpletypes.YoID;
import ch.vobos.tchalk.core.services.TchalkServerServices;
import ch.vobos.tchalk.core.services.impl.TchalkServerServicesImpl;

public class TchalkServerServicesTest {

	TchalkServerServices services = new TchalkServerServicesImpl();
	
	@Test
	public void testTchalkServerServiceSay() throws Exception {
		Token fromToken = new Token("...");
		Channel testChannel = new Channel("TestChannel");
		NewYo newYo = new NewYo(fromToken, testChannel, "hello, world");
		
		CompletableFuture<YoID> yoId = new CompletableFuture<>();
		services.say(newYo, id -> { yoId.complete(id); });
		
		assertEquals("...", yoId.get().toString());
	}

}
