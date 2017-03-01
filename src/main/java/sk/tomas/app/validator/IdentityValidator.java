package sk.tomas.app.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.tomas.app.exception.InputValidationException;
import sk.tomas.app.exception.OutputValidationException;
import sk.tomas.app.iam.model.input.IdentityInput;
import sk.tomas.app.iam.model.output.IdentityOutput;
import sk.tomas.app.service.impl.IdentityServiceImpl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by Tomas Pachnik on 12-Jan-17.
 */
public class IdentityValidator {

    private static Logger loger = LoggerFactory.getLogger(IdentityValidator.class);

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

    public static void validateOutput(List<IdentityOutput> list) throws OutputValidationException {
        for (IdentityOutput item : list) {
            try {
                validateOutput(item);
            } catch (OutputValidationException e) {
                loger.warn(e.getMessage() + " - {}", item);
                throw e;
            }
        }
    }

    public static void validateInput(IdentityInput identityInput, UUID uuid) throws InputValidationException {
        validateInput(identityInput);
    }

    public static void validateInput(int firstResult, int maxResult, String orderBy) throws InputValidationException {
        if (firstResult < 0) {
            throw new InputValidationException("firstResult must not be negative.");
        }
        if (maxResult < 0) {
            throw new InputValidationException("maxResult must not be negative.");
        }
        if (orderBy != null && !orderBy.isEmpty() && !Objects.equals(orderBy, "uuid") && !Objects.equals(orderBy, "name") && !Objects.equals(orderBy, "surname") && !Objects.equals(orderBy, "login") && !Objects.equals(orderBy, "email") && !Objects.equals(orderBy, "age")) {
            throw new InputValidationException("orderBy must be one of following: 'null', '', 'uuid', 'name', 'surname', 'login', 'email', 'age'.");
        }
    }
}