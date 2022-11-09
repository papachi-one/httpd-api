package one.papachi.httpd.api.http;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface HttpRequest {

    interface Builder {

        Builder setMethod(HttpMethod method);

        Builder setPath(String path);

        Builder setVersion(HttpVersion version);

        Builder addParameter(String name, String value);

        default Builder addParameter(String name, List<String> values) {
            values.forEach(value -> addParameter(name, value));
            return this;
        }

        default Builder addParameter(String name, String[] values) {
            addParameter(name, Arrays.asList(values));
            return this;
        }

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

        HttpRequest build();

    }

    HttpMethod getMethod();

    String getPath();

    HttpVersion getVersion();

    Map<String, List<String>> getParameters();

    String getParameterValue(String name);

    List<String> getParameterValues(String name);

    HttpHeaders getHttpHeaders();

    default List<HttpHeader> getHeaders() {
        return getHttpHeaders().getHeaders();
    }

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
