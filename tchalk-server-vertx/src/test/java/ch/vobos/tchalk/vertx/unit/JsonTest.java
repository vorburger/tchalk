package ch.vobos.tchalk.vertx.unit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import ch.vobos.tchalk.core.domain.UserPublicProfile;
import ch.vobos.tchalk.core.domain.Yo;
import ch.vobos.tchalk.core.domain.simpletypes.Timestamp;
import ch.vobos.tchalk.core.domain.simpletypes.YoID;
import org.junit.Test;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
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

    @Test
    public void testArray() {
        Yo[] yos = new Yo[] { new Yo(new YoID("123"), new UserPublicProfile("me"),
                new Channel("TestChannel"), "hello, World", new Timestamp("now")) };

        String json = Json.encode(yos);

        JsonObject jsonObj = new JsonObject().putArray("value", new JsonArray(json));
        Yo[] yos2 = Json.decodeValue(jsonObj.getArray("value").encode(), Yo[].class);
        assertArrayEquals(yos, yos2);
    }
}
