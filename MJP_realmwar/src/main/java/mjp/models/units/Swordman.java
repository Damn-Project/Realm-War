package mjp.models.units;


import mjp.models.Kingdom;
import mjp.models.Position;
import mjp.models.blocks.Block;

public class Swordman extends Unit {

    public Swordman(Block block, Kingdom kingdom) {
        super(block, kingdom);
        foodCost = 5;
        createCost = 6;
        damage = 3;
        health = 6;
    }
}
