package sk.tomas.app.model.output;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Tomas Pachnik on 12-Jan-17.
 */
public class IdentityOutput implements Serializable {

    private UUID uuid;
    private String name;
    private String surname;
    private String login;
    private String email;
    private int age;

    public IdentityOutput(UUID uuid, String name, String surname, String login, String email, int age) {
        this.uuid = uuid;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.email = email;
        this.age = age;
    }

    public IdentityOutput() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "IdentityOutput{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
