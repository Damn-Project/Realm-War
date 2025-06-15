package mjp.models.blocks;

import mjp.models.Kingdom;
import mjp.models.Position;
import mjp.models.structures.Structure;
import mjp.models.units.Unit;
import mjp.utils.ResourceLoader;

import javax.swing.*;
import java.awt.*;

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

    public Block(Position position) {
        imageLoader = new ResourceLoader();
        this.position = position;
        imagePath = "/home/sopoyan/Desktop/Realm-War/resources";
        hasStructure = false;
        hasUnit = false;
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
        hasUnit = true;
        this.unit = unit;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        hasStructure = true;
        this.structure = structure;
        setIcon(structure.getIcon());
        setBackground(structure.getKingdom().getMyColor());
    }

    public void setKingdom(Kingdom kingdom) {
        this.kingdom = kingdom;
    }

    public void iconSetCheck() {
        if (getUnit() == null && getStructure() == null) {
            setIcon(this.icon);
        } else if (getUnit() != null) {
            setIcon(this.unit.getIcon());
        }else {
            setIcon(this.structure.getIcon());
        }
    }

}
