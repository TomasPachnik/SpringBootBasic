package sk.tomas.app.model;


import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by tomas on 24.12.2016.
 */
@Entity
@Table(name = "Identity")
public class Identity extends sk.tomas.app.model.base.Entity {

    private String name;
    private String surname;
    private int age;

    public Identity(int id, String name, String surname, int age) {
        super.setId(id);
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

    public int getId() {
        return super.getId();
    }

    public void setId(int id) {
        super.setId(id);
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

        Identity identity = (Identity) o;

        if (age != identity.age) return false;
        if (name != null ? !name.equals(identity.name) : identity.name != null) return false;
        return surname != null ? surname.equals(identity.surname) : identity.surname == null;

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
        return "Identity{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }
}
