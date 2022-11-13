package one.papachi.httpd.api.websocket;

import one.papachi.httpd.api.http.HttpRequest;
import one.papachi.httpd.api.http.HttpResponse;

import java.util.concurrent.CompletableFuture;

public interface WebSocketSession {

    HttpRequest getRequest();

    HttpResponse getResponse();

    WebSocketListener getListener();

    WebSocketSession setListener(WebSocketListener listener);

    CompletableFuture<WebSocketSession> sendClose();

    CompletableFuture<WebSocketSession> send(WebSocketFrame src);

    CompletableFuture<WebSocketSession> send(WebSocketMessage src);

    CompletableFuture<WebSocketSession> send(WebSocketStream src);

}
