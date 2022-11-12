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

        default Builder http0() {
            version(HttpVersion.HTTP_1_0);
            return this;
        }

        default Builder http1() {
            version(HttpVersion.HTTP_1_1);
            return this;
        }

        default Builder http2() {
            version(HttpVersion.HTTP_2);
            return this;
        }

        Builder version(HttpVersion version);

        Builder statusCode(int statusCode);

        Builder reasonPhrase(String reasonPhrase);

        default Builder status(int statusCode, String reasonPhrase) {
            statusCode(statusCode).reasonPhrase(reasonPhrase);
            return this;
        }

        default Builder status(HttpStatus status) {
            statusCode(status.getStatusCode()).reasonPhrase(status.getReasonPhrase());
            return this;
        }

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

        default Builder setHeaders(HttpHeaders headers) {
            headers.getHeaders().forEach(this::header);
            return this;
        }

        Builder body(HttpBody body);

        Builder body(AsynchronousByteChannel channel);

        Builder body(AsynchronousFileChannel channel);

        Builder body(ReadableByteChannel channel);

        Builder body(InputStream inputStream);

        default Builder body(Path path) {
            try {
                body(AsynchronousFileChannel.open(path, StandardOpenOption.READ));
            } catch (IOException e) {
            }
            return this;
        }

        default Builder body(File file) {
            body(file.toPath());
            return this;
        }

        default Builder body(String string) {
            body(new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8)));
            return this;
        }

        default Builder body(byte[] bytes) {
            body(new ByteArrayInputStream(bytes));
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
