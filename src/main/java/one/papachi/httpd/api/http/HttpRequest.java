package one.papachi.httpd.api.http;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface HttpRequest {

    interface Builder {

        default Builder setRequestLine(String requestLine) {
            String[] split = requestLine.split("\\s", 3);
            setMethod(split[0].trim());
            setPath(split[1].trim());
            setVersion(split[2].trim());
            return this;
        }

        Builder setMethod(String method);

        Builder setPath(String path);

        Builder setVersion(String version);

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

    default String getRequestLine() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getMethod());
        stringBuilder.append(" ");
        stringBuilder.append(getPath());
        if (!getParameters().isEmpty())
            stringBuilder.append('?');
        for (Map.Entry<String, List<String>> entry : getParameters().entrySet()) {
            String name = entry.getKey();
            List<String> values = entry.getValue();
            int counter = 0;
            for (String value : values) {
                if (counter++ > 0) {
                    stringBuilder.append('&');
                }
                name = URLEncoder.encode(name, StandardCharsets.UTF_8);
                value = URLEncoder.encode(value, StandardCharsets.UTF_8);
                stringBuilder.append(name);
                stringBuilder.append('=');
                stringBuilder.append(value);
            }
        }
        stringBuilder.append(" ");
        stringBuilder.append(getVersion());
        return stringBuilder.toString();
    }

    String getMethod();

    String getPath();

    String getVersion();

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
