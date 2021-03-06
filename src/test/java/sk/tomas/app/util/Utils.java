package sk.tomas.app.util;

import sk.tomas.app.iam.model.input.IdentityInput;
import sk.tomas.app.iam.model.input.RoleInput;
import sk.tomas.app.model.Identity;
import sk.tomas.app.model.Password;
import sk.tomas.app.model.Role;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.Random;
import java.util.UUID;

/**
 * Created by tomas on 05.01.2017.
 */
public class Utils {

    private static PodamFactory factory;

    static {
        factory = new PodamFactoryImpl();
    }

    public static Identity createRandomIdentity() {
        Identity identity = new Identity(UUID.randomUUID().toString(), UUID.randomUUID().toString(), new Random().nextInt(100));
        identity.setEmail("sadas@sadsa.sk");
        identity.setLogin("randomLogin");
        identity.setPassword(new Password("randomPassowrd"));
        return identity;
    }

    public static Role createRandomRole() {
        return new Role(UUID.randomUUID().toString(), UUID.randomUUID().toString(), new Random().nextInt(100));
    }

    public static IdentityInput randomIdentity() {
        IdentityInput identityInput = factory.manufacturePojo(IdentityInput.class);
        identityInput.setEmail("asdsa@asd.sk");
        return identityInput;
    }

    public static RoleInput randomRole() {
        return factory.manufacturePojo(RoleInput.class);
    }

}
