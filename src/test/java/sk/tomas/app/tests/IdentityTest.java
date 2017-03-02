package sk.tomas.app.tests;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import sk.tomas.app.controller.IdentityController;
import sk.tomas.app.exception.InputValidationException;
import sk.tomas.app.exception.OutputValidationException;
import sk.tomas.app.iam.model.input.IdentityInput;

import javax.annotation.PostConstruct;

/**
 * Created by Tomas Pachnik on 02-Mar-17.
 */
public class IdentityTest extends AbstractTest {

    @Autowired
    IdentityController identityController;

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void setUp() {
        setUp(identityController, new IdentityInput());
    }

    @Test
    @WithMockUser(authorities = {"admin"})
    public void createTest() throws OutputValidationException, InputValidationException {
        super.createTest();
    }


}
