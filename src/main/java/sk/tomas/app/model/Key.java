package sk.tomas.app.model;

import sk.tomas.app.model.base.Entity;

/**
 * Created by tomas on 23.12.2016.
 */
public class Key extends Entity {
    private String name;
    private String value;

    public Key(int id, String name, String value) {
        super.setId(id);
        this.name = name;
        this.value = value;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Key key = (Key) o;

        if (name != null ? !name.equals(key.name) : key.name != null) return false;
        return value != null ? value.equals(key.value) : key.value == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Key{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
