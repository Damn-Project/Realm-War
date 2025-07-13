package mjp.models.units;


import mjp.models.Kingdom;
import mjp.models.Position;
import mjp.models.blocks.Block;
import mjp.models.structures.Structure;
import mjp.utils.ResourceLoader;

import javax.swing.*;
import java.util.ArrayList;

public abstract class Unit {
    int ID;
    transient Position position;
    transient Kingdom kingdom;
    public int kingdomID;
    transient Structure structure;
    public int structureID;
    transient Block block;
    public int blockID;
    int level;
    int levelUpGradeCost;
    int foodCost;
    public static int createCost;
    int damage;
    int health;
    transient ImageIcon icon; // after json
    transient ResourceLoader loader; // after json
    static int maxLevel = 3;
    boolean inForest;
    static int unitID = 1;
    static ArrayList<Unit> units = new ArrayList<>();
    public String classType;

    public Unit(Block block, Kingdom kingdom) {
        this.ID = unitID++;
        this.kingdom = kingdom;
        this.block = block;
        if (block.getClass().getSimpleName().equalsIgnoreCase("ForestBlock")) {
            inForest = true;
            upgradeDamage();
        }
        this.position = block.getPosition();
        this.level = 1;
        loader = new ResourceLoader();
        levelUpGradeCost = 1;
        units.add(this);
    }

    public static void resetID() {
        unitID = 1;
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
        if (block.getClass().getSimpleName().equalsIgnoreCase("ForestBlock") && !inForest) {
            inForest = true;
            upgradeDamage();
        } else if (!block.getClass().getSimpleName().equalsIgnoreCase("ForestBlock") && inForest) {
            inForest = false;
            decreaseDamage();
        }
    }

    public int getID() {
        return ID;
    }

    public void upgradeDamage() {
        this.damage++;
    }

    public void decreaseDamage() {
        this.damage--;
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

    public static ArrayList<Unit> getUnits() {
        return units;
    }

    public int getLevelUpGradeCost() {
        return levelUpGradeCost;
    }

    public int attack() {
        return getDamage();
    }

    public void decreaseHealth(int damage) {
        this.health -= damage;
    }

    public static void unitRemoveStatic(Unit unit) {
        units.remove(unit);
    }

    public void readyToJson() {
        kingdomID = this.kingdom.getID();
        if (structure != null)
            structureID = structure.getID();
        blockID =block.getID();
    }

    public void makeLoader() {
        loader = new ResourceLoader();
        switch (this.getClass().getSimpleName()) {
            case "Spearman": {
                icon = loader.imageIcons[6];
            }
            case "Swordman": {
                icon = loader.imageIcons[7];
            }
            case "Knight": {
                icon = loader.imageIcons[4];
            }
        }
    }

    public static void removeArray() {
        units = new ArrayList<>();
    }

    public void setKingdom(Kingdom kingdom) {
        this.kingdom = kingdom;
    }

    public int getBlockID() {
        return blockID;
    }

    public int getKingdomID() {
        return kingdomID;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}