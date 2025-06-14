package mjp.models;

import mjp.models.blocks.Block;
import mjp.models.structures.Structure;
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
        kingdomCounter++;
        myColor = colors[kingdomCounter];
        gold = 20;
        food = 20;
        blocks = new ArrayList<>();
        structures = new ArrayList<>();
        units = new ArrayList<>();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
