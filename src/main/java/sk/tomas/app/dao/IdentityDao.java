package sk.tomas.app.dao;

import sk.tomas.app.model.Identity;

import java.util.UUID;

/**
 * Created by tomas on 24.12.2016.
 */
public interface IdentityDao extends BaseDao<Identity> {

    boolean hasRole(UUID identityUuid, UUID roleUuid);

}
