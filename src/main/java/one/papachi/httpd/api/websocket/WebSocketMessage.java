package one.papachi.httpd.api.websocket;

public interface WebSocketMessage extends WebSocketData {

    enum Type {
        TEXT, BINARY
    }

    Type getType();

}
