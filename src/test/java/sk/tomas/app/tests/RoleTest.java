package sk.tomas.app.tests;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import sk.tomas.app.controller.RoleController;
import sk.tomas.app.exception.InputValidationException;
import sk.tomas.app.exception.OutputValidationException;
import sk.tomas.app.iam.model.input.RoleInput;

import javax.annotation.PostConstruct;

/**
 * Created by Tomas Pachnik on 02-Mar-17.
 */
public class RoleTest extends AbstractTest {

    @Autowired
    private RoleController roleController;

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void setUp() {
        setUp(roleController, new RoleInput());
    }

    @Test
    @WithMockUser(authorities = {"admin"})
    public void createTest() throws OutputValidationException, InputValidationException {
        super.createTest();
    }

}
