package sk.tomas.app.service;

import sk.tomas.app.iam.model.input.RoleInput;
import sk.tomas.app.iam.model.output.RoleOutput;
import sk.tomas.app.model.Role;

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
