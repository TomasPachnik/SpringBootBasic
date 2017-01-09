package sk.tomas.app.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import sk.tomas.app.model.Token;
import sk.tomas.app.service.TokenService;
import sk.tomas.app.util.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static sk.tomas.app.util.Constrants.VALIDITY;

/**
 * Created by tomas on 07.01.2017.
 */

@Service
public class TokenServiceImpl implements TokenService {

    private Map<String, Token> loggedUsers;

    @Override
    public String loginUser(UserDetails userDetails) {
        UUID uuid = UUID.randomUUID();
        String token = Util.base64encode(uuid.toString());
        Token tokenObject = new Token(token, System.currentTimeMillis() + VALIDITY, userDetails);
        getLoggedUsers().put(token, tokenObject);
        return token;
    }

    @Override
    public Token getUserByToken(String token) {
        return getLoggedUsers().get(token);
    }

    @Override
    public void removeUser(String token) {
        getLoggedUsers().remove(token);
    }

    @Override
    public boolean isLogged(String token) {
        return getLoggedUsers().containsKey(token);
    }

    @Override
    public String getTokenByLogin(String login) {
        for (Map.Entry<String, Token> entry : getLoggedUsers().entrySet()) {
            if (entry.getValue().getUserDetails().getUsername().equals(login)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private Map<String, Token> getLoggedUsers() {
        if (loggedUsers == null) {
            loggedUsers = new HashMap<>();
        }
        return loggedUsers;
    }
}
