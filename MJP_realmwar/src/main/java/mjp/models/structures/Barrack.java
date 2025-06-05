package mjp.models.structures;

import mjp.models.Kingdom;
import mjp.models.blocks.Block;

public class Barrack extends Structure {

    public Barrack(Block block, Kingdom kingdom) {
        super(block, kingdom);
        createCost = 10;
        health = 10;
    }

}