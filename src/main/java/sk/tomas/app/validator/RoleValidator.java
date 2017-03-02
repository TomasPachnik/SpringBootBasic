package sk.tomas.app.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.tomas.app.exception.InputValidationException;
import sk.tomas.app.exception.OutputValidationException;
import sk.tomas.app.iam.model.input.RoleInput;
import sk.tomas.app.iam.model.output.RoleOutput;

import java.util.List;
import java.util.Objects;


/**
 * Created by Tomas Pachnik on 12-Jan-17.
 */
public class RoleValidator {

    private static Logger loger = LoggerFactory.getLogger(RoleValidator.class);

    public static void validateInput(RoleInput roleInput) throws InputValidationException {
        if (roleInput == null) {
            throw new InputValidationException("Role is null.");
        }
        if (roleInput.getName() == null || roleInput.getName().isEmpty()) {
            throw new InputValidationException("Role name is empty.");
        }
    }

    public static void validateOutput(RoleOutput roleOutput) throws OutputValidationException {
        if (roleOutput == null) {
            throw new OutputValidationException("Role is null.");
        }
        if (roleOutput.getUuid() == null) {
            throw new OutputValidationException("Role uuid is empty.");
        }
        if (roleOutput.getName() == null || roleOutput.getName().isEmpty()) {
            throw new OutputValidationException("Role name is empty.");
        }
    }

    public static void validateOutput(List<RoleOutput> list) throws OutputValidationException {
        for (RoleOutput item : list) {
            try {
                validateOutput(item);
            } catch (OutputValidationException e) {
                loger.warn(e.getMessage() + " - {}", item);
                throw e;
            }
        }
    }

    public static void validateInput(int firstResult, int maxResult, String orderBy) throws InputValidationException {
        if (firstResult < 0) {
            throw new InputValidationException("firstResult must not be negative.");
        }
        if (maxResult < 0) {
            throw new InputValidationException("maxResult must not be negative.");
        }
        if (orderBy != null && !orderBy.isEmpty() && !Objects.equals(orderBy, "uuid") && !Objects.equals(orderBy, "name") && !Objects.equals(orderBy, "surname") && !Objects.equals(orderBy, "login") && !Objects.equals(orderBy, "email") && !Objects.equals(orderBy, "age")) {
            throw new InputValidationException("orderBy must be one of following: 'null', '', 'uuid', 'name', 'description', 'level'.");
        }
    }

}
