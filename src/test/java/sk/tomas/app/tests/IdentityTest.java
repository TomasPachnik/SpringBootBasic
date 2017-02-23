package sk.tomas.app.tests;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import sk.tomas.app.controller.IdentityController;
import sk.tomas.app.exception.InputValidationException;
import sk.tomas.app.exception.OutputValidationException;
import sk.tomas.app.model.Identity;
import sk.tomas.app.model.Input.IdentityInput;
import sk.tomas.app.model.Role;
import sk.tomas.app.model.output.IdentityOutput;
import sk.tomas.app.service.IdentityService;
import sk.tomas.app.service.RoleService;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.List;
import java.util.UUID;

import static sk.tomas.app.util.Utils.createRandomIdentity;
import static sk.tomas.app.util.Utils.createRandomRole;
import static sk.tomas.app.util.Utils.randomIdentity;

/**
 * Created by Tomas Pachnik on 04-Jan-17.
 */
public class IdentityTest extends BaseTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private IdentityController identityController;

    @Test
    @WithMockUser(authorities = {"admin"})
    public void createIdentityTest() throws OutputValidationException, InputValidationException {
        //vytvorim identitu
        IdentityInput identityInput = randomIdentity();
        UUID uuid = identityController.create(identityInput);
        IdentityOutput single = identityController.getSingle(uuid);
        identityController.listIdentityWithParam(0, 0, "name", false);
        Assert.assertTrue("Identita nevytvorena", identityInput.getLogin().equals(single.getLogin()));
    }

    @Test
    public void createIdentityInputTest() throws InputValidationException {
        //vytvorim identitu
        IdentityInput identityInput = new IdentityInput("meno", "priezvisko", "login", "email@email.sk", 30);
        UUID uuid = identityService.create(identityInput);
        Identity byUuid = identityService.findByUuid(uuid);
        Assert.assertTrue("Identita nevytvorena", byUuid != null);
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
    public void deleteIdentityTest() throws InputValidationException {
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
        Assert.assertTrue("Identita nenajdena", (list.size() == 2));
    }

    @Test
    public void IdentityRoleRelationshipPositiveTest() {
        Identity identity = createRandomIdentity();
        Role role = createRandomRole();
        UUID uuid = roleService.create(role);
        Role byUuid = roleService.findByUuid(uuid);
        identity.addRole(byUuid);

        identityService.create(identity);
        Identity bySurname = identityService.findBySurname(identity.getSurname());
        Assert.assertTrue("Priradena rola nenajdena!", bySurname.getRoles().contains(byUuid));
    }

    @Test
    public void IdentityRoleRelationshipNegativeTest() {
        Identity identity = createRandomIdentity();
        Role role = createRandomRole();
        identity.addRole(role);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Related object is not created before!");
        identityService.create(identity);
    }

    @Test
    public void identityCountTest() {
        long count = identityService.count();
        Assert.assertTrue("Identity nenajdene", (count == 2));
    }

    @Test
    public void identityPaginationTest() {
        List<Identity> list1 = identityService.list(0, 10, "login", false);
        Assert.assertTrue("posledny login", "sysadmin".equals(list1.get(1).getLogin()));
        Assert.assertTrue("Identity nenajdene", (list1.size() == 2));
        List<Identity> list2 = identityService.list(1, 10, "login", false);
        Assert.assertTrue("Identity nenajdene", (list2.size() == 1));
    }

}
