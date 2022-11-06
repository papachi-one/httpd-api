package one.papachi.httpd.api.http;

import java.util.concurrent.CompletableFuture;

public interface HttpClient {

    CompletableFuture<HttpResponse> send(HttpRequest request);

}
