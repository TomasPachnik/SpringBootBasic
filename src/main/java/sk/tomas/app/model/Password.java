package sk.tomas.app.model;

/**
 * Created by tomas on 05.01.2017.
 */
public class Password {

    private String password;

    public Password(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "password is hidden";
    }
}