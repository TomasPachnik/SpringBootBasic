package sk.tomas.app.model.output;

/**
 * Created by Tomas Pachnik on 24-Feb-17.
 */
public class TokenOutput {

    private String token;
    private Long validity;
    private String login;

    public TokenOutput() {
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

    @Override
    public String toString() {
        return "TokenOutput{" +
                "token='" + token + '\'' +
                ", validity=" + validity +
                ", login='" + login + '\'' +
                '}';
    }
}
