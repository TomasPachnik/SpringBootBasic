package sk.tomas.app.service;

import sk.tomas.app.model.Identity;
import sk.tomas.app.model.Input.IdentityInput;
import sk.tomas.app.model.Key;
import sk.tomas.app.model.output.IdentityOutput;
import sk.tomas.app.model.output.PaginationWithCount;
import java.util.List;
import java.util.UUID;

/**
 * Created by tomas on 23.12.2016.
 */
public interface IdentityService extends BaseService<Identity> {

    List<Key> getKeys();

    Identity findBySurname(String surName);

    Identity findByLogin(String login);

    UUID create(IdentityInput identityInput);

    List<IdentityOutput> getList();

    IdentityOutput findIdentityOutputByUuid(UUID uuid);

    PaginationWithCount listIdentityOutput(int firstResult, int maxResult, String orderBy, boolean desc);

    void update(IdentityInput identityInput, UUID uuid);

}
