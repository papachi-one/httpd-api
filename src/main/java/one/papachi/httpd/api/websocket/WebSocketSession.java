package one.papachi.httpd.api.websocket;

import one.papachi.httpd.api.http.HttpRequest;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousByteChannel;
import java.util.concurrent.CompletableFuture;

public interface WebSocketSession {

    HttpRequest getRequest();

    WebSocketListener getListener();

    void setListener(WebSocketListener listener);

    void sendClose();

    CompletableFuture<WebSocketSession> send(ByteBuffer src);

    CompletableFuture<WebSocketSession> send(AsynchronousByteChannel src);

    CompletableFuture<WebSocketSession> send(WebSocketFrame src);

}
