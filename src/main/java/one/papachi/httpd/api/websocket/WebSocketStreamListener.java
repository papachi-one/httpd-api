package one.papachi.httpd.api.websocket;

import java.util.concurrent.CompletableFuture;

public interface WebSocketStreamListener extends WebSocketListener {

    CompletableFuture<Void> onStream(WebSocketStream stream);

}
