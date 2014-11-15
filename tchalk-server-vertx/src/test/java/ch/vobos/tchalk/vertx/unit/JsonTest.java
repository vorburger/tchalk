package ch.vobos.tchalk.vertx.unit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.vertx.java.core.json.impl.Json;

import ch.vobos.tchalk.core.domain.NewYo;
import ch.vobos.tchalk.core.domain.simpletypes.Channel;
import ch.vobos.tchalk.core.domain.simpletypes.Token;

public class JsonTest {

    @Test
    public void testNewYoJSON() {
	NewYo newYo = new NewYo(new Token("secure"), new Channel("TestChannel"), "hello, World");
	String json = Json.encode(newYo);
	NewYo newYoAgain = Json.decodeValue(json, NewYo.class);
	assertEquals(newYo, newYoAgain);
    }

}
