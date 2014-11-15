package ch.vobos.tchalk.vertx;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.io.ByteStreams;
import org.vertx.java.core.Handler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.platform.Verticle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class WebServerVerticle extends Verticle {

    private static Map<String, String> mime = new HashMap<>();
    {
        mime.put("js", "application/javascript");
        mime.put("html", "text/html");
    }

    @Override
    public void start() {
        HttpServer server = vertx.createHttpServer();
        JsonObject config = new JsonObject().putString("prefix", "/eventbus");
        JsonArray noPermitted = new JsonArray();
        noPermitted.add(new JsonObject());
        server.requestHandler(new Handler<HttpServerRequest>() {
            @Override
            public void handle(HttpServerRequest req) {
                if (req.method().equals("GET")) {
                    if (req.path().equals("/") || req.path().equals("")) {
                        req.response().setStatusCode(302);
                        req.response().putHeader("Location", req.absoluteURI().toString().replaceAll("/$", "") + "/index.html");
                        req.response().end();
                        return;
                    }
                    InputStream is;
                    File f = new File("../src/main/resources/web/"+req.path());
                    if(f.exists()) {
                        try {
                            System.out.println("Development mode, loading "+f+" from sources");
                            is = new FileInputStream(f);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        is = this.getClass().getClassLoader().getResourceAsStream("web/" + req.path());
                    }
                    if (is != null) {
                        try {
                            Buffer b = new Buffer(ByteStreams.toByteArray(is));
                            is.close();
                            String last = Iterables.getLast(Splitter.on('.').splitToList(req.path()));
                            req.response().putHeader("Content-Type", mime.get(last));
                            req.response().setStatusCode(200);
                            req.response().end(b);
                        } catch (IOException e) {
                            req.response().setStatusCode(500);
                            req.response().end("Wow, ya broke da server, man");
                        }
                    }
                } else {
                    req.response().setStatusCode(405);
                    req.response().end("Unsupported Method");
                }
            }
        });
        vertx.createSockJSServer(server).bridge(config, noPermitted, noPermitted);
        server.listen(8080);

        vertx.eventBus().registerHandler("tchalk.server", (Message<JsonObject> m) -> {
            System.out.println(m.body().toString());
        });
    }
}
