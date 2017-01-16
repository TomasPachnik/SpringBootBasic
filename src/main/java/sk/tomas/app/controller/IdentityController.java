package sk.tomas.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sk.tomas.app.exception.InputValidationException;
import sk.tomas.app.exception.OutputValidationException;
import sk.tomas.app.model.Input.IdentityInput;
import sk.tomas.app.model.output.Count;
import sk.tomas.app.model.output.IdentityOutput;
import sk.tomas.app.service.IdentityService;

import java.util.List;
import java.util.UUID;

import static sk.tomas.app.util.Constrants.BASE_PATH;

/**
 * Created by tomas on 23.12.2016.
 */
@RestController
@RequestMapping(BASE_PATH + "/identities")
public class IdentityController {

    @Autowired
    private IdentityService identityService;

    @RequestMapping(method = RequestMethod.GET)
    List<IdentityOutput> identities() throws OutputValidationException {
        return identityService.getList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{uuid}")
    IdentityOutput getSingle(@PathVariable("uuid") UUID uuid) throws OutputValidationException {
        return identityService.findIdentityOutputByUuid(uuid);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/withParam")
    List<IdentityOutput> listIdentityWithParam(@RequestParam(defaultValue = "0", value = "firstResult") int firstResult, @RequestParam(defaultValue = "10", value = "maxResult") int maxResult,
                                               @RequestParam(required = false, defaultValue = "uuid", value = "orderBy") String orderBy, @RequestParam(required = false, defaultValue = "false", value = "desc") boolean desc) throws OutputValidationException {
        return identityService.listIdentityOutput(firstResult, maxResult, orderBy, desc);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/count}")
    Count getSingle() throws OutputValidationException {
        return new Count(identityService.count());
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    UUID create(@RequestBody IdentityInput identity) throws InputValidationException {
        return identityService.create(identity);
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(method = RequestMethod.POST, value = "/update/{uuid}")
    void update(@PathVariable("uuid") UUID uuid, @RequestBody IdentityInput identityInput) throws InputValidationException {
        identityService.update(identityInput, uuid);
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(method = RequestMethod.GET, value = "/delete/{uuid}")
    void delete(@PathVariable("uuid") UUID uuid) {
        identityService.delete(uuid);
    }

}
