package sk.tomas.app.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import sk.tomas.app.service.TokenService;
import sk.tomas.app.util.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by tomas on 07.01.2017.
 */

@Service
public class TokenServiceImpl implements TokenService {

    private Map<String, UserDetails> loggedUsers;

    @Override
    public String loginUser(UserDetails userDetails) {
        UUID uuid = UUID.randomUUID();
        String token = Util.base64encode(uuid.toString());
        getLoggedUsers().put(token, userDetails);
        return token;
    }

    @Override
    public UserDetails getUserByToken(String token) {
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
        for (Map.Entry<String, UserDetails> entry : getLoggedUsers().entrySet()) {
            if (entry.getValue().getUsername().equals(login)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private Map<String, UserDetails> getLoggedUsers() {
        if (loggedUsers == null) {
            loggedUsers = new HashMap<>();
        }
        return loggedUsers;
    }
}
