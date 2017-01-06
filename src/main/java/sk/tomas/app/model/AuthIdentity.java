package sk.tomas.app.model;

import java.util.Arrays;

/**
 * Created by tomas on 06.01.2017.
 */
public class AuthIdentity {
    private String username;
    private String encodedPassword;
    private String[] roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "AuthIdentity{" +
                "username='" + username + '\'' +
                ", encodedPassword='" + encodedPassword + '\'' +
                ", roles=" + Arrays.toString(roles) +
                '}';
    }
}
