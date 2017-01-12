package sk.tomas.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.tomas.app.dao.BaseDao;
import sk.tomas.app.dao.RoleDao;
import sk.tomas.app.model.Role;
import sk.tomas.app.service.RoleService;

/**
 * Created by Tomas Pachnik on 05-Jan-17.
 */
@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public BaseDao getDao() {
        return roleDao;
    }

    @Override
    public Role findByName(String name) {
        return roleDao.findByValue("name", name);
    }
}
