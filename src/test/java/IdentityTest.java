import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sk.tomas.app.model.Identity;
import sk.tomas.app.model.Role;
import sk.tomas.app.service.IdentityService;
import sk.tomas.app.service.RoleService;

import java.util.List;
import java.util.UUID;

import static sk.tomas.app.util.Utils.createRandomIdentity;
import static sk.tomas.app.util.Utils.createRandomRole;

/**
 * Created by Tomas Pachnik on 04-Jan-17.
 */
public class IdentityTest extends BaseTest {

    @Autowired
    IdentityService identityService;

    @Autowired
    RoleService roleService;

    @Test
    public void createIdentityTest() {
        //vytvorim identitu
        Identity identity = createRandomIdentity();
        UUID uuid = identityService.create(identity);
        Identity byUuid = identityService.findByUuid(uuid);
        Assert.assertTrue("Identita nevytvorena", identity.equals(byUuid));
    }

    @Test
    public void updateIdentityTest() {
        //vytvorim identitu
        Identity identity = createRandomIdentity();
        identityService.create(identity);
        Identity bySurname = identityService.findBySurname(identity.getSurname());
        Assert.assertTrue("Identita nevytvorena", identity.equals(bySurname));
        //updatujem identitu
        identity.setName(UUID.randomUUID().toString());
        identityService.update(identity);
        Identity bySurname2 = identityService.findBySurname(identity.getSurname());
        Assert.assertTrue("Najdena stara identita", !identity.equals(bySurname));
        Assert.assertTrue("Identita sa nezhoduje", identity.equals(bySurname2));
    }

    @Test
    public void deleteIdentityTest() {
        //vytvorim identitu
        Identity identity = createRandomIdentity();
        UUID uuid = identityService.create(identity);
        Identity bySurname = identityService.findBySurname(identity.getSurname());
        Assert.assertTrue("Identita nevytvorena", identity.equals(bySurname));
        Assert.assertTrue("Identita nevytvorena", identity.equals(bySurname));
        //zmazem identitu
        identityService.delete(identity.getUuid());
        Identity bySurname2 = identityService.findBySurname(identity.getSurname());
        Assert.assertTrue("Identita sa nevymazala", !identity.equals(bySurname2));
    }

    @Test
    public void listIdentityTest() {
        List<Identity> list = identityService.list();
        Assert.assertTrue("Identita nenajdena", (list.size() >= 1));
    }

    @Test
    public void IdentityRoleRelationshipTest() {
        Identity identity = createRandomIdentity();
        Role role = createRandomRole();
        identity.addRole(role);

        identityService.create(identity);
        Identity bySurname = identityService.findBySurname(identity.getSurname());
        Assert.assertTrue("Priradena rola nenajdena!", bySurname.getRoles().contains(role));
    }

}
