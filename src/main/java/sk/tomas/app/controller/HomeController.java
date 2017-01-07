package sk.tomas.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sk.tomas.app.model.Identity;
import sk.tomas.app.model.Key;
import sk.tomas.app.model.Role;
import sk.tomas.app.model.Token;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println(currentPrincipalName);
        System.out.println(authentication.getAuthorities());
        List<Identity> list = identityService.list();
        for (Identity identity : list) {
            for (Role role : identity.getRoles()) {
                role.setIdentity(null);
            }
        }
        return list;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
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

    @RequestMapping(method = RequestMethod.GET, value = "/authenticate")
    Token authenticate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        String token = (String) authentication.getDetails();
        return new Token(token);
    }

}
