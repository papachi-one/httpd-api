package one.papachi.httpd.api.http;

import one.papachi.httpd.api.spi.HttpClientProvider;

import java.util.ServiceLoader;
import java.util.concurrent.CompletableFuture;

/**
 * HTTP Client class instance used to send HTTP requests to remote server(s) specified by URL. Supports HTTP versions 1.0, 1.1, 2.
 */
public interface HttpClient extends HttpOptions {

    /**
     * @return HttpClient specific implementation provided by SPI implementation.
     */
    static HttpClient getInstance() {
        return ServiceLoader.load(HttpClientProvider.class).findFirst().map(HttpClientProvider::getHttpClient).orElse(null);
    }

    /**
     * Sends an HTTP Request to a remote server specified in URL. Method return CompletableFuture holding HTTP Response from server.
     * @param request
     * @return CompletableFuture holding future result (HTTP Response) to an HTTP Request send to a server specified in URL
     */
    CompletableFuture<HttpResponse> send(HttpRequest request);

    /**
     * Closes all idle connections made to remote server(s).
     * After calling this method, it is still possible to send new HTTP Requests via this instance.
     */
    void close();

}
