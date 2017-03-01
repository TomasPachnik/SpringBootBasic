package sk.tomas.app.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.tomas.app.exception.InputValidationException;
import sk.tomas.app.exception.OutputValidationException;
import sk.tomas.app.iam.model.input.RoleInput;
import sk.tomas.app.iam.model.output.RoleOutput;

import java.util.List;


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


}
