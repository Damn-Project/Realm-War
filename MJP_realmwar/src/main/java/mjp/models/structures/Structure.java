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
    int createCost;
    int health;
    ImageIcon icon;
    ResourceLoader loader;
    static int maxLevel = 3;

    public Structure(Block block, Kingdom kingdom) {
        this.kingdom = kingdom;
        this.block = block;
        this.position = block.getPosition();
        this.level = 1;
        loader = new ResourceLoader();
    }

    public Block getBlock() {
        return block;
    }

    public int getCreateCost() {
        return createCost;
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
        }
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void decreaseHealth(int damage) {
        this.health -= damage;
    }
}