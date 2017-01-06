package sk.tomas.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.tomas.app.model.Identity;
import sk.tomas.app.model.Key;
import sk.tomas.app.service.IdentityService;

import java.util.List;
import java.util.UUID;

/**
 * Created by tomas on 23.12.2016.
 */
@RestController
public class HomeController {

    @Autowired
    private IdentityService identityService;

    @RequestMapping("/")
    List<Key> home() {
        return identityService.getKeys();
    }

    @RequestMapping("/identities")
    List<Identity> identities() {
        return identityService.list();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/create")
    UUID save() {
        return identityService.create(new Identity(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 30));
    }

    @RequestMapping("/update")
    UUID update() {
        UUID update = null;
        List<Identity> list = identityService.list();
        if (!list.isEmpty()) {
            Identity identity = list.get(0);
            identity.setName(UUID.randomUUID().toString());
            update = identityService.update(identity);
        }
        return update;
    }

    @RequestMapping("/delete")
    void delete() {
        List<Identity> list = identityService.list();
        if (!list.isEmpty()) {
            Identity identity = list.get(0);
            identityService.delete(identity.getUuid());
        }
    }

}
