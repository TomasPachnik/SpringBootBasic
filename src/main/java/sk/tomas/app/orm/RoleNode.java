package sk.tomas.app.orm;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tomas Pachnik on 05-Jan-17.
 */
@Entity
@Table(name = "Role")
public class RoleNode extends EntityNode {

    private String name;
    private String description;
    private int level;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private Set<IdentityNode> identities;

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
        if (!super.equals(o)) return false;

        RoleNode roleNode = (RoleNode) o;

        if (level != roleNode.level) return false;
        if (name != null ? !name.equals(roleNode.name) : roleNode.name != null) return false;
        return description != null ? description.equals(roleNode.description) : roleNode.description == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + level;
        return result;
    }

    public Set<IdentityNode> getIdentities() {
        return identities;
    }

    public void setIdentities(Set<IdentityNode> identities) {
        this.identities = identities;
    }

    @Override
    public String toString() {
        return "RoleNode{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", level=" + level +
                ", identities=" + identities +
                '}';
    }
}
