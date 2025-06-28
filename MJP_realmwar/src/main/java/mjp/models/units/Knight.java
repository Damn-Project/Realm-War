package mjp.models.units;


import mjp.models.Kingdom;
import mjp.models.Position;
import mjp.models.blocks.Block;

public class Knight extends Unit {

    public Knight(Block block, Kingdom kingdom) {
        super(block, kingdom);
        icon = loader.imageIcons[4];
        foodCost = 6;
        createCost = 7;
        damage = 4;
        health = 7;
        kingdom.decreaseGold(getCreateCost());
    }

}
