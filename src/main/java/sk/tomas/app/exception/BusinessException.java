package sk.tomas.app.exception;

/**
 * Created by Tomas Pachnik on 12-Jan-17.
 */
public class BusinessException extends Exception {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {

        super(message, cause);
    }
}
