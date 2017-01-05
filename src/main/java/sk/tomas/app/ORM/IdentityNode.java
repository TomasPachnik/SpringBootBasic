package sk.tomas.app.orm;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Created by Tomas Pachnik on 05-Jan-17.
 */

@Entity
@Table(name = "Identity")
public class IdentityNode  extends EntityNode{

    private String name;
    private String surname;
    private int age;

    public IdentityNode(String uuid, String name, String surname, int age) {
        super(uuid);
        this.name = name;
        this.surname = surname;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        IdentityNode that = (IdentityNode) o;

        if (age != that.age) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return surname != null ? surname.equals(that.surname) : that.surname == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "IdentityNode{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }
}
