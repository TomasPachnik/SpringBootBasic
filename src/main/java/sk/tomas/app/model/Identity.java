package sk.tomas.app.model;

import sk.tomas.app.model.base.Entity;

import java.util.UUID;

/**
 * Created by tomas on 24.12.2016.
 */

public class Identity extends Entity {

    private String name;
    private String surname;
    private int age;

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

    public UUID getUuid() {
        return super.getUuid();
    }

    public void setUuid(int id) {
        super.setUuid(getUuid());
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
