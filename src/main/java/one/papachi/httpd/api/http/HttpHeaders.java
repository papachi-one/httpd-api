package one.papachi.httpd.api.http;

import one.papachi.httpd.api.spi.HttpClientProvider;
import one.papachi.httpd.api.spi.HttpDataProvider;

import java.util.Arrays;
import java.util.List;
import java.util.ServiceLoader;

public interface HttpHeaders {

    static HttpHeaders.Builder getBuilder() {
        return ServiceLoader.load(HttpDataProvider.class).findFirst().map(HttpDataProvider::getHttpHeadersBuilder).orElse(null);
    }

    interface Builder {

        Builder headerLine(String line);

        Builder header(HttpHeader header);

        Builder header(String name, String value);

        default Builder header(String name, List<String> values) {
            values.forEach(value -> header(name, value));
            return this;
        }

        default Builder header(String name, String[] values) {
            header(name, Arrays.asList(values));
            return this;
        }

        HttpHeaders build();

    }

    List<HttpHeader> getHeaders();

    HttpHeader getHeader(String name);

    List<HttpHeader> getHeaders(String name);

    String getHeaderValue(String name);

    List<String> getHeaderValues(String name);

}
