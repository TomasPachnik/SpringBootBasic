package sk.tomas.app.orm;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Tomas Pachnik on 18-Jan-17.
 */

@Entity
@Table(name = "Token")
public class TokenNode extends EntityNode {

    private String token;
    private Long validity;
    private String login;

    public TokenNode() {
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
