package one.papachi.httpd.api.http;


import one.papachi.httpd.api.spi.HttpServerProvider;
import one.papachi.httpd.api.websocket.WebSocketHandler;

import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.concurrent.ExecutorService;

public interface HttpServer {

    static HttpServer getInstance() {
        return ServiceLoader.load(HttpServerProvider.class).findFirst().map(HttpServerProvider::getHttpServerInstance).orElse(null);
    }

    ExecutorService getExecutorService();

    AsynchronousServerSocketChannel getServerSocketChannel();

    HttpHandler getHttpHandler();

    void setHttpHandler(HttpHandler handler);

    WebSocketHandler getWebSocketHandler();

    void setWebSocketHandler(WebSocketHandler handler);

    Set<HttpOption<?>> supportedOptions();

    <T> T getOption(HttpOption<T> name);

    <T> HttpServer setOption(HttpOption<T> name, T value);

    void start();

    void stop();

}