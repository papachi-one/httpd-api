package one.papachi.httpd.api.http;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.AsynchronousByteChannel;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public interface HttpResponse {

    interface Builder {

        Builder setVersion(HttpVersion version);

        Builder setStatusCode(int statusCode);

        Builder setReasonPhrase(String reasonPhrase);

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

        Builder setBody(AsynchronousByteChannel channel);

        Builder setBody(AsynchronousFileChannel channel);

        Builder setBody(ReadableByteChannel channel);

        Builder setBody(InputStream inputStream);

        default Builder setBody(Path path) {
            try {
                setBody(AsynchronousFileChannel.open(path, StandardOpenOption.READ));
            } catch (IOException e) {
            }
            return this;
        }

        default Builder setBody(File file) {
            setBody(file.toPath());
            return this;
        }

        default Builder setBody(String string) {
            setBody(new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8)));
            return this;
        }

        default Builder setBody(byte[] bytes) {
            setBody(new ByteArrayInputStream(bytes));
            return this;
        }

        HttpResponse build();

    }

    HttpVersion getVersion();

    int getStatusCode();

    String getReasonPhrase();

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
