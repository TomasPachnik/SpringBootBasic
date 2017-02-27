package sk.tomas.app.service;

import org.springframework.security.core.userdetails.UserDetails;
import sk.tomas.app.iam.model.output.TokenOutput;
import sk.tomas.app.model.Token;

/**
 * Created by tomas on 07.01.2017.
 */
public interface TokenService {

    String loginUser(UserDetails userDetails);

    TokenOutput getTokenEntityByToken(String token);

    Token getUserByToken(String token);

    void removeUser(String token);

    boolean isLogged(String token);

    String getTokenByLogin(String login);

}
