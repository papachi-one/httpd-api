package one.papachi.httpd.api.http;

import java.nio.channels.AsynchronousSocketChannel;

public interface HttpConnection {

    HttpServer getHttpServer();

    AsynchronousSocketChannel getSocketChannel();

}
