package sk.tomas.app.validator;

import sk.tomas.app.exception.InputValidationException;
import sk.tomas.app.exception.OutputValidationException;
import sk.tomas.app.model.Input.RoleInput;
import sk.tomas.app.model.output.RoleOutput;

import java.util.List;

/**
 * Created by Tomas Pachnik on 12-Jan-17.
 */
public class RoleValidator {

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
            validateOutput(item);
        }
    }


}
