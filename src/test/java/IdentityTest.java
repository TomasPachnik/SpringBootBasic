import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sk.tomas.app.model.Identity;
import sk.tomas.app.service.IdentityService;

import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Tomas Pachnik on 04-Jan-17.
 */
public class IdentityTest extends BaseTest {

    @Autowired
    IdentityService identityService;

    @Test
    public void createIdentityTest() {
        //vytvorim identitu
        Identity identity = createIdentity();
        UUID uuid = identityService.create(identity);
        Identity bySurname = identityService.findBySurname(identity.getSurname());
        Assert.assertTrue("Identita nevytvorena", identity.equals(bySurname));
        List<Identity> list = identityService.list();
    }

    @Test
    public void updateIdentityTest() {
        //vytvorim identitu
        Identity identity = createIdentity();
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
        Identity identity = createIdentity();
        identityService.create(identity);
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

    private Identity createIdentity(){
        return new Identity(UUID.randomUUID().toString(), UUID.randomUUID().toString(), new Random().nextInt(100));
    }

}
