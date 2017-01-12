package sk.tomas.app.service;

import sk.tomas.app.exception.InputValidationException;
import sk.tomas.app.model.Identity;
import sk.tomas.app.model.Input.IdentityInput;
import sk.tomas.app.model.Key;

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

}
