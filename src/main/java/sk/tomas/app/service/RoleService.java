package sk.tomas.app.service;

import sk.tomas.app.exception.InputValidationException;
import sk.tomas.app.exception.OutputValidationException;
import sk.tomas.app.model.Input.RoleInput;
import sk.tomas.app.model.Role;
import sk.tomas.app.model.output.RoleOutput;

import java.util.List;
import java.util.UUID;

/**
 * Created by Tomas Pachnik on 05-Jan-17.
 */
public interface RoleService extends BaseService<Role> {

    Role findByName(String name);

    List<RoleOutput> getList();

    RoleOutput findRoleOutputByUuid(UUID uuid) ;

    UUID create(RoleInput roleInput);

    void update(RoleInput roleInput, UUID uuid);
}
