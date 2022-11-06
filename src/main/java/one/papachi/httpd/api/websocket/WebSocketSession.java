package one.papachi.httpd.api.websocket;

import java.nio.channels.CompletionHandler;
import java.util.concurrent.Future;

public interface WebSocketSession {

    WebSocketConnection getWebSocketConnection();

    WebSocketDataHandler getHandler();

    void setHandler(WebSocketDataHandler handler);

    boolean isClosed();

    Future<Integer> sendWebSocketMessage(WebSocketMessage message);

    <A> void sendWebSocketMessage(WebSocketMessage message, A attachment, CompletionHandler<Integer, ? super A> handler);

    Future<Void> sendWebSocketFrame(WebSocketFrame frame);

    <A> void sendWebSocketFrame(WebSocketFrame frame, A attachment, CompletionHandler<Void, ? super A> handler);

}
