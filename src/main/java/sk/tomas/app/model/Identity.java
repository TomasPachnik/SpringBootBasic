package sk.tomas.app.model;

import sk.tomas.app.model.base.Entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by tomas on 24.12.2016.
 */

public class Identity extends Entity {

    private String name;
    private String surname;
    private String login;
    private String email;
    private Password password;
    private int age;
    private Set<Role> roles = new HashSet<>();

    public Identity(String name, String surname, String login, String email, Password password, int age) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public Identity(UUID uuid, String name, String surname, int age) {
        super.setUuid(uuid);
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Identity(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Identity() {
    }

    public void addRole(Role role) {
        roles.add(role);
        role.getIdentities().add(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getIdentities().remove(this);
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

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public UUID getUuid() {
        return super.getUuid();
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Identity identity = (Identity) o;

        if (age != identity.age) return false;
        if (name != null ? !name.equals(identity.name) : identity.name != null) return false;
        if (surname != null ? !surname.equals(identity.surname) : identity.surname != null) return false;
        if (login != null ? !login.equals(identity.login) : identity.login != null) return false;
        if (email != null ? !email.equals(identity.email) : identity.email != null) return false;
        if (password != null ? !password.equals(identity.password) : identity.password != null) return false;
        return roles != null ? roles.equals(identity.roles) : identity.roles == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Identity{" +
                "uuid='" + getUuid() + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password=" + password +
                ", age=" + age +
                '}';
    }
}
