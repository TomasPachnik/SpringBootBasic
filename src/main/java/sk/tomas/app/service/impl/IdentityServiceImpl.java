package sk.tomas.app.service.impl;

import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.tomas.app.dao.BaseDao;
import sk.tomas.app.dao.IdentityDao;
import sk.tomas.app.exception.InputValidationException;
import sk.tomas.app.iam.model.input.IdentityInput;
import sk.tomas.app.iam.model.output.HasRole;
import sk.tomas.app.iam.model.output.IdentityOutput;
import sk.tomas.app.iam.model.output.PaginationWithCount;
import sk.tomas.app.model.Identity;
import sk.tomas.app.model.Password;
import sk.tomas.app.model.Role;
import sk.tomas.app.service.IdentityService;
import sk.tomas.app.service.RoleService;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by tomas on 23.12.2016.
 */
@Service
@Transactional
public class IdentityServiceImpl extends BaseServiceImpl<Identity> implements IdentityService {

    private static Logger loger = LoggerFactory.getLogger(IdentityServiceImpl.class);

    @Autowired
    private MapperFacade mapper;
    @Autowired
    private IdentityDao identityDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

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
    public UUID create(IdentityInput identityInput) {
        Identity identity = mapper.map(identityInput, Identity.class);
        identity.setPassword(new Password(""));
        UUID uuid = create(identity);
        loger.info("Vytvoreny pouzivatel '{}'", identity);
        return uuid;
    }

    @Override
    public List<IdentityOutput> getList() {
        List<Identity> list = list();
        return mapper.mapAsList(list, IdentityOutput.class);
    }

    @Override
    @Cacheable(value = "findIdentityOutputByUuid", key = "#uuid")
    public IdentityOutput findIdentityOutputByUuid(UUID uuid) {
        Identity byUuid = findByUuid(uuid);
        return mapper.map(byUuid, IdentityOutput.class);
    }

    @Override
    public PaginationWithCount listIdentityOutput(int firstResult, int maxResult, String orderBy, boolean desc) {
        List<Identity> list = list(firstResult, maxResult, orderBy, desc);
        List<IdentityOutput> identityOutputs = mapper.mapAsList(list, IdentityOutput.class);
        return new PaginationWithCount(count(), identityOutputs);
    }

    @Override
    @CacheEvict(value = "findIdentityOutputByUuid", key = "#uuid")
    public void update(IdentityInput identityInput, UUID uuid) {
        Identity old = findByUuid(uuid);
        Identity identity = mapper.map(identityInput, Identity.class);
        identity.setUuid(uuid);
        update(identity);
        loger.info("Pouzivatel '{}' aktualizovany na '{}'", old, identityInput);
    }

    @Override
    public HasRole hasRole(UUID identityUuid, UUID roleUuid) {
        HasRole hasRole = new HasRole();
        hasRole.setHasRole(false);
        Identity identity = findByUuid(identityUuid);
        if (identity != null) {
            Set<Role> roles = identity.getRoles();
            for (Role role : roles) {
                if (role.getUuid().equals(roleUuid)) {
                    hasRole.setHasRole(true);
                    return hasRole;
                }
            }
        }
        return hasRole;
    }

    @Override
    public void addRole(UUID identityUuid, UUID roleUuid) {
        Identity identity = findByUuid(identityUuid);
        Role role = roleService.findByUuid(roleUuid);
        identity.addRole(role);
        update(identity);
        loger.info("Pouzivatelovi '{}' pridana rola '{}'", identity.getLogin(), role.getName());
    }

    @Override
    public void removeRole(UUID identityUuid, UUID roleUuid) {
        Identity identity = findByUuid(identityUuid);
        Role role = roleService.findByUuid(roleUuid);
        identity.removeRole(role);
        update(identity);
        loger.info("Pouzivatelovi '{}' odobrana rola '{}'", identity.getLogin(), role.getName());
    }

    @Override
    @CacheEvict(value = "findIdentityOutputByUuid", key = "#uuid")
    public void delete(UUID uuid) throws InputValidationException {
        getDao().delete(uuid);
    }

}
