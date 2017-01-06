package sk.tomas.app.configuration;

import javax.sql.DataSource;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import sk.tomas.app.model.AuthIdentity;
import sk.tomas.app.model.Identity;
import sk.tomas.app.service.IdentityService;

import java.util.List;


/**
 * Created by tomas on 06.01.2017.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    IdentityService identityService;
    @Autowired
    private MapperFacade mapper;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        List<Identity> list = identityService.list();
        List<AuthIdentity> authIdentities = mapper.mapAsList(list, AuthIdentity.class);
        for (AuthIdentity identity : authIdentities) {
            auth.inMemoryAuthentication().withUser(identity.getUsername()).password(identity.getEncodedPassword()).roles(identity.getRoles());
        }
    }
}
