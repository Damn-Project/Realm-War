package mjp.models.structures;


import mjp.models.Kingdom;
import mjp.models.blocks.Block;

public class Farm extends Structure {

    public Farm(Block block, Kingdom kingdom) {
        super(block, kingdom);
        createCost = 7;
        health = 5;
    }
}