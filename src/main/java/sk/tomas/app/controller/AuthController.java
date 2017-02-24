package sk.tomas.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sk.tomas.app.model.TokenEntity;
import sk.tomas.app.model.output.Token;
import sk.tomas.app.model.output.TokenOutput;
import sk.tomas.app.service.TokenService;

import static sk.tomas.app.util.Constrants.AUTHORIZE_ENDPOINT;
import static sk.tomas.app.util.Constrants.TOKEN_INFO_ENDPOINT;

/**
 * Created by Tomas Pachnik on 12-Jan-17.
 */
@RestController
public class AuthController {

    @Autowired
    TokenService tokenService;

    @RequestMapping(method = RequestMethod.GET, value = AUTHORIZE_ENDPOINT)
    public Token authenticate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) authentication.getDetails();
        return new Token(token);
    }

    @RequestMapping(method = RequestMethod.GET, value = TOKEN_INFO_ENDPOINT)
    public TokenOutput tokenInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) authentication.getDetails();
        return tokenService.getTokenEntityByToken(token);
    }

}
