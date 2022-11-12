package one.papachi.httpd.api.spi;

import one.papachi.httpd.api.http.HttpBody;
import one.papachi.httpd.api.http.HttpHeader;
import one.papachi.httpd.api.http.HttpHeaders;
import one.papachi.httpd.api.http.HttpRequest;
import one.papachi.httpd.api.http.HttpResponse;

public interface HttpDataProvider {

    HttpRequest.Builder getHttpRequestBuilder();

    HttpResponse.Builder getHttpResponseBuilder();

    HttpHeaders.Builder getHttpHeadersBuilder();

    HttpHeader.Builder getHttpHeaderBuilder();

    HttpBody.Builder getHttpBodyBuilder();

}
