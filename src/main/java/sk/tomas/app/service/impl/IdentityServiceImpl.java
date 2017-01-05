package sk.tomas.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.tomas.app.dao.IdentityDao;
import sk.tomas.app.dao.KeyDao;
import sk.tomas.app.model.Identity;
import sk.tomas.app.model.Key;
import sk.tomas.app.service.IdentityService;

import java.util.List;
import java.util.UUID;

/**
 * Created by tomas on 23.12.2016.
 */
@Service
public class IdentityServiceImpl implements IdentityService {

    @Autowired
    private KeyDao keyDao;
    @Autowired
    private IdentityDao identityDao;

    @Override
    public List<Key> getKeys() {
        return keyDao.getAll();
    }

    @Override
    public UUID create(Identity identity) {
        return identityDao.create(identity);
    }

    @Override
    public Identity findBySurname(String surName) {
        return identityDao.findByValue("surname", surName);
    }

    @Override
    public UUID update(Identity identity) {
        return identityDao.update(identity);
    }

    @Override
    public void delete(UUID uuid) {
        identityDao.delete(uuid);
    }

    @Override
    public List<Identity> list() {
        return identityDao.list();
    }
}
