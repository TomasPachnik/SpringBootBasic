package sk.tomas.app.exception;

/**
 * Created by Tomas Pachnik on 12-Jan-17.
 */
public class BusinessException extends Exception {

    BusinessException(String message) {
        super(message);
    }
}
