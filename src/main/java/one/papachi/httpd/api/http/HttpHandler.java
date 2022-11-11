package one.papachi.httpd.api.http;

import java.util.concurrent.CompletableFuture;

public interface HttpHandler {

    CompletableFuture<HttpResponse> handle(HttpRequest request);

}
