package mjp.models.structures;


import mjp.models.Kingdom;
import mjp.models.blocks.Block;

public class Tower extends Structure {
    int damage;

    public Tower(Block block, Kingdom kingdom) {
        super(block, kingdom);
        icon = loader.imageIcons[8];
        createCost = 9;
        health = 10;
        damage = 5;
    }

    @Override
    public void levelUpgrade() {
        super.levelUpgrade();
        this.damage = this.damage + 2;
    }

    public int attack() {
        return damage;
    }
}