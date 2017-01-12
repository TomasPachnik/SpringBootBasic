package sk.tomas.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sk.tomas.app.exception.InputValidationException;
import sk.tomas.app.exception.OutputValidationException;
import sk.tomas.app.model.Input.RoleInput;
import sk.tomas.app.model.output.RoleOutput;
import sk.tomas.app.service.RoleService;

import java.util.List;
import java.util.UUID;

/**
 * Created by Tomas Pachnik on 12-Jan-17.
 */

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    List<RoleOutput> identities() throws OutputValidationException {
        return roleService.getList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{uuid}")
    RoleOutput getSingle(@PathVariable("uuid") UUID uuid) throws OutputValidationException {
        return roleService.findRoleOutputByUuid(uuid);
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    UUID create(@RequestBody RoleInput roleInput) throws InputValidationException {
        return roleService.create(roleInput);
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(method = RequestMethod.POST, value = "/update/{uuid}")
    void update(@PathVariable("uuid") UUID uuid, @RequestBody RoleInput roleInput) throws InputValidationException {
        roleService.update(roleInput, uuid);
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(method = RequestMethod.GET, value = "/delete/{uuid}")
    void delete(@PathVariable("uuid") UUID uuid) {
        roleService.delete(uuid);
    }

}
