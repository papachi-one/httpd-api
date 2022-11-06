package one.papachi.httpd.api.websocket;


import one.papachi.httpd.api.http.HttpConnection;

public interface WebSocketConnection {

    HttpConnection getHttpConnection();

    WebSocketSession getWebSocketSession();

}
