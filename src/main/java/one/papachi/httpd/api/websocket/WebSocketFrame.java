package one.papachi.httpd.api.websocket;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.AsynchronousByteChannel;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public interface WebSocketFrame extends WebSocketData {

    enum Type {
        CONTINUATION_FRAME, TEXT_FRAME, BINARY_FRAME, NON_CONTROL_FRAME_3, NON_CONTROL_FRAME_4, NON_CONTROL_FRAME_5, NON_CONTROL_FRAME_6, NON_CONTROL_FRAME_7, CONNECTION_CLOSE, PING_FRAME, PONG_FRAME, CONTROL_FRAME_B, CONTROL_FRAME_C, CONTROL_FRAME_D, CONTROL_FRAME_E, CONTROL_FRAME_F
    }

    interface Builder {

        default Builder fin() {
            fin(true);
            return this;
        }

        Builder fin(boolean value);

        default Builder rsv1() {
            rsv1(true);
            return this;
        }
        Builder rsv1(boolean value);

        default Builder rsv2() {
            rsv2(true);
            return this;
        }
        Builder rsv2(boolean value);

        default Builder rsv3() {
            rsv3(true);
            return this;
        }

        Builder rsv3(boolean value);

        Builder type(Type type);

        default Builder mask() {
            mask(true);
            return this;
        }
        Builder mask(boolean value);

        Builder length(long length);

        Builder mask(byte[] mask);

        Builder payload();

        Builder payload(AsynchronousByteChannel channel);

        Builder payload(AsynchronousFileChannel channel);

        Builder payload(ReadableByteChannel channel);

        Builder payload(InputStream inputStream);

        default Builder payload(Path path) {
            try {
                payload(AsynchronousFileChannel.open(path, StandardOpenOption.READ)).length(Files.size(path));
            } catch (IOException e) {
            }
            return this;
        }

        default Builder payload(File file) {
            payload(file.toPath());
            return this;
        }

        default Builder payload(String string) {
            byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
            payload(new ByteArrayInputStream(bytes)).length(bytes.length);
            return this;
        }

        default Builder payload(byte[] bytes) {
            payload(new ByteArrayInputStream(bytes)).length(bytes.length);
            return this;
        }

        WebSocketFrame build();

    }

    Type getType();

    boolean isFin();

    boolean isRsv1();

    boolean isRsv2();

    boolean isRsv3();

    boolean isMasked();

    long getLength();

    byte[] getMask();

}
