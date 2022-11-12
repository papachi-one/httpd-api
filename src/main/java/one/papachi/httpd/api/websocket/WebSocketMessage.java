package one.papachi.httpd.api.websocket;

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

public interface WebSocketMessage extends WebSocketData {

    enum Type {
        TEXT, BINARY
    }

    interface Builder {

        default Builder text() {
            type(Type.TEXT);
            return this;
        }

        default Builder binary() {
            type(Type.BINARY);
            return this;
        }

        Builder type(Type type);

        Builder payload(AsynchronousByteChannel channel);

        Builder payload(AsynchronousFileChannel channel);

        Builder payload(ReadableByteChannel channel);

        Builder payload(InputStream inputStream);

        default Builder payload(Path path) {
            try {
                payload(AsynchronousFileChannel.open(path, StandardOpenOption.READ));
            } catch (IOException e) {
            }
            return this;
        }

        default Builder payload(File file) {
            payload(file.toPath());
            return this;
        }

        default Builder payload(String string) {
            payload(new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8)));
            return this;
        }

        default Builder payload(byte[] bytes) {
            payload(new ByteArrayInputStream(bytes));
            return this;
        }

        WebSocketMessage build();

    }

    Type getType();

}
