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

public interface WebSocketStream extends WebSocketData {

    interface Builder {

        Builder input(AsynchronousByteChannel channel);

        Builder input(AsynchronousFileChannel channel);

        Builder input(ReadableByteChannel channel);

        Builder input(InputStream inputStream);

        default Builder input(Path path) {
            try {
                input(AsynchronousFileChannel.open(path, StandardOpenOption.READ));
            } catch (IOException e) {
            }
            return this;
        }

        default Builder input(File file) {
            input(file.toPath());
            return this;
        }

        default Builder input(String string) {
            input(new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8)));
            return this;
        }

        default Builder input(byte[] bytes) {
            input(new ByteArrayInputStream(bytes));
            return this;
        }

        WebSocketStream build();

    }

}
