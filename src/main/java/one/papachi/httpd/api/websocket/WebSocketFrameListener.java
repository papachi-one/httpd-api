package one.papachi.httpd.api.websocket;

import java.util.concurrent.CompletableFuture;

public interface WebSocketFrameListener extends WebSocketListener {

    CompletableFuture<Void> onFrame(WebSocketFrame frame);

}
