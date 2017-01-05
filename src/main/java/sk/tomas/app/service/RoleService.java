package sk.tomas.app.service;

import sk.tomas.app.model.Role;

import java.util.UUID;

/**
 * Created by Tomas Pachnik on 05-Jan-17.
 */
public interface RoleService {

    UUID create(Role role);

    Role findByName(String name);
}
