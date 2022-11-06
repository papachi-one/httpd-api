package one.papachi.httpd.api.websocket;

public interface WebSocketFrame extends WebSocketData {

    enum Type {
        CONTINUATION_FRAME, TEXT_FRAME, BINARY_FRAME, NON_CONTROL_FRAME_3, NON_CONTROL_FRAME_4, NON_CONTROL_FRAME_5, NON_CONTROL_FRAME_6, NON_CONTROL_FRAME_7, CONNECTION_CLOSE, PING_FRAME, PONG_FRAME, CONTROL_FRAME_B, CONTROL_FRAME_C, CONTROL_FRAME_D, CONTROL_FRAME_E, CONTROL_FRAME_F
    }

    boolean isFin();

    Type getType();

    boolean isMasked();

    long getLength();

    byte[] getMask();

}
