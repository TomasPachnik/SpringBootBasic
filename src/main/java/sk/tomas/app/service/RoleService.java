package sk.tomas.app.service;

import sk.tomas.app.model.Role;

/**
 * Created by Tomas Pachnik on 05-Jan-17.
 */
public interface RoleService extends BaseService<Role>{

    Role findByName(String name);
}
