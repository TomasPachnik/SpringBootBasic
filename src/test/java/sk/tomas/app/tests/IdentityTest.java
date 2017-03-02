package sk.tomas.app.tests;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import sk.tomas.app.controller.IdentityController;
import sk.tomas.app.controller.RoleController;
import sk.tomas.app.exception.InputValidationException;
import sk.tomas.app.exception.OutputValidationException;
import sk.tomas.app.iam.model.input.IdentityInput;
import sk.tomas.app.iam.model.input.RoleInput;
import sk.tomas.app.iam.model.output.IdentityOutput;
import sk.tomas.app.iam.model.output.IdentityPaginationWithCount;
import sk.tomas.app.model.Identity;
import sk.tomas.app.model.Role;
import sk.tomas.app.service.IdentityService;
import sk.tomas.app.service.RoleService;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

import static sk.tomas.app.util.Utils.*;
import static sk.tomas.app.util.Utils.randomIdentity;
import static sk.tomas.app.util.Utils.randomRole;

/**
 * Created by Tomas Pachnik on 02-Mar-17.
 */
public class IdentityTest extends AbstractTest {

    @Autowired
    IdentityController identityController;
    @Autowired
    RoleController roleController;
    @Autowired
    IdentityService identityService;
    @Autowired
    RoleService roleService;

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

    @Test
    @WithMockUser(authorities = {"admin"})
    public void updateTest() throws OutputValidationException, InputValidationException {
        super.updateTest();
    }

    @Test
    @WithMockUser(authorities = {"admin"})
    public void deleteTest() throws OutputValidationException, InputValidationException {
        super.deleteTest();
    }

    @Test
    public void listIdentityTest() throws OutputValidationException {
        List<IdentityOutput> list = identityController.list();
        Assert.assertTrue("Identita nenajdena", (list.size() == 2));
    }

    @Test
    @WithMockUser(authorities = {"admin"})
    public void identityListWithParamsTest() throws OutputValidationException, InputValidationException {
        IdentityPaginationWithCount withCount = (IdentityPaginationWithCount) super.listWithParamsTest();
        Assert.assertTrue(withCount.getCount() == 3);
        Assert.assertTrue(withCount.getIdentityOutputs().size() == 3);
    }

    //*******STARE TESTY*********

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


    @Test
    @WithMockUser(authorities = {"admin"})
    public void addRoleTest() throws InputValidationException {
        IdentityInput identityInput = randomIdentity();
        RoleInput roleInput = randomRole();
        UUID identityUuid = identityController.create(identityInput);
        UUID roleUuid = roleController.create(roleInput);
        identityController.addRole(identityUuid, roleUuid);
        Assert.assertTrue(identityController.hasRole(identityUuid, roleUuid).isHasRole());
    }

    @Test
    @WithMockUser(authorities = {"admin"})
    public void hasRoleTest() throws InputValidationException {
        IdentityInput identityInput = randomIdentity();
        RoleInput roleInput = randomRole();
        UUID identityUuid = identityController.create(identityInput);
        UUID roleUuid = roleController.create(roleInput);
        identityController.addRole(identityUuid, roleUuid);
        Assert.assertTrue(identityController.hasRole(identityUuid, roleUuid).isHasRole());
    }

    @Test
    @WithMockUser(authorities = {"admin"})
    public void removeRoleTest() throws InputValidationException {
        IdentityInput identityInput = randomIdentity();
        RoleInput roleInput = randomRole();
        UUID identityUuid = identityController.create(identityInput);
        UUID roleUuid = roleController.create(roleInput);
        identityController.addRole(identityUuid, roleUuid);
        Assert.assertTrue(identityController.hasRole(identityUuid, roleUuid).isHasRole());
        identityController.removeRole(identityUuid, roleUuid);
        Assert.assertFalse(identityController.hasRole(identityUuid, roleUuid).isHasRole());
    }

}
