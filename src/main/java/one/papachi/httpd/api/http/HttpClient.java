package one.papachi.httpd.api.http;

import one.papachi.httpd.api.spi.HttpClientProvider;

import java.net.URL;
import java.util.ServiceLoader;
import java.util.concurrent.CompletableFuture;

public interface HttpClient extends HttpOptions {

    static HttpClient getInstance() {
        return ServiceLoader.load(HttpClientProvider.class).findFirst().map(HttpClientProvider::getHttpClient).orElse(null);
    }

    CompletableFuture<HttpResponse> send(URL url, HttpRequest request);

    void close();

}
