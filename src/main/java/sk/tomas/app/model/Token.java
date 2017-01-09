package sk.tomas.app.model;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by tomas on 09.01.2017.
 */
public class Token {

    private String token;
    private long validity;
    private UserDetails userDetails;

    public Token(String token, long validity, UserDetails userDetails) {
        this.token = token;
        this.validity = validity;
        this.userDetails = userDetails;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getValidity() {
        return validity;
    }

    public void setValidity(long validity) {
        this.validity = validity;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token1 = (Token) o;

        if (validity != token1.validity) return false;
        if (token != null ? !token.equals(token1.token) : token1.token != null) return false;
        return userDetails != null ? userDetails.equals(token1.userDetails) : token1.userDetails == null;
    }

    @Override
    public int hashCode() {
        int result = token != null ? token.hashCode() : 0;
        result = 31 * result + (int) (validity ^ (validity >>> 32));
        result = 31 * result + (userDetails != null ? userDetails.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                ", validity=" + validity +
                ", userDetails=" + userDetails +
                '}';
    }
}
