import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sk.tomas.app.model.Identity;
import sk.tomas.app.model.Role;
import sk.tomas.app.service.IdentityService;
import sk.tomas.app.service.RoleService;

import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Tomas Pachnik on 05-Jan-17.
 */
public class RoleTest extends BaseTest {

    @Autowired
    RoleService roleService;

    @Test
    public void createRoleTest() {
        //vytvorim identitu
        Role role = new Role(UUID.randomUUID().toString(), UUID.randomUUID().toString(), new Random().nextInt(100));
        UUID uuid = roleService.create(role);
        Role byName = roleService.findByName(role.getName());
        Assert.assertTrue("Identita nevytvorena", role.equals(byName));
    }

}
