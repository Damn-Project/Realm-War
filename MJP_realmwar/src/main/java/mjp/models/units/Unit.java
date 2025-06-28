package mjp.models.units;


import mjp.models.Kingdom;
import mjp.models.Position;
import mjp.models.blocks.Block;
import mjp.models.structures.Structure;
import mjp.utils.ResourceLoader;

import javax.swing.*;

public abstract class Unit {
    Position position;
    Kingdom kingdom;
    Structure structure;
    Block block;
    int level;
    int levelUpGradeCost;
    int foodCost;
    public static int createCost;
    int damage;
    int health;
    ImageIcon icon;
    ResourceLoader loader;
    static int maxLevel = 3;

    public Unit(Block block, Kingdom kingdom) {
        this.kingdom = kingdom;
        this.block = block;
        this.position = block.getPosition();
        this.level = 1;
        loader = new ResourceLoader();
        levelUpGradeCost = 1;

    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
        this.position = block.getPosition();
    }

    public int getFoodCost() {
        return foodCost;
    }

    public int getCreateCost() {
        return createCost;
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public void levelUpgrade() {
        if (this.level < maxLevel) {
            this.level++;
            health += 2;
            damage++;
            kingdom.decreaseGold(levelUpGradeCost);
        }
    }

    public int getLevelUpGradeCost() {
        return levelUpGradeCost;
    }

    public int attack() {
        return getDamage();
    }

    public void decreaseHealth(int damage) {
        this.health -= damage;
    }}