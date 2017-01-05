package sk.tomas.app.util;

import sk.tomas.app.model.Identity;
import sk.tomas.app.model.Role;

import java.util.Random;
import java.util.UUID;

/**
 * Created by tomas on 05.01.2017.
 */
public class Utils {
    public static Identity createRandomIdentity() {
        return new Identity(UUID.randomUUID().toString(), UUID.randomUUID().toString(), new Random().nextInt(100));
    }

    public static Role createRandomRole() {
        return new Role(UUID.randomUUID().toString(), UUID.randomUUID().toString(), new Random().nextInt(100));
    }

}
