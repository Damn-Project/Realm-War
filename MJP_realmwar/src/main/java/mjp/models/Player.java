package mjp.models;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import java.awt.*;
import java.util.ArrayList;

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