package one.papachi.httpd.api.http;

import java.util.Arrays;
import java.util.List;

public interface HttpPart {

    interface Builder {

        Builder setName(String name);

        Builder addHeaderLine(String line);

        Builder addHeader(HttpHeader header);

        Builder addHeader(String name, String value);

        default Builder addHeader(String name, List<String> values) {
            values.forEach(value -> addHeader(name, value));
            return this;
        }

        default Builder addHeader(String name, String[] values) {
            addHeader(name, Arrays.asList(values));
            return this;
        }

        default Builder setHeaders(HttpHeaders headers) {
            headers.getHeaders().forEach(this::addHeader);
            return this;
        }

        Builder setBody(HttpBody body);

        HttpPart build();

    }

    HttpHeaders getHttpHeaders();

    default HttpHeader getHeader(String name) {
        return getHttpHeaders().getHeader(name);
    }

    default List<HttpHeader> getHeaders(String name) {
        return getHttpHeaders().getHeaders(name);
    }

    default String getHeaderValue(String name) {
        return getHttpHeaders().getHeaderValue(name);
    }

    default List<String> getHeaderValues(String name) {
        return getHttpHeaders().getHeaderValues(name);
    }

    HttpBody getHttpBody();

}
