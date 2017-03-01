package sk.tomas.app.service;

import sk.tomas.app.iam.model.input.IdentityInput;
import sk.tomas.app.iam.model.output.HasRole;
import sk.tomas.app.iam.model.output.IdentityOutput;
import sk.tomas.app.iam.model.output.IdentityPaginationWithCount;
import sk.tomas.app.model.Identity;

import java.util.List;
import java.util.UUID;

/**
 * Created by tomas on 23.12.2016.
 */
public interface IdentityService extends BaseService<Identity> {

    Identity findBySurname(String surName);

    Identity findByLogin(String login);

    UUID create(IdentityInput identityInput);

    List<IdentityOutput> getList();

    IdentityOutput findIdentityOutputByUuid(UUID uuid);

    IdentityPaginationWithCount listIdentityOutput(int firstResult, int maxResult, String orderBy, boolean desc);

    void update(IdentityInput identityInput, UUID uuid);

    HasRole hasRole(UUID identityUuid, UUID roleUuid);

    void addRole(UUID identityUuid, UUID roleUuid);

    void removeRole(UUID identityUuid, UUID roleUuid);
}
