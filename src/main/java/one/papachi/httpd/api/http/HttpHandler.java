package one.papachi.httpd.api.http;

import java.util.concurrent.CompletionStage;

public interface HttpHandler {

    CompletionStage<HttpResponse> handle(HttpRequest request);

}
