package mjp.models.structures;


import mjp.models.Kingdom;
import mjp.models.blocks.Block;

public class Market extends Structure {

    public Market(Block block, Kingdom kingdom) {
        super(block, kingdom);
        createCost = 7;
        health = 5;
    }
}