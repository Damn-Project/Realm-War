package mjp.models.structures;

import com.google.gson.Gson;
import mjp.models.Kingdom;
import mjp.models.Position;
import mjp.models.blocks.Block;
import mjp.models.units.Unit;
import mjp.utils.ResourceLoader;

import javax.swing.*;
import java.util.ArrayList;

public abstract class Structure {
    int ID;
    transient Position position;
    transient Kingdom kingdom;
    public int kingdomID;
    transient Block block;
    public int blockID;
    transient Unit unit;
    public int unitId;
    int level;
    int levelUpGradeCost;
    public static int createCost;
    int health;
    static int structureID = 1;
    static ArrayList<Structure> structures = new ArrayList<>();
    public String classType;
    transient ImageIcon icon;
    transient ResourceLoader loader;
    static int maxLevel = 3;

    public int getLevelUpGradeCost() {
        return levelUpGradeCost;
    }

    public int getLevel() {
        return level;
    }

    public int getBlockID() {
        return blockID;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public int getKingdomID() {
        return kingdomID;
    }

    public void setKingdom(Kingdom kingdom) {
        this.kingdom = kingdom;
    }

    public Structure(Block block, Kingdom kingdom) {
        this.ID = structureID++;
        this.kingdom = kingdom;
        this.block = block;
        this.position = block.getPosition();
        this.level = 1;
        loader = new ResourceLoader();
        levelUpGradeCost = 2;
        structures.add(this);
    }

    public static void resetID() {
        structureID = 1;
    }

    public int getID() {
        return ID;
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

    public void makeLoader() {
        loader = new ResourceLoader();
        switch (this.getClass().getSimpleName()) {
            case "Form": {
                icon = loader.imageIcons[2];
            }
            case "Barrack": {
                icon = loader.imageIcons[1];
            }
            case "Tower": {
                icon = loader.imageIcons[8];
            }
            case "TownHall": {
                icon = loader.imageIcons[9];
            }
        }
    }

    public static ArrayList<Structure> getStructures() {
        return structures;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void decreaseHealth(int damage) {
        this.health -= damage;
    }

    public static void structureRemoveStatic(Structure structure) {
        structures.remove(structure);
    }

    public void readyToJson() {
        kingdomID = this.kingdom.getID();
        if (unit != null)
            unitId = unit.getID();
        blockID =block.getID();
    }

    public static void removeArray() {
        structures = new ArrayList<>();
    }
}