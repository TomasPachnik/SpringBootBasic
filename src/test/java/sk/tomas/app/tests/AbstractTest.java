package sk.tomas.app.tests;

import org.junit.Assert;
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

    private Controller getController() {
        return t.getController();
    }

}
