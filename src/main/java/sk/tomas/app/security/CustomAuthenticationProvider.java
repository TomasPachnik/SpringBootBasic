package sk.tomas.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sk.tomas.app.annotation.Logger;
import sk.tomas.app.service.TokenService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by tomas on 06.01.2017.
 */

@Logger
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        //TODO validacia hesla
        //TODO skusit presunut logiku ktora nepatri do filtra sem
        //TODO upratat to tu, vytvorit privatne metody atd.
        //TODO vyriesit jednotne server hlasky
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails != null) {
            String tokenByLogin = tokenService.getTokenByLogin(username);
            if (tokenByLogin != null) {//ak uz je prihlaseny zmazem stary token
                tokenService.removeUser(tokenByLogin);
            }
            String token = tokenService.loginUser(userDetails);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
            authToken.setDetails(token);
            SecurityContextHolder.getContext().setAuthentication(authToken);
            return authToken;
        }

//    boolean flag = true;
        //    if (flag) {
        //        List<GrantedAuthority> authorities = new ArrayList<>();
        //         authorities.add(new SimpleGrantedAuthority("ADMIN"));
        //         UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password, authorities);
        //        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        //        return usernamePasswordAuthenticationToken;
        //    } else
        throw new BadCredentialsException("Bad Credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
