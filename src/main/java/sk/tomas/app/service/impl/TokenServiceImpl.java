package sk.tomas.app.service.impl;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import sk.tomas.app.annotation.Logger;
import sk.tomas.app.dao.TokenDao;
import sk.tomas.app.iam.model.output.TokenOutput;
import sk.tomas.app.model.Token;
import sk.tomas.app.model.TokenEntity;
import sk.tomas.app.service.TokenService;
import sk.tomas.app.util.Util;

import java.util.UUID;

import static sk.tomas.app.util.Constrants.VALIDITY;

/**
 * Created by tomas on 07.01.2017.
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    TokenDao tokenDao;
    @Autowired
    private MapperFacade mapper;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public String loginUser(UserDetails userDetails) {
        UUID uuid = UUID.randomUUID();
        String token = Util.base64encode(uuid.toString());
        TokenEntity tokenObject = new TokenEntity(token, System.currentTimeMillis() + VALIDITY, userDetails.getUsername());
        tokenDao.create(tokenObject);
        return token;
    }

    @Override
    @SuppressWarnings("unchecked")
    public TokenOutput getTokenEntityByToken(String token) {
        TokenEntity tokenEntity = tokenDao.findByValue("token", token);
        return mapper.map(tokenEntity, TokenOutput.class);
    }

    @Override
    @Cacheable(value = "getUserByToken", key = "#token")
    public Token getUserByToken(String token) {
        TokenEntity tokenEntity = tokenDao.findByValue("token", token);
        if (tokenEntity != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(tokenEntity.getLogin());
            if (userDetails != null) {
                return new Token(tokenEntity.getToken(), tokenEntity.getValidity(), userDetails);
            }
        }
        return null;
    }

    @Override
    @CacheEvict(value = "getUserByToken", key = "#token")
    public void removeUser(String token) {
        TokenEntity tokenEntity = tokenDao.findByValue("token", token);
        if (tokenEntity != null) {
            tokenDao.delete(tokenEntity.getUuid());
        }
    }

    @Override
    public boolean isLogged(String token) {
        TokenEntity tokenEntity = tokenDao.findByValue("token", token);
        return tokenEntity != null && System.currentTimeMillis() < tokenEntity.getValidity();
    }

    @Override
    public String getTokenByLogin(String login) {
        TokenEntity tokenEntity = tokenDao.findByValue("login", login);
        if (tokenEntity != null) {
            return tokenEntity.getToken();
        }
        return null;
    }

}
