package sk.tomas.app.tests;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import sk.tomas.app.controller.Controller;
import sk.tomas.app.exception.InputValidationException;
import sk.tomas.app.exception.OutputValidationException;
import sk.tomas.app.util.CustomStringManufacturer;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.UUID;

/**
 * Created by Tomas Pachnik on 01-Mar-17.
 */
public abstract class AbstractTest<T extends Controller, I, O> extends BaseTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private PodamFactory factory;
    private T t;
    private I i;

    public void setUp(T t, I i) {
        CustomStringManufacturer customStringManufacturer = new CustomStringManufacturer();
        factory = new PodamFactoryImpl();
        factory.getStrategy().addOrReplaceTypeManufacturer(String.class, customStringManufacturer);
        this.t = t;
        this.i = i;
    }

    @SuppressWarnings("unchecked")
    public void createTest() throws InputValidationException, OutputValidationException {
        I input = (I) factory.manufacturePojo(i.getClass());
        UUID uuid = getController().create(input);
        O output = (O) getController().getSingle(uuid);
        Assert.assertTrue(output != null);
    }

    @SuppressWarnings("unchecked")
    public void updateTest() throws InputValidationException, OutputValidationException {
        I input = (I) factory.manufacturePojo(i.getClass());
        UUID uuid = getController().create(input);
        O output = (O) getController().getSingle(uuid);
        I updatedInput = (I) factory.manufacturePojo(i.getClass());
        getController().update(uuid, updatedInput);
        O updatedOutput = (O) getController().getSingle(uuid);
        Assert.assertTrue(!output.equals(updatedOutput));
    }

    @SuppressWarnings("unchecked")
    public void deleteTest() throws InputValidationException, OutputValidationException {
        I input = (I) factory.manufacturePojo(i.getClass());
        UUID uuid = getController().create(input);
        O output = (O) getController().getSingle(uuid);
        Assert.assertTrue(output != null);
        getController().delete(uuid);
        thrown.expect(OutputValidationException.class);
        O secondOutput = (O) getController().getSingle(uuid);
    }

    @SuppressWarnings("unchecked")
    public Object listWithParamsTest() throws InputValidationException, OutputValidationException {
        I input = (I) factory.manufacturePojo(i.getClass());
        getController().create(input);
        return getController().listWithParam(0, 10, "uuid", false);
    }

    private Controller getController() {
        return t.getController();
    }

}
