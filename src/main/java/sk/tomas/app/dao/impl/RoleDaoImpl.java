package sk.tomas.app.dao.impl;

import org.springframework.stereotype.Repository;
import sk.tomas.app.dao.RoleDao;
import sk.tomas.app.model.Identity;
import sk.tomas.app.model.Role;
import sk.tomas.app.orm.IdentityNode;
import sk.tomas.app.orm.RoleNode;

import java.util.List;
import java.util.UUID;

/**
 * Created by Tomas Pachnik on 05-Jan-17.
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role, RoleNode> implements RoleDao {

    public RoleDaoImpl() {
        super(Role.class, RoleNode.class);
    }
}
