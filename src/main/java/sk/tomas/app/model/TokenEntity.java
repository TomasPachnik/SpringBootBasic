package sk.tomas.app.model;

import sk.tomas.app.model.base.Entity;

/**
 * Created by Tomas Pachnik on 18-Jan-17.
 */
public class TokenEntity extends Entity {

    private String token;
    private Long validity;
    private String login;

    public TokenEntity() {
    }

    public TokenEntity(String token, Long validity, String login) {
        this.token = token;
        this.validity = validity;
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getValidity() {
        return validity;
    }

    public void setValidity(Long validity) {
        this.validity = validity;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
