package mjp.models.structures;


import mjp.models.Kingdom;
import mjp.models.blocks.Block;

public class Farm extends Structure {
    private int makeFood;

    public Farm(Block block, Kingdom kingdom) {
        super(block, kingdom);
        icon = loader.imageIcons[2];
        createCost = 7;
        health = 5;
        makeFood = 5;
        kingdom.decreaseGold(getCreateCost());
        classType = "Farm";
    }

    @Override
    public void levelUpgrade() {
        super.levelUpgrade();
        makeFood += 2;
    }

    public int getMakeFood() {
        return makeFood;
    }
}