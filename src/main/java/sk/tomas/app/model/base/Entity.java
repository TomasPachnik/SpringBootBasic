package sk.tomas.app.model.base;

import java.util.UUID;

/**
 * Created by Tomas Pachnik on 04-Jan-17.
 */

public abstract class Entity {

    private UUID uuid;

    public Entity(UUID uuid) {
        this.uuid = uuid;
    }

    public Entity() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;

        return uuid != null ? uuid.equals(entity.uuid) : entity.uuid == null;

    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "uuid='" + uuid + '\'' +
                '}';
    }
}
