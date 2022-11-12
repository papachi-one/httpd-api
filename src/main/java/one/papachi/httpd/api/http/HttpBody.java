package one.papachi.httpd.api.http;


import one.papachi.httpd.api.spi.HttpClientProvider;
import one.papachi.httpd.api.spi.HttpDataProvider;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousByteChannel;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ServiceLoader;
import java.util.concurrent.Future;

public interface HttpBody extends AsynchronousByteChannel {

    static HttpBody.Builder getBuilder() {
        return ServiceLoader.load(HttpDataProvider.class).findFirst().map(HttpDataProvider::getHttpBodyBuilder).orElse(null);
    }

    interface Builder {

        Builder empty();

        Builder length(long length);

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

}
