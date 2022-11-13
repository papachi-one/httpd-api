package one.papachi.httpd.api.websocket;

import java.util.concurrent.CompletableFuture;

public interface WebSocketMessageListener extends WebSocketListener {

    CompletableFuture<Void> onMessage(WebSocketMessage message);

}
