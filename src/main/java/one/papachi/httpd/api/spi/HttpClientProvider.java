package one.papachi.httpd.api.spi;

import one.papachi.httpd.api.http.HttpClient;

public interface HttpClientProvider {

    HttpClient getHttpClient();

}
