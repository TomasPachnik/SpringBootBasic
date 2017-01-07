package sk.tomas.app.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by tomas on 07.01.2017.
 */
public interface TokenService {

    String loginUser(UserDetails userDetails);

    UserDetails getUseByUuid(String token);

    void removeUser(String token);

    boolean isLogged(String token);

    String getTokenByLogin(String token);

}
