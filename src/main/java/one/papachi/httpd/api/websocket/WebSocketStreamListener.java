package one.papachi.httpd.api.websocket;

public interface WebSocketStreamListener extends WebSocketListener {

    void onStream(WebSocketStream stream);

}
