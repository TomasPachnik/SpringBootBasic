package sk.tomas.app.model;

import sk.tomas.app.model.base.Entity;

/**
 * Created by Tomas Pachnik on 05-Jan-17.
 */
public class Role extends Entity {

    private String name;
    private String description;
    private int level;
    private Identity identity;

    public Role() {
    }

    public Role(String name, String description, int level) {
        this.name = name;
        this.description = description;
        this.level = level;
    }

    public Role(String name, String description, int level, Identity identity) {
        this.name = name;
        this.description = description;
        this.level = level;
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (level != role.level) return false;
        if (name != null ? !name.equals(role.name) : role.name != null) return false;
        return description != null ? description.equals(role.description) : role.description == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + level;
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", level=" + level +
                ", identity=" + identity +
                '}';
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }
}
