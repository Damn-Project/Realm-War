package mjp.models.units;


import mjp.models.Kingdom;
import mjp.models.Position;
import mjp.models.blocks.Block;

public class Spearman extends Unit {

    public Spearman(Block block, Kingdom kingdom) {
        super(block, kingdom);
        icon = loader.imageIcons[6];
        foodCost = 4;
        createCost = 5;
        damage = 2;
        health = 5;
    }

}
