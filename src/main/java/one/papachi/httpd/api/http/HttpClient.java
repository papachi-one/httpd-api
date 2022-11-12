package one.papachi.httpd.api.http;

import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public interface HttpClient extends HttpOptions {

    CompletableFuture<HttpResponse> send(URL url, HttpRequest request);

    ExecutorService getExecutorService();

}
