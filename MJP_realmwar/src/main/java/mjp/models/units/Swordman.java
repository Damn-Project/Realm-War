package mjp.models.units;


import mjp.models.Kingdom;
import mjp.models.Position;
import mjp.models.blocks.Block;

public class Swordman extends Unit {

    public Swordman(Block block, Kingdom kingdom) {
        super(block, kingdom);
        icon = loader.imageIcons[7];
        foodCost = 5;
        createCost = 5;
        damage = 3;
        health = 5;
        kingdom.decreaseGold(getCreateCost());
        classType = "Swordman";
    }
}
