package mjp.models;

import java.awt.*;

public class Player {
    private final String name;
    private Kingdom kingdom;
    Color color;

    public Player(String name, Kingdom kingdom ) {
        this.name = name;
        this.kingdom = kingdom;
        this.color = kingdom.myColor;
    }

    public String getName() {
        return name;
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public void setKingdom(Kingdom kingdom) {
        this.kingdom = kingdom;
    }
}
