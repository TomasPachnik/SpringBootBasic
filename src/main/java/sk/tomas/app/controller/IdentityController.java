package sk.tomas.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sk.tomas.app.exception.InputValidationException;
import sk.tomas.app.exception.OutputValidationException;
import sk.tomas.app.iam.model.input.IdentityInput;
import sk.tomas.app.iam.model.output.Count;
import sk.tomas.app.iam.model.output.HasRole;
import sk.tomas.app.iam.model.output.IdentityOutput;
import sk.tomas.app.iam.model.output.IdentityPaginationWithCount;
import sk.tomas.app.service.IdentityService;
import sk.tomas.app.validator.IdentityValidator;

import java.util.List;
import java.util.UUID;

import static sk.tomas.app.util.Constrants.BASE_PATH;

/**
 * Created by tomas on 23.12.2016.
 */
@RestController
@RequestMapping(BASE_PATH + "/identities")
public class IdentityController implements Controller<IdentityInput, IdentityOutput, IdentityPaginationWithCount> {

    @Autowired
    private IdentityService identityService;

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public List<IdentityOutput> list() throws OutputValidationException {
        List<IdentityOutput> list = identityService.getList();
        IdentityValidator.validateOutput(list);
        return list;
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/{uuid}")
    public IdentityOutput getSingle(@PathVariable("uuid") UUID uuid) throws OutputValidationException, InputValidationException {
        IdentityOutput identityOutputByUuid = identityService.findIdentityOutputByUuid(uuid);
        IdentityValidator.validateOutput(identityOutputByUuid);
        return identityOutputByUuid;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{identityUuid}/hasRole/{roleUuid}")
    public HasRole hasRole(@PathVariable("identityUuid") UUID identityUuid, @PathVariable("roleUuid") UUID roleUuid) {
        return identityService.hasRole(identityUuid, roleUuid);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{identityUuid}/addRole/{roleUuid}")
    public void addRole(@PathVariable("identityUuid") UUID identityUuid, @PathVariable("roleUuid") UUID roleUuid) {
        identityService.addRole(identityUuid, roleUuid);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{identityUuid}/removeRole/{roleUuid}")
    public void removeRole(@PathVariable("identityUuid") UUID identityUuid, @PathVariable("roleUuid") UUID roleUuid) {
        identityService.removeRole(identityUuid, roleUuid);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/withParam")
    public IdentityPaginationWithCount listWithParam(@RequestParam(defaultValue = "0", value = "firstResult") int firstResult, @RequestParam(defaultValue = "10", value = "maxResult") int maxResult,
                                                     @RequestParam(required = false, defaultValue = "uuid", value = "orderBy") String orderBy, @RequestParam(required = false, defaultValue = "false", value = "desc") boolean desc) throws InputValidationException, OutputValidationException {
        IdentityValidator.validateInput(firstResult, maxResult, orderBy);
        IdentityPaginationWithCount identityPaginationWithCount = identityService.listIdentityOutput(firstResult, maxResult, orderBy, desc);
        IdentityValidator.validateOutput(identityPaginationWithCount.getIdentityOutputs());
        return identityPaginationWithCount;
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/count}")
    public Count getCount() throws OutputValidationException {
        return new Count(identityService.count());
    }

    @Override
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public UUID create(@RequestBody IdentityInput identity) throws InputValidationException {
        IdentityValidator.validateInput(identity);
        return identityService.create(identity);
    }

    @Override
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(method = RequestMethod.POST, value = "/update/{uuid}")
    public void update(@PathVariable("uuid") UUID uuid, @RequestBody IdentityInput identityInput) throws InputValidationException {
        IdentityValidator.validateInput(identityInput, uuid);
        identityService.update(identityInput, uuid);
    }

    @Override
    public Controller getController() {
        return this;
    }

    @Override
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(method = RequestMethod.GET, value = "/delete/{uuid}")
    public void delete(@PathVariable("uuid") UUID uuid) throws InputValidationException {
        identityService.delete(uuid);
    }

}
