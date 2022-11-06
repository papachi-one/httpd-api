package one.papachi.httpd.api.websocket;

public interface WebSocketFrameHandler extends WebSocketDataHandler {

    void onFrame(WebSocketFrame frame);

}
