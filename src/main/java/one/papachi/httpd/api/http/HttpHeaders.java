package one.papachi.httpd.api.http;

import java.util.Arrays;
import java.util.List;

public interface HttpHeaders {

    interface Builder {

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

        HttpHeaders build();

    }

    List<HttpHeader> getHeaders();

    HttpHeader getHeader(String name);

    List<HttpHeader> getHeaders(String name);

    String getHeaderValue(String name);

    List<String> getHeaderValues(String name);

}
