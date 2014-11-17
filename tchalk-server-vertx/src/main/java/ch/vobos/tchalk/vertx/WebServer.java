package ch.vobos.tchalk.vertx;


import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.io.ByteStreams;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.sockjs.EventBusBridgeHook;
import org.vertx.java.core.sockjs.SockJSSocket;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class WebServer {

    private static Map<String, String> mime = new HashMap<>();
    {
        mime.put("js", "application/javascript");
        mime.put("html", "text/html");
    }

    public WebServer(Vertx vertx) {
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
                        req.response().putHeader("Location",
                                req.uri().toString().replaceAll("/$", "") + "/index.html");
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

        // TODO review noPermitted to disallow clients posting broadcast Yo to bus themselves
        vertx.createSockJSServer(server).bridge(config, noPermitted, noPermitted).setHook(new EventBusBridgeHook() {
            @Override
            public boolean handleSocketCreated(SockJSSocket sockJSSocket) {
                return true;
            }

            @Override
            public void handleSocketClosed(SockJSSocket sockJSSocket) {

            }

            @Override
            public boolean handleSendOrPub(SockJSSocket sockJSSocket, boolean b, JsonObject jsonObject, String s) {
                return true;
            }

            @Override
            public boolean handlePreRegister(SockJSSocket sockJSSocket, String s) {
                return true;
            }

            @Override
            public void handlePostRegister(SockJSSocket sockJSSocket, String address) {
                return;
            }

            @Override
            public boolean handleUnregister(SockJSSocket sockJSSocket, String s) {
                return true;
            }

            @Override
            public boolean handleAuthorise(JsonObject jsonObject, String s, Handler<AsyncResult<Boolean>> asyncResultHandler) {
                return true;
            }
        });

        server.listen(8080);
    }
}
