package sk.tomas.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sk.tomas.app.exception.InputValidationException;
import sk.tomas.app.exception.OutputValidationException;
import sk.tomas.app.iam.model.input.RoleInput;
import sk.tomas.app.iam.model.output.RoleOutput;
import sk.tomas.app.service.RoleService;
import sk.tomas.app.validator.RoleValidator;

import java.util.List;
import java.util.UUID;

import static sk.tomas.app.util.Constrants.BASE_PATH;
import static sk.tomas.app.validator.RoleValidator.*;

/**
 * Created by Tomas Pachnik on 12-Jan-17.
 */

@RestController
@RequestMapping(BASE_PATH + "/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public List<RoleOutput> roles() throws OutputValidationException {
        List<RoleOutput> list = roleService.getList();
        RoleValidator.validateOutput(list);
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{uuid}")
    public RoleOutput getSingle(@PathVariable("uuid") UUID uuid) throws OutputValidationException {
        RoleOutput roleOutput = roleService.findRoleOutputByUuid(uuid);
        RoleValidator.validateOutput(roleOutput);
        return roleOutput;
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public UUID create(@RequestBody RoleInput roleInput) throws InputValidationException {
        validateInput(roleInput);
        return roleService.create(roleInput);
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(method = RequestMethod.POST, value = "/update/{uuid}")
    public void update(@PathVariable("uuid") UUID uuid, @RequestBody RoleInput roleInput) throws InputValidationException {
        validateInput(roleInput);
        roleService.update(roleInput, uuid);
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(method = RequestMethod.GET, value = "/delete/{uuid}")
    public void delete(@PathVariable("uuid") UUID uuid) throws InputValidationException {
        roleService.delete(uuid);
    }

}
