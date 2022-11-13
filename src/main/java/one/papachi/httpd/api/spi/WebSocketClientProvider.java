package one.papachi.httpd.api.spi;

import one.papachi.httpd.api.http.WebSocketClient;

public interface WebSocketClientProvider {

    WebSocketClient getWebSocketClient();

}
