package one.papachi.httpd.api.spi;

import one.papachi.httpd.api.http.HttpHeader;
import one.papachi.httpd.api.http.HttpHeaders;
import one.papachi.httpd.api.websocket.WebSocketRequest;

public interface WebSocketDataProvider {

    WebSocketRequest.Builder getWebSocketRequestBuilder();

    HttpHeaders.Builder getHttpHeadersBuilder();

    HttpHeader.Builder getHttpHeaderBuilder();

}
