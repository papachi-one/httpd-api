package one.papachi.httpd.api.http;

import one.papachi.httpd.api.websocket.WebSocketRequest;
import one.papachi.httpd.api.websocket.WebSocketSession;

import java.util.concurrent.CompletableFuture;

public interface WebSocketClient {

    CompletableFuture<WebSocketSession> connect(WebSocketRequest request);

}
