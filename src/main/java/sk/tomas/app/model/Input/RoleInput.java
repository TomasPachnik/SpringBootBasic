package sk.tomas.app.model.Input;

/**
 * Created by Tomas Pachnik on 12-Jan-17.
 */
public class RoleInput {

    private String name;
    private String description;
    private int level;

    public RoleInput() {
    }

    public RoleInput(String name, String description, int level) {
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
}
