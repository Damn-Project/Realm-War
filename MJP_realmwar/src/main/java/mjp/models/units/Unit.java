package mjp.models.units;


import mjp.models.Kingdom;
import mjp.models.Position;
import mjp.models.blocks.Block;
import mjp.models.structures.Structure;

public abstract class Unit {
    Position position;
    Kingdom kingdom;
    Structure structure;
    Block block;
    int level;
    int foodCost;
    int createCost;
    int damage;
    int health;

    public Unit(Block block, Kingdom kingdom) {
        this.kingdom = kingdom;
        this.block = block;
        this.position = block.getPosition();
        this.level = 1;
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
        this.level++;
        health += 2;
        damage++;
    }

    public void decreaseHealth(int damage) {
        this.health -= damage;
    }
}
