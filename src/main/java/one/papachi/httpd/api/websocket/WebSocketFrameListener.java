package one.papachi.httpd.api.websocket;

public interface WebSocketFrameListener extends WebSocketListener {

    void onFrame(WebSocketFrame frame);

}
