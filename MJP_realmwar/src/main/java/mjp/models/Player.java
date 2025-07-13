package mjp.models;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    int ID;
    private final String name;
    transient Kingdom kingdom;
    public int kingdomID;
    transient Color color;
    static int playerID = 1;
    static ArrayList<Player> players = new ArrayList<>();

    public Player(String name, Kingdom kingdom ) {
        this.name = name;
        this.kingdom = kingdom;
        this.color = kingdom.myColor;
        this.ID = playerID++;
        players.add(this);
    }

    public Color getColor() {
        return color;
    }

    public int getKingdomID() {
        return kingdomID;
    }

    public static int getPlayerID() {
        return playerID;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static void resetID() {
        playerID = 1;
    }

    public int getID() {
        return ID;
    }

    public static ArrayList<Player> getPlayers() {
        return players;
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

    public static void playerRemoveStatic(Player player) {
        players.remove(player);
    }

    public void readyToJson() {
        kingdomID = this.kingdom.getID();
    }

    public static void removeArray() {
        players = new ArrayList<>();
    }
}