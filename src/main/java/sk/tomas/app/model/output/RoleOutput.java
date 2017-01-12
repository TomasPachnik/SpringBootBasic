package sk.tomas.app.model.output;

import java.util.UUID;

/**
 * Created by Tomas Pachnik on 12-Jan-17.
 */
public class RoleOutput {

    private UUID uuid;
    private String name;
    private String description;
    private int level;

    public RoleOutput() {
    }

    public RoleOutput(UUID uuid, String name, String description, int level) {
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.level = level;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
}
