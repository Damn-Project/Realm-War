package mjp.models.units;


import mjp.models.Kingdom;
import mjp.models.Position;
import mjp.models.blocks.Block;

public class Peasant extends Unit {

    public Peasant(Block block, Kingdom kingdom) {
        super(block, kingdom);
        foodCost = 2;
        createCost = 3;
        health = 4;
    }
}