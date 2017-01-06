package sk.tomas.app.tests;

import ma.glasnost.orika.MapperFacade;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sk.tomas.app.model.Identity;
import sk.tomas.app.model.Role;
import sk.tomas.app.orm.IdentityNode;
import sk.tomas.app.orm.RoleNode;
import sk.tomas.app.util.Utils;

import java.util.UUID;

/**
 * Created by tomas on 06.01.2017.
 */
public class MapperTest extends BaseTest {

    @Autowired
    private MapperFacade mapper;

    @Test
    public void identityMapperTest() {
        Identity identity = Utils.createRandomIdentity();
        identity.setUuid(UUID.randomUUID());
        IdentityNode identityNode = mapper.map(identity, IdentityNode.class);
        Identity mapped = mapper.map(identityNode, Identity.class);
        Assert.assertTrue("Identity sa nezhoduju", identity.equals(mapped));
    }

    @Test
    public void roleMapperTest() {
        Role role = Utils.createRandomRole();
        role.setUuid(UUID.randomUUID());
        RoleNode roleNode = mapper.map(role, RoleNode.class);
        Role mapped = mapper.map(roleNode, Role.class);
        Assert.assertTrue("Roly sa nezhoduju", role.equals(mapped));
    }

}
