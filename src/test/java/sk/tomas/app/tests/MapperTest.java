package sk.tomas.app.tests;

import ma.glasnost.orika.MapperFacade;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sk.tomas.app.iam.model.input.IdentityInput;
import sk.tomas.app.iam.model.input.RoleInput;
import sk.tomas.app.iam.model.output.IdentityOutput;
import sk.tomas.app.iam.model.output.RoleOutput;
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
        IdentityInput identityInput = Utils.randomIdentity();
        Identity identity = mapper.map(identityInput, Identity.class);
        identity.setUuid(UUID.randomUUID());
        IdentityNode identityNode = mapper.map(identity, IdentityNode.class);
        Identity mapped = mapper.map(identityNode, Identity.class);
        IdentityOutput identityOutput = mapper.map(mapped, IdentityOutput.class);
        IdentityInput identityInput1 = mapper.map(identityOutput, IdentityInput.class);
        Assert.assertTrue("Identity sa nezhoduju", identityInput.equals(identityInput1));
    }

    @Test
    public void roleMapperTest() {
        RoleInput roleInput = Utils.randomRole();
        Role role = mapper.map(roleInput, Role.class);
        role.setUuid(UUID.randomUUID());
        RoleNode roleNode = mapper.map(role, RoleNode.class);
        Role mapped = mapper.map(roleNode, Role.class);
        RoleOutput roleOutput = mapper.map(mapped, RoleOutput.class);
        RoleInput roleInput1 = mapper.map(roleOutput, RoleInput.class);
        Assert.assertTrue("Roly sa nezhoduju", roleInput.equals(roleInput1));
    }

}
