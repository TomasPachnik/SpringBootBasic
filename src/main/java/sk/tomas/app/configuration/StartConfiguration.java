package sk.tomas.app.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.tomas.app.model.Identity;
import sk.tomas.app.model.Password;
import sk.tomas.app.model.Role;
import sk.tomas.app.service.IdentityService;
import sk.tomas.app.service.RoleService;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * Created by Tomas Pachnik on 12-Jan-17.
 */

@Component
class StartConfiguration {

    static Logger loger = LoggerFactory.getLogger(StartConfiguration.class);

    @Autowired
    private
    IdentityService identityService;

    @Autowired
    private
    RoleService roleService;

    @PostConstruct
    private void defaultIntialization() {
        Identity sysadmin = identityService.findByLogin("sysadmin");
        if (sysadmin == null) {
            sysadmin = new Identity("admin", "admin", "sysadmin", "admin@email.sk", new Password("Heslo123"), 99);
            identityService.create(sysadmin);
        } else {
            System.out.println("admin uz existuje");
        }
        Role adminRole = roleService.findByName("admin");
        if (adminRole == null) {
            adminRole = new Role("admin", "admin rola", 8, sysadmin);
            roleService.create(adminRole);
        } else {
            System.out.println("rola uz existuje");
        }
    }

}
