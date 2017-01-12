package sk.tomas.app.validator;

import org.apache.commons.validator.routines.EmailValidator;
import sk.tomas.app.exception.InputValidationException;
import sk.tomas.app.exception.OutputValidationException;
import sk.tomas.app.model.Input.IdentityInput;
import sk.tomas.app.model.output.IdentityOutput;

/**
 * Created by Tomas Pachnik on 12-Jan-17.
 */
public class IdentityValidator {

    public static void validateInput(IdentityInput identityInput) throws InputValidationException {
        if (identityInput == null) {
            throw new InputValidationException("Identity is null.");
        }
        if (identityInput.getName() == null || identityInput.getName().isEmpty()) {
            throw new InputValidationException("Identity name is empty.");
        }
        if (identityInput.getSurname() == null || identityInput.getSurname().isEmpty()) {
            throw new InputValidationException("Identity surname is empty.");
        }
        if (identityInput.getLogin() == null || identityInput.getLogin().isEmpty()) {
            throw new InputValidationException("Identity login is empty.");
        }
        if (identityInput.getEmail() == null || identityInput.getEmail().isEmpty()) {
            throw new InputValidationException("Identity email is empty.");
        }
        if (!EmailValidator.getInstance().isValid(identityInput.getEmail())) {
            throw new InputValidationException("Identity email is not valid.");
        }
    }

    public static void validateOutput(IdentityOutput identityOutput) throws OutputValidationException {
        if (identityOutput == null) {
            throw new OutputValidationException("Identity is null.");
        }
        if (identityOutput.getUuid() == null) {
            throw new OutputValidationException("Identity uuid is null.");
        }
        if (identityOutput.getName() == null || identityOutput.getName().isEmpty()) {
            throw new OutputValidationException("Identity name is empty.");
        }
        if (identityOutput.getSurname() == null || identityOutput.getSurname().isEmpty()) {
            throw new OutputValidationException("Identity surname is empty.");
        }
        if (identityOutput.getLogin() == null || identityOutput.getLogin().isEmpty()) {
            throw new OutputValidationException("Identity login is empty.");
        }
        if (identityOutput.getEmail() == null || identityOutput.getEmail().isEmpty()) {
            throw new OutputValidationException("Identity email is empty.");
        }
        if (!EmailValidator.getInstance().isValid(identityOutput.getEmail())) {
            throw new OutputValidationException("Identity email is not valid.");
        }
    }

}
