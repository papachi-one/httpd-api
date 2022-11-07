package one.papachi.httpd.api.http;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public interface HttpClient {

    CompletableFuture<HttpResponse> send(String host, int port, boolean https, HttpRequest request);

    ExecutorService getExecutorService();

    Set<HttpOption<?>> supportedOptions();

    <T> T getOption(HttpOption<T> name);

    <T> HttpClient setOption(HttpOption<T> name, T value);

}
