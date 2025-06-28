package mjp.models.blocks;

import mjp.models.Kingdom;
import mjp.models.Position;
import mjp.models.structures.Structure;
import mjp.models.units.Unit;
import mjp.utils.ResourceLoader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Block extends JButton {
    public ImageIcon icon = null;
    Color color;
    static String imagePath;
    Position position;
    Kingdom kingdom = null;
    Unit unit = null;
    Structure structure = null;
    Boolean hasUnit;
    Boolean hasStructure;
    ResourceLoader imageLoader;
    ArrayList<Block> neighbors;
    ArrayList<Block> enemies;

    public ArrayList<Block> getEnemies() {
        return enemies;
    }

    public Block(Position position) {
        imageLoader = new ResourceLoader();
        this.position = position;
        imagePath = "/home/sopoyan/Desktop/Realm-War/resources";
        hasStructure = false;
        hasUnit = false;
        neighbors = new ArrayList<>();
        enemies = new ArrayList<>();
    }

    public void setEnemies() {
        for (Block b : neighbors) {
            if (b.hasUnit || b.hasStructure) {
                if (b.hasStructure && !b.getStructure().getKingdom().equals(getKingdom())) {
                    if (!enemies.contains(b))
                        enemies.add(b);
                }else if (b.hasStructure && b.getStructure().getKingdom().equals(getKingdom())){
                    enemies.remove(b);
                }else if (b.hasUnit && !b.getUnit().getKingdom().equals(getKingdom())) {
                    if (!enemies.contains(b))
                        enemies.add(b);
                }else if (b.hasUnit && b.getUnit().getKingdom().equals(getKingdom())) {
                    enemies.remove(b);
                }
            }else {
                    enemies.remove(b);
            }
        }
    }

    public void addNeighbor(Block block) {
        neighbors.add(block);
    }

    public ArrayList<Block> getNeighbors() {
        return neighbors;
    }

    public Position getPosition() {
        return position;
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        if (!hasUnit) {
            hasUnit = true;
            this.unit = unit;
            setKingdom(unit.getKingdom());
            setIcon(unit.getIcon());
            setBorder(BorderFactory.createLineBorder(unit.getKingdom().getMyColor()));
        }
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        if (!hasStructure) {
            hasStructure = true;
            this.structure = structure;
            setKingdom(structure.getKingdom());
            setBorder(BorderFactory.createLineBorder(structure.getKingdom().getMyColor()));
            if (!hasUnit)
                setIcon(structure.getIcon());
        }
    }

    public void setKingdom(Kingdom kingdom) {
        this.kingdom = kingdom;
    }

    public void iconSetCheck() {
        if (getUnit() == null && getStructure() == null) {
            setIcon(this.icon);
        } else if (getUnit() != null) {
            setIcon(this.unit.getIcon());
        } else {
            setIcon(this.structure.getIcon());
        }
    }

    public boolean hasUnit() {
        return this.hasUnit;
    }

    public boolean hasStructure() {
        return this.hasStructure;
    }

    public void removeUnit() {
        unitDied();
    }

    public void unitDied() {
        this.unit = null;
        hasUnit = false;
        iconSetCheck();
        if (!hasStructure)
            setKingdom(null);
    }

    public void structureDied() {
        this.structure = null;
        hasStructure = false;
        iconSetCheck();
        if (!hasUnit)
            setKingdom(null);
    }

    public Block getFreeBlockInNeighbors() {
        for (Block b : neighbors) {
            if (!b.hasUnit && !b.hasStructure && !b.getClass().getSimpleName().equalsIgnoreCase("voidBlock"))
                return b;
        }
        return null;
    }
}
