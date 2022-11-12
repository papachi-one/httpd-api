package one.papachi.httpd.api.http;

import java.util.Arrays;
import java.util.List;

public interface HttpHeaders {

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
