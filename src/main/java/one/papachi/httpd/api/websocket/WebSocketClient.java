package one.papachi.httpd.api.websocket;

import one.papachi.httpd.api.http.HttpOptions;
import one.papachi.httpd.api.http.HttpRequest;

import java.util.concurrent.CompletableFuture;

public interface WebSocketClient extends HttpOptions {

    CompletableFuture<WebSocketSession> send(HttpRequest request);

}
