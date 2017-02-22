package sk.tomas.app.service;

import sk.tomas.app.exception.InputValidationException;
import sk.tomas.app.exception.OutputValidationException;
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

    UUID create(IdentityInput identityInput) throws InputValidationException;

    List<IdentityOutput> getList() throws OutputValidationException;

    IdentityOutput findIdentityOutputByUuid(UUID uuid) throws OutputValidationException, InputValidationException;

    PaginationWithCount listIdentityOutput(int firstResult, int maxResult, String orderBy, boolean desc) throws OutputValidationException, InputValidationException;

    void update(IdentityInput identityInput, UUID uuid) throws InputValidationException;

}
