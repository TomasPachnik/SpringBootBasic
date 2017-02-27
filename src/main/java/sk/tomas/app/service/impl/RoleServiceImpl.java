package sk.tomas.app.service.impl;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.tomas.app.dao.BaseDao;
import sk.tomas.app.dao.RoleDao;
import sk.tomas.app.exception.InputValidationException;
import sk.tomas.app.exception.OutputValidationException;
import sk.tomas.app.model.Input.RoleInput;
import sk.tomas.app.model.Role;
import sk.tomas.app.model.output.RoleOutput;
import sk.tomas.app.service.RoleService;
import sk.tomas.app.validator.RoleValidator;

import java.util.List;
import java.util.UUID;

/**
 * Created by Tomas Pachnik on 05-Jan-17.
 */
@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private MapperFacade mapper;

    @Override
    public BaseDao getDao() {
        return roleDao;
    }

    @Override
    public Role findByName(String name) {
        return roleDao.findByValue("name", name);
    }

    @Override
    public List<RoleOutput> getList() {
        List<Role> list = list();
        return mapper.mapAsList(list, RoleOutput.class);
    }

    @Override
    public RoleOutput findRoleOutputByUuid(UUID uuid) {
        Role byUuid = findByUuid(uuid);
        return mapper.map(byUuid, RoleOutput.class);
    }

    @Override
    public UUID create(RoleInput roleInput)  {
        Role role = mapper.map(roleInput, Role.class);
        return create(role);
    }

    @Override
    public void update(RoleInput roleInput, UUID uuid) {
        Role role = mapper.map(roleInput, Role.class);
        role.setUuid(uuid);
        update(role);
    }
}
