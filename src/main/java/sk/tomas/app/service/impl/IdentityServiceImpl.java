package sk.tomas.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.tomas.app.dao.BaseDao;
import sk.tomas.app.dao.IdentityDao;
import sk.tomas.app.dao.KeyDao;
import sk.tomas.app.model.Identity;
import sk.tomas.app.model.Key;
import sk.tomas.app.model.Password;
import sk.tomas.app.model.Role;
import sk.tomas.app.service.IdentityService;

import java.util.List;
import java.util.UUID;

/**
 * Created by tomas on 23.12.2016.
 */
@Service
@Transactional
public class IdentityServiceImpl extends BaseServiceImpl<Identity> implements IdentityService {

    @Autowired
    private KeyDao keyDao;
    @Autowired
    private IdentityDao identityDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Key> getKeys() {
        return keyDao.getAll();
    }

    @Override
    public BaseDao getDao() {
        return identityDao;
    }

    @Override
    public Identity findBySurname(String surName) {
        return identityDao.findByValue("surname", surName);
    }

    @Override
    public Identity findByLogin(String login) {
        return identityDao.findByValue("login", login);
    }

    @Override
    public UUID create(Identity identity) {
        String rawPassword = identity.getPassword().getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        identity.setPassword(new Password(encodedPassword));
        return super.create(identity);
    }

}
