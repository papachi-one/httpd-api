package one.papachi.httpd.api.http;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousByteChannel;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.concurrent.Future;

public interface HttpBody extends AsynchronousByteChannel {

    interface Builder {

        Builder setEmpty();

        Builder setInput(AsynchronousByteChannel channel);

        Builder setInput(AsynchronousFileChannel channel);

        Builder setInput(ReadableByteChannel channel);

        Builder setInput(InputStream inputStream);

        default Builder setInput(Path path) {
            setInput(path.toFile());
            return this;
        }

        default Builder setInput(File file) {
            try {
                setInput(new FileInputStream(file));
            } catch (FileNotFoundException e) {
            }
            return this;
        }

        default Builder setInput(String string) {
            setInput(new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8)));
            return this;
        }

        default Builder setInput(byte[] bytes) {
            setInput(new ByteArrayInputStream(bytes));
            return this;
        }

        HttpBody build();

    }

    boolean isPresent();

    @Override
    default Future<Integer> write(ByteBuffer src) {
        throw new UnsupportedOperationException();
    }

    @Override
    default <A> void write(ByteBuffer src, A attachment, CompletionHandler<Integer, ? super A> handler) {
        throw new UnsupportedOperationException();
    }

    ReadableByteChannel getByteChannel();

    InputStream getInputStream();

}
