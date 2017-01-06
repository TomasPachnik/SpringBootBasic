package sk.tomas.app.exception;

import java.util.UUID;

/**
 * Created by tomas on 06.01.2017.
 */
public class ServerMessage {
    private UUID code;
    private String text;

    public ServerMessage(UUID code, String text) {
        this.code = code;
        this.text = text;
    }

    public UUID getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
