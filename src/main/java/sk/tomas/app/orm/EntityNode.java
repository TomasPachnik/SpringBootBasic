package sk.tomas.app.orm;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Tomas Pachnik on 05-Jan-17.
 */

@MappedSuperclass
public class EntityNode implements Serializable{

    @Id
    private String uuid;

    public EntityNode(String uuid) {
        this.uuid = uuid;
    }

    public EntityNode() {
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
