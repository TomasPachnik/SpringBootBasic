package sk.tomas.app.util;

/**
 * Created by Tomas Pachnik on 05-Jan-17.
 */
public enum ErrorMessages {

    MISSING_UUID(1, "Uuid should be set!"),
    MOREOVER_UUID(2, "Uuid should not be set, the repository sets it automatically!");

    private int code;
    private String message;

    ErrorMessages(int code, String test) {
        this.code = code;
        this.message = test;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

}
