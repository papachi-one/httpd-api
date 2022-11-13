package one.papachi.httpd.api.websocket;

import one.papachi.httpd.api.http.HttpBody;
import one.papachi.httpd.api.http.HttpHeader;
import one.papachi.httpd.api.http.HttpHeaders;
import one.papachi.httpd.api.http.HttpMethod;
import one.papachi.httpd.api.http.HttpRequest;
import one.papachi.httpd.api.http.HttpVersion;

import java.io.File;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.channels.AsynchronousByteChannel;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public interface WebSocketRequest extends HttpRequest {

    interface Builder extends HttpRequest.Builder {

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
            throw new UnsupportedOperationException();
        }

        default Builder POST(URL url) {
            throw new UnsupportedOperationException();
        }

        default Builder PUT(URL url) {
            throw new UnsupportedOperationException();
        }

        default Builder DELETE(URL url) {
            throw new UnsupportedOperationException();
        }

        default Builder CONNECT(URL url) {
            throw new UnsupportedOperationException();
        }

        default Builder OPTIONS(URL url) {
            throw new UnsupportedOperationException();
        }

        default Builder TRACE(URL url) {
            throw new UnsupportedOperationException();
        }

        default Builder PATCH(URL url) {
            throw new UnsupportedOperationException();
        }

        default Builder GET() {
            method(HttpMethod.GET);
            return this;
        }

        default Builder HEAD() {
            throw new UnsupportedOperationException();
        }

        default Builder POST() {
            throw new UnsupportedOperationException();
        }

        default Builder PUT() {
            throw new UnsupportedOperationException();
        }

        default Builder DELETE() {
            throw new UnsupportedOperationException();
        }

        default Builder CONNECT() {
            throw new UnsupportedOperationException();
        }

        default Builder OPTIONS() {
            throw new UnsupportedOperationException();
        }

        default Builder TRACE() {
            throw new UnsupportedOperationException();
        }

        default Builder PATCH() {
            throw new UnsupportedOperationException();
        }

        Builder method(HttpMethod method);

        Builder path(String path);

        default Builder http0() {
            throw new UnsupportedOperationException();
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

        default Builder body(HttpBody body) {
            throw new UnsupportedOperationException();
        }

        default Builder body(AsynchronousByteChannel channel) {
            throw new UnsupportedOperationException();
        }

        default Builder body(AsynchronousFileChannel channel) {
            throw new UnsupportedOperationException();
        }

        default Builder body(ReadableByteChannel channel) {
            throw new UnsupportedOperationException();
        }

        default Builder body(InputStream inputStream) {
            throw new UnsupportedOperationException();
        }

        default Builder body(Path path) {
            throw new UnsupportedOperationException();
        }

        default Builder body(File file) {
            throw new UnsupportedOperationException();
        }

        default Builder body(String string) {
            throw new UnsupportedOperationException();
        }

        default Builder body(byte[] bytes) {
            throw new UnsupportedOperationException();
        }

        WebSocketRequest build();

    }

}
