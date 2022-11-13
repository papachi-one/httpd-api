package one.papachi.httpd.api.http;

import one.papachi.httpd.api.spi.HttpDataProvider;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.channels.AsynchronousByteChannel;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

public interface HttpRequest {

    static HttpRequest.Builder getBuilder() {
        return ServiceLoader.load(HttpDataProvider.class).findFirst().map(HttpDataProvider::getHttpRequestBuilder).orElse(null);
    }

    interface Builder {

        default Builder server(String host, int port) {
            server(new InetSocketAddress(host, port));
            return this;
        }

        Builder server(InetSocketAddress server);

        default Builder url(URL url) {
            server(url.getHost(), url.getPort() != -1 ? url.getPort() : url.getDefaultPort());
            scheme(url.getProtocol());
            path(url.getPath());
            header("Host", url.getHost() + (url.getPort() != -1 ? ":" + url.getPort() : ":"));
            return this;
        }

        Builder scheme(String scheme);

        default Builder GET(URL url) {
            url(url);
            method(HttpMethod.GET);
            return this;
        }

        default Builder HEAD(URL url) {
            url(url);
            method(HttpMethod.HEAD);
            return this;
        }

        default Builder POST(URL url) {
            url(url);
            method(HttpMethod.POST);
            return this;
        }

        default Builder PUT(URL url) {
            url(url);
            method(HttpMethod.PUT);
            return this;
        }

        default Builder DELETE(URL url) {
            url(url);
            method(HttpMethod.DELETE);
            return this;
        }

        default Builder CONNECT(URL url) {
            url(url);
            method(HttpMethod.CONNECT);
            return this;
        }

        default Builder OPTIONS(URL url) {
            url(url);
            method(HttpMethod.OPTIONS);
            return this;
        }

        default Builder TRACE(URL url) {
            url(url);
            method(HttpMethod.TRACE);
            return this;
        }

        default Builder PATCH(URL url) {
            url(url);
            method(HttpMethod.PATCH);
            return this;
        }

        default Builder GET() {
            method(HttpMethod.GET);
            return this;
        }

        default Builder HEAD() {
            method(HttpMethod.HEAD);
            return this;
        }

        default Builder POST() {
            method(HttpMethod.POST);
            return this;
        }

        default Builder PUT() {
            method(HttpMethod.PUT);
            return this;
        }

        default Builder DELETE() {
            method(HttpMethod.DELETE);
            return this;
        }

        default Builder CONNECT() {
            method(HttpMethod.CONNECT);
            return this;
        }

        default Builder OPTIONS() {
            method(HttpMethod.OPTIONS);
            return this;
        }

        default Builder TRACE() {
            method(HttpMethod.TRACE);
            return this;
        }

        default Builder PATCH() {
            method(HttpMethod.PATCH);
            return this;
        }

        Builder method(HttpMethod method);

        Builder path(String path);

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

        default Builder httpAny() {
            version(HttpVersion.ANY);
            return this;
        }

        Builder version(HttpVersion version);

        Builder parameter(String name, String value);

        default Builder parameter(String name, List<String> values) {
            values.forEach(value -> parameter(name, value));
            return this;
        }

        default Builder parameter(String name, String[] values) {
            parameter(name, Arrays.asList(values));
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

        default Builder headers(HttpHeaders headers) {
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

        HttpRequest build();

    }

    InetSocketAddress getServer();

    String getScheme();

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
