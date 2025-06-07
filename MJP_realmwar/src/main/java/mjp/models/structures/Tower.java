package mjp.models.structures;


import mjp.models.Kingdom;
import mjp.models.blocks.Block;

public class Tower extends Structure {

    public Tower(Block block, Kingdom kingdom) {
        super(block, kingdom);
        icon = loader.imageIcons[8];
        createCost = 9;
        health = 10;
    }
}