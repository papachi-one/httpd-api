package one.papachi.httpd.api.websocket;

public interface WebSocketMessageHandler extends WebSocketDataHandler {

    void onMessage(WebSocketMessage message);

}
