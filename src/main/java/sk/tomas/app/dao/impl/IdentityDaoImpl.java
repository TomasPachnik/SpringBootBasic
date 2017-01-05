package sk.tomas.app.dao.impl;

import org.springframework.stereotype.Repository;
import sk.tomas.app.dao.IdentityDao;
import sk.tomas.app.model.Identity;
import sk.tomas.app.orm.IdentityNode;

/**
 * Created by tomas on 24.12.2016.
 */

@Repository
public class IdentityDaoImpl extends BaseDaoImpl<Identity, IdentityNode> implements IdentityDao {

    public IdentityDaoImpl() {
        super(Identity.class, IdentityNode.class);
    }
}
