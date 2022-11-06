package one.papachi.httpd.api.websocket;

public interface WebSocketStreamHandler extends WebSocketDataHandler {

    void onStream(WebSocketStream stream);

}
