package sk.tomas.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import sk.tomas.app.dao.RoleDao;
import sk.tomas.app.model.Identity;
import sk.tomas.app.model.Role;
import sk.tomas.app.service.RoleService;

import java.util.UUID;

/**
 * Created by Tomas Pachnik on 05-Jan-17.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public UUID create(Role role) {
        return roleDao.create(role);
    }

    @Override
    public Role findByName(String name) {
        return roleDao.findByValue("name", name);
    }
}
