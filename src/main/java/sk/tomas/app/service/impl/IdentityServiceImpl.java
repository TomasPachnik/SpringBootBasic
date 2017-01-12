package sk.tomas.app.service.impl;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.tomas.app.dao.BaseDao;
import sk.tomas.app.dao.IdentityDao;
import sk.tomas.app.dao.KeyDao;
import sk.tomas.app.exception.InputValidationException;
import sk.tomas.app.exception.OutputValidationException;
import sk.tomas.app.model.Identity;
import sk.tomas.app.model.Input.IdentityInput;
import sk.tomas.app.model.Key;
import sk.tomas.app.model.Password;
import sk.tomas.app.model.output.IdentityOutput;
import sk.tomas.app.service.IdentityService;
import sk.tomas.app.validator.IdentityValidator;

import java.util.List;
import java.util.UUID;

/**
 * Created by tomas on 23.12.2016.
 */
@Service
@Transactional
public class IdentityServiceImpl extends BaseServiceImpl<Identity> implements IdentityService {

    @Autowired
    private MapperFacade mapper;
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
    public UUID create(IdentityInput identityInput) throws InputValidationException {
        IdentityValidator.validateInput(identityInput);
        Identity identity = mapper.map(identityInput, Identity.class);
        identity.setPassword(new Password(""));
        return create(identity);
    }

    @Override
    public List<IdentityOutput> getList() throws OutputValidationException {
        List<Identity> list = list();
        List<IdentityOutput> identityOutputs = mapper.mapAsList(list, IdentityOutput.class);
        for (IdentityOutput output : identityOutputs) {
            IdentityValidator.validateOutput(output);
        }
        return identityOutputs;
    }

    @Override
    public IdentityOutput findIdentityOutputByUuid(UUID uuid) throws OutputValidationException {
        Identity byUuid = findByUuid(uuid);
        IdentityOutput identityOutput = mapper.map(byUuid, IdentityOutput.class);
        IdentityValidator.validateOutput(identityOutput);
        return identityOutput;
    }

}
