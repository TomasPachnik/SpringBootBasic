package sk.tomas.app.service;

import sk.tomas.app.dao.BaseDao;
import sk.tomas.app.model.Identity;
import sk.tomas.app.model.Key;

import java.util.List;
import java.util.UUID;

/**
 * Created by tomas on 23.12.2016.
 */
public interface IdentityService extends BaseService<Identity> {

    List<Key> getKeys();

    UUID create(Identity identity);

    Identity findBySurname(String surName);

    UUID update(Identity identity);

    void delete(UUID uuid);

    List<Identity> list();

}
