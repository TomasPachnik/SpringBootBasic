package sk.tomas.app.model;

import sk.tomas.app.model.base.Entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tomas Pachnik on 05-Jan-17.
 */
public class Role extends Entity {

    private String name;
    private String description;
    private int level;
    private Set<Identity> identities = new HashSet<>();

    public Role() {
    }

    public Role(String name, String description, int level) {
        this.name = name;
        this.description = description;
        this.level = level;
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

    public Set<Identity> getIdentities() {
        return identities;
    }

    public void setIdentities(Set<Identity> identities) {
        this.identities = identities;
    }

    @Override
    public String toString() {
        return "Role{" +
                "uuid='" + getUuid() + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", level=" + level +
                '}';
    }
}
