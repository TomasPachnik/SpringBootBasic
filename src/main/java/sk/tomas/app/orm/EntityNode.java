package sk.tomas.app.orm;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by Tomas Pachnik on 05-Jan-17.
 */

@MappedSuperclass
public abstract class EntityNode implements Serializable {

    @Id
    @Column(unique = true, nullable = false, length = 36)
    private String uuid;

    EntityNode(String uuid) {
        this.uuid = uuid;
    }

    EntityNode() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityNode that = (EntityNode) o;

        return uuid != null ? uuid.equals(that.uuid) : that.uuid == null;

    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "EntityNode{" +
                "uuid=" + uuid +
                '}';
    }
}
