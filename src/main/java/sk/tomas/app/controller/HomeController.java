package sk.tomas.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sk.tomas.app.model.Identity;
import sk.tomas.app.model.Key;
import sk.tomas.app.model.Role;
import sk.tomas.app.model.output.Token;
import sk.tomas.app.service.IdentityService;
import sk.tomas.app.util.Constrants;

import java.util.List;
import java.util.UUID;

import static sk.tomas.app.util.Constrants.*;

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        List<Identity> list = identityService.list();
        for (Identity identity : list) {
            for (Role role : identity.getRoles()) {
                role.setIdentity(null);
            }
        }
        return list;
    }

    @PreAuthorize("hasAuthority('admin')")
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

    @PreAuthorize("hasAuthority('NEEXISTUJUCA')")
    @RequestMapping("/delete")
    void delete() {
        System.out.println("deleted");
    }

    @RequestMapping(method = RequestMethod.GET, value = AUTHORIZE_ENDPOINT)
    Token authenticate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        String token = (String) authentication.getDetails();
        return new Token(token);
    }

}
