package sk.tomas.app.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UrlPathHelper;
import sk.tomas.app.model.Token;
import sk.tomas.app.service.TokenService;
import sk.tomas.app.util.Util;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static sk.tomas.app.util.Constrants.AUTHORIZE_ENDPOINT;

/**
 * Created by tomas on 07.01.2017.
 */

@Component
public class CustomAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationTokenFilter.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = new UrlPathHelper().getPathWithinApplication(request);

        String authToken = request.getHeader("authorization");
        
        //ako prve ide bearer, bude castejsi
        if (!AUTHORIZE_ENDPOINT.equals(path)) {
            String bearer = "Bearer ";
            if (authToken != null && authToken.startsWith(bearer) && authToken.length() > bearer.length()) {
                //TODO lepsia validacia authorization
                String token = authToken.substring(authToken.lastIndexOf(bearer) + bearer.length());
                Token user = tokenService.getUserByToken(token);
                if (user != null) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUserDetails().getUsername(), null, user.getUserDetails().getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(token);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    filterChain.doFilter(request, response);
                } else {
                    logger.warn("Zadany token '{}' sa v systeme nenachadza.", token);
                    throw new BadCredentialsException("Bad Credentials");
                }
            } else {
                logger.warn("Bearer token '{}' je v nespravnom tvare, alebo neexistuje.", authToken);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            }
        } else {
            //autentifikujem
            String basic = "Basic ";
            if (authToken != null && authToken.startsWith(basic) && authToken.length() > basic.length()) {
                //TODO lepsia validacia authorization
                UsernamePasswordAuthenticationToken authRequest = basicCheck(authToken);
                SecurityContextHolder.getContext().setAuthentication(authRequest);
                filterChain.doFilter(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            }
        }
    }

    /**
     * vrati UsernamePasswordAuthenticationToken vygenerovany z basic auth
     *
     * @param authToken
     * @return
     */
    private UsernamePasswordAuthenticationToken basicCheck(String authToken) throws BadCredentialsException {
        String basic = "Basic ";
        String encodedAuth = authToken.substring(authToken.lastIndexOf(basic) + basic.length())
        String decodedAuth = Util.base64decode(encodedAuth);
        String[] parts = decodedAuth.split(":");
        if (parts.length != 2) {
            throw new BadCredentialsException("Bad Credentials");
        }
        String username = parts[0];
        String password = parts[1];

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            logger.warn("Pouzivatel '{}' nenajdeny.", username);
            throw new BadCredentialsException("Bad Credentials");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            logger.warn("Pouzivatel '{}' zadal nespravne heslo.", username);
            throw new BadCredentialsException("Bad Credentials");
        }
        String tokenByLogin = tokenService.getTokenByLogin(username);
        if (tokenByLogin != null) {//ak uz token existuje, tak ho zmazem
            tokenService.removeUser(tokenByLogin);
        }
        String token = tokenService.loginUser(userDetails);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
        authRequest.setDetails(token);
        logger.info("Prihlasenie pouzivatela '{}', vydany token: '{}'", userDetails.getUsername(), token);
        return authRequest;
    }

}
