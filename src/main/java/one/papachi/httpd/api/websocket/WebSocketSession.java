package one.papachi.httpd.api.websocket;

import one.papachi.httpd.api.http.HttpRequest;

import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;

public interface WebSocketSession {

    HttpRequest getRequest();

    WebSocketListener getListener();

    WebSocketSession setListener(WebSocketListener listener);

    CompletableFuture<WebSocketSession> sendClose();

    CompletableFuture<WebSocketSession> send(ByteBuffer src);

    CompletableFuture<WebSocketSession> send(WebSocketMessage src);

    CompletableFuture<WebSocketSession> send(WebSocketFrame src);

}
