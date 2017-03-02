package sk.tomas.app.tests;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sk.tomas.app.exception.InputValidationException;
import sk.tomas.app.iam.model.input.RoleInput;
import sk.tomas.app.model.Identity;
import sk.tomas.app.model.Role;
import sk.tomas.app.service.RoleService;

import java.util.List;
import java.util.UUID;

import static sk.tomas.app.util.Utils.createRandomRole;

/**
 * Created by Tomas Pachnik on 05-Jan-17.
 */
public class RoleTestOld extends BaseTest {

    @Autowired
    RoleService roleService;

    @Test
    public void createRoleTest() {
        //vytvorim rolu
        Role role = createRandomRole();
        roleService.create(role);
        Role byName = roleService.findByName(role.getName());
        Assert.assertTrue("Rola nevytvorena", role.equals(byName));
    }

    @Test
    public void createRoleInputTest() throws InputValidationException {
        //vytvorim identitu
        RoleInput roleInput = new RoleInput("rola", "popis", 8);
        UUID uuid = roleService.create(roleInput);
        Role byUuid = roleService.findByUuid(uuid);
        Assert.assertTrue("Identita nevytvorena", byUuid != null);
    }

    @Test
    public void updateRoleTest() {
        //vytvorim rolu
        Role role = createRandomRole();
        roleService.create(role);
        Role byName = roleService.findByName(role.getName());
        Assert.assertTrue("Rola nevytvorena", role.equals(byName));
        //updatujem rolu
        role.setName(UUID.randomUUID().toString());
        roleService.update(role);
        Role byName2 = roleService.findByName(role.getName());
        Assert.assertTrue("Najdena stara rola", !role.equals(byName));
        Assert.assertTrue("Rola sa nezhoduje", role.equals(byName2));
    }

    @Test
    public void deleteRoleTest() throws InputValidationException {
        Role role = createRandomRole();
        roleService.create(role);
        Role byName = roleService.findByName(role.getName());
        Assert.assertTrue("Rola nevytvorena", role.equals(byName));
        //zmazem rolu
        roleService.delete(byName.getUuid());
        Role byName2 = roleService.findByName(role.getName());
        Assert.assertTrue("Rola sa nevymazala", !role.equals(byName2));
    }

    @Test
    public void listRoleTest() {
        List<Role> list = roleService.list();
        Assert.assertTrue("Rola nenajdena", (list.size() == 1));
    }

}
