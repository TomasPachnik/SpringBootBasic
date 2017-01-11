package sk.tomas.app.exception;

/**
 * Created by tomas on 06.01.2017.
 */
public class ServerMessage {
    private String code;
    private String message;

    public ServerMessage(String code, String text) {
        this.code = code;
        this.message = text;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
