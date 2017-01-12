package sk.tomas.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sk.tomas.app.exception.InputValidationException;
import sk.tomas.app.model.Identity;
import sk.tomas.app.model.Input.IdentityInput;
import sk.tomas.app.model.Role;
import sk.tomas.app.model.output.IdentityOutput;
import sk.tomas.app.service.IdentityService;

import java.util.List;
import java.util.UUID;

/**
 * Created by tomas on 23.12.2016.
 */
@RestController
@RequestMapping("/identities")
public class IdentityController {

    @Autowired
    private IdentityService identityService;

    @RequestMapping(method = RequestMethod.GET)
    List<IdentityOutput> identities() throws InputValidationException {
        return identityService.getList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{uuid}")
    Identity getSingle(@PathVariable("uuid") UUID uuid) {
        return identityService.findByUuid(uuid);
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    UUID create(@RequestBody IdentityInput identity) throws InputValidationException {
        return identityService.create(identity);
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    void update(@RequestBody Identity identity) {
        identityService.update(identity);
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(method = RequestMethod.GET, value = "/delete/uuid")
    void delete(@PathVariable("uuid") UUID uuid) {
        identityService.delete(uuid);
    }

}
