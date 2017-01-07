package sk.tomas.app.security;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UrlPathHelper;
import sk.tomas.app.service.TokenService;

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
    private TokenService tokenService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = new UrlPathHelper().getPathWithinApplication(httpRequest);

        if ("/authenticate".equals(path)) {
            //autentifikujem
            String authToken = httpRequest.getHeader("authorization");
            String basic = "Basic ";
            if (authToken != null && authToken.startsWith(basic) && authToken.length() > basic.length()) {
                String encodedAuth = authToken.substring(authToken.lastIndexOf(basic) + basic.length());
                String decodedAuth = StringUtils.newStringUtf8(Base64.decodeBase64(encodedAuth));
                String[] parts = decodedAuth.split(":");
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(parts[0], parts[1]);
                Authentication authResult = authenticationManager.authenticate(authRequest);
                chain.doFilter(request, response);
            } else {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            }
        } else {
            String authToken = httpRequest.getHeader("authorization");
            String bearer = "Bearer ";
            if (authToken != null && authToken.startsWith(bearer) && authToken.length() > bearer.length()) {
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
        }


        //authRequest.setDetails(ssoTokenAuthenticationDetailsSource.buildDetails(request));

        // Delegate authentication to SsoTokenAuthenticationProvider, he will call the SsoTokenAuthenticationProvider <-- because of the configuration in WebSecurityConfig.java
        //  httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//        chain.doFilter(request, response);
    }
}
