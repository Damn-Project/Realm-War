package mjp.models;

import java.awt.*;

public class Player {
    String name;
    Kingdom kingdom;
    Color color;

    public Player(String name, Kingdom kingdom , Color color) {
        this.name = name;
        this.kingdom = kingdom;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public void setKingdom(Kingdom kingdom) {
        this.kingdom = kingdom;
    }
}
