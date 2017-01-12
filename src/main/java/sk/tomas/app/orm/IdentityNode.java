package sk.tomas.app.orm;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tomas Pachnik on 05-Jan-17.
 */

@Entity
@Table(name = "Identity")
public class IdentityNode extends EntityNode {

    private String name;
    private String surname;
    private String login;
    private String email;
    private String encodedPassword;
    private int age;
    @OneToMany(mappedBy = "identity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Set<RoleNode> roles;

    public IdentityNode(String uuid, String name, String surname, String login, String email, String encodedPassowrd, int age) {
        super(uuid);
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.email = email;
        this.encodedPassword = encodedPassowrd;
        this.age = age;
    }

    public IdentityNode() {
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

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public Set<RoleNode> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleNode> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        IdentityNode that = (IdentityNode) o;

        if (age != that.age) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (encodedPassword != null ? !encodedPassword.equals(that.encodedPassword) : that.encodedPassword != null)
            return false;
        return roles != null ? roles.equals(that.roles) : that.roles == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (encodedPassword != null ? encodedPassword.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "IdentityNode{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", encodedPassword='" + encodedPassword + '\'' +
                ", age=" + age +
                '}';
    }
}
