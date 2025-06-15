package mjp.models;

import mjp.models.blocks.Block;
import mjp.models.structures.Structure;
import mjp.models.structures.TownHall;
import mjp.models.units.Unit;

import java.awt.*;
import java.util.ArrayList;

public class Kingdom {
    Player player;
    static Color[] colors = {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW};
    Color myColor;
    int gold;
    int food;
    ArrayList<Block> blocks;
    ArrayList<Structure> structures;
    ArrayList<Unit> units;
    static int kingdomCounter = 0;

    public Kingdom() {
        myColor = colors[kingdomCounter];
        kingdomCounter++;
        gold = 20;
        food = 20;
        blocks = new ArrayList<>();
        structures = new ArrayList<>();
        units = new ArrayList<>();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setTownHall(TownHall townHall1, TownHall townHall2) {
        structures.add(townHall1);
        structures.add(townHall2);
    }

    public  Color getMyColor() {
        return myColor;
    }
}
