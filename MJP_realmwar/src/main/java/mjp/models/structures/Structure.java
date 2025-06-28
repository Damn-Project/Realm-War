package mjp.models.structures;

import mjp.models.Kingdom;
import mjp.models.Position;
import mjp.models.blocks.Block;
import mjp.models.units.Unit;
import mjp.utils.ResourceLoader;

import javax.swing.*;

public abstract class Structure {
    Position position;
    Kingdom kingdom;
    Block block;
    Unit unit;
    int level;
    int levelUpGradeCost;
    public static int createCost;
    int health;

    public int getLevelUpGradeCost() {
        return levelUpGradeCost;
    }

    public int getLevel() {
        return level;
    }

    ImageIcon icon;
    ResourceLoader loader;
    static int maxLevel = 3;

    public Structure(Block block, Kingdom kingdom) {
        this.kingdom = kingdom;
        this.block = block;
        this.position = block.getPosition();
        this.level = 1;
        loader = new ResourceLoader();
        levelUpGradeCost = 2;
    }

    public Block getBlock() {
        return block;
    }

    public int getCreateCost() {
        return createCost;
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public int getHealth() {
        return health;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void levelUpgrade() {
        if (this.level < maxLevel) {
            this.level++;
            health += 2;
            kingdom.decreaseGold(levelUpGradeCost);
        }
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void decreaseHealth(int damage) {
        this.health -= damage;
    }
}