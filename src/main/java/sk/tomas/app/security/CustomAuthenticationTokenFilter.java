package sk.tomas.app.security;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UrlPathHelper;
import sk.tomas.app.service.TokenService;
import sk.tomas.app.util.Util;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tomas on 07.01.2017.
 */

@Component
public class CustomAuthenticationTokenFilter extends GenericFilterBean {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = new UrlPathHelper().getPathWithinApplication(httpRequest);

        String authToken = httpRequest.getHeader("authorization");
        //ako prve ide bearer, bude castejsi
        if (!"/authenticate".equals(path)) {
            String bearer = "Bearer ";
            if (authToken != null && authToken.startsWith(bearer) && authToken.length() > bearer.length()) {
                //TODO lepsia validacia authorization
                String token = authToken.substring(authToken.lastIndexOf(bearer) + bearer.length());
                UserDetails user = tokenService.getUserByToken(token);
                if (user != null) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(token);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    chain.doFilter(request, response);
                } else {
                    throw new BadCredentialsException("Bad Credentials");
                }
            } else {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            }
        } else {
            //autentifikujem
            String basic = "Basic ";
            if (authToken != null && authToken.startsWith(basic) && authToken.length() > basic.length()) {
                //TODO lepsia validacia authorization
                UsernamePasswordAuthenticationToken authRequest = basicCheck(authToken);
                SecurityContextHolder.getContext().setAuthentication(authRequest);
                authenticationManager.authenticate(authRequest);
                chain.doFilter(request, response);
            } else {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
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
        String encodedAuth = authToken.substring(authToken.lastIndexOf(basic) + basic.length());
        String decodedAuth = Util.base64decode(encodedAuth);
        String[] parts = decodedAuth.split(":");
        String username = parts[0];
        String password = parts[1];

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Bad Credentials");
        }
        String tokenByLogin = tokenService.getTokenByLogin(username);
        if (tokenByLogin != null) {//ak uz je prihlaseny zmazem stary token
            tokenService.removeUser(tokenByLogin);
        }
        String token = tokenService.loginUser(userDetails);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
        authRequest.setDetails(token);
        return authRequest;
    }

}
