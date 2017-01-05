package sk.tomas.app.dao.impl;

import org.springframework.stereotype.Repository;
import sk.tomas.app.dao.IdentityDao;
import sk.tomas.app.model.Identity;

/**
 * Created by tomas on 24.12.2016.
 */

@Repository
public class IdentityDaoImpl extends BaseDaoImpl<Identity> implements IdentityDao {

    public IdentityDaoImpl() {
        super(Identity.class);
    }
}
