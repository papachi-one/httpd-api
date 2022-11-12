package one.papachi.httpd.api.websocket;

public interface WebSocketMessageListener extends WebSocketListener {

    void onMessage(WebSocketMessage message);

}
