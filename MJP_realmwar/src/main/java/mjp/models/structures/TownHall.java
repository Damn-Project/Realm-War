package mjp.models.structures;

import mjp.models.Kingdom;
import mjp.models.blocks.Block;

public class TownHall extends Structure {

    public TownHall(Block block, Kingdom kingdom) {
        super(block, kingdom);
        health = 20;
    }
}