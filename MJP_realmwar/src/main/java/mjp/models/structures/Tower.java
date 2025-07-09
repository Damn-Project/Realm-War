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
        if (block.getClass().getSimpleName().equalsIgnoreCase("ForestBlock")) {
            damage += 2;
        }
        kingdom.decreaseGold(getCreateCost());
        classType = "Tower";
    }

    @Override
    public void levelUpgrade() {
        super.levelUpgrade();
        this.damage = this.damage + 2;
    }

    public int attack() {
        return damage;
    }

    public int getDamage() {
        return this.damage;
    }
}