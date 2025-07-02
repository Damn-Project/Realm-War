package mjp.models;

import mjp.models.blocks.Block;
import mjp.models.structures.Structure;
import mjp.models.structures.TownHall;
import mjp.models.units.Unit;

import java.awt.*;
import java.util.ArrayList;

public class Kingdom {
    private Player player;
    static Color[] colors = {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW};
    Color myColor;
    int gold;
    int food;
    private Position[] positions;
    private ArrayList<Structure> structures;
    private ArrayList<Unit> units;
    static int kingdomCounter = 0;

    public static void setKingdomCounter(int kingdomCounter) {
        Kingdom.kingdomCounter = kingdomCounter;
    }

    public Kingdom() {
        myColor = colors[kingdomCounter];
        kingdomCounter++;
        gold = 50;
        food = 50;
        structures = new ArrayList<>();
        units = new ArrayList<>();
        setPositions();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setTownHall(TownHall townHall1, TownHall townHall2) {
        structures.add(townHall1);
        structures.add(townHall2);
    }

    public Color getMyColor() {
        return myColor;
    }

    public static Color[] getColors() {
        return colors;
    }

    public int getGold() {
        return gold;
    }

    public Player getPlayer() {
        return player;
    }

    public int getFood() {
        return food;
    }

    public ArrayList<Structure> getStructures() {
        return structures;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public static int getKingdomCounter() {
        return kingdomCounter;
    }

    public void setStructure(Structure structure) {
        this.structures.add(structure);
    }

    public void setUnit(Unit unit) {
        this.units.add(unit);
    }

    public void decreaseGold(int gold) {
        this.gold -= gold;
    }

    public boolean decreaseFood(int food) {
        if (this.food < food) {
            this.food = 0;
            return false;
        } else {
            this.food -= food;
            return true;
        }

    }

    public void penalty() {
        for (int i = 0; i < units.size(); i++) {
            units.get(i).decreaseHealth(1);
        }
    }


    public void addFood(int food) {
        this.food += food;
    }

    public void setPositions() {
        switch (kingdomCounter) {
            case 1: {
                positions = new Position[]{
                        new Position(0, 1), new Position(0, 2), new Position(0, 3), new Position(0, 4), new Position(0, 5), new Position(0, 6), new Position(0, 7), new Position(0, 8),
                        new Position(1, 1), new Position(1, 2), new Position(1, 3), new Position(1, 4), new Position(1, 5), new Position(1, 6), new Position(1, 7), new Position(2, 2),
                        new Position(2, 3), new Position(2, 4), new Position(2, 5), new Position(2, 6), new Position(1, 8), new Position(3, 3), new Position(3, 4), new Position(3, 5),
                        new Position(3, 6), new Position(2, 7)
                };
                break;
            }
            case 2: {
                positions = new Position[]{
                        new Position(9, 1), new Position(9, 2), new Position(9, 3), new Position(9, 4), new Position(9, 5), new Position(9, 6), new Position(9, 7), new Position(9, 8),
                        new Position(8, 1), new Position(8, 2), new Position(8, 3), new Position(8, 4), new Position(8, 5), new Position(8, 6), new Position(8, 7), new Position(7, 2),
                        new Position(7, 3), new Position(7, 4), new Position(7, 5), new Position(7, 6), new Position(8, 8), new Position(6, 3), new Position(6, 4), new Position(6, 5),
                        new Position(6, 6), new Position(7, 7)
                };
                break;
            }
            case 3: {
                positions = new Position[]{
                        new Position(1, 0), new Position(2, 0), new Position(3, 0), new Position(4, 0), new Position(5, 0), new Position(6, 0), new Position(7, 0), new Position(8, 0),
                        new Position(1, 1), new Position(2, 1), new Position(3, 1), new Position(4, 1), new Position(5, 1), new Position(6, 1), new Position(7, 1), new Position(2, 2),
                        new Position(3, 2), new Position(4, 2), new Position(5, 2), new Position(6, 2), new Position(7, 2), new Position(8, 1), new Position(4, 3), new Position(5, 3),
                        new Position(6, 3), new Position(3, 3)
                };
                break;
            }
            case 4: {
                positions = new Position[]{
                        new Position(1, 9), new Position(2, 9), new Position(3, 9), new Position(4, 9), new Position(5, 9), new Position(6, 9), new Position(7, 9), new Position(8, 9),
                        new Position(1, 8), new Position(2, 8), new Position(3, 8), new Position(4, 8), new Position(5, 8), new Position(6, 8), new Position(7, 8), new Position(2, 7),
                        new Position(3, 7), new Position(4, 7), new Position(5, 7), new Position(6, 7), new Position(7, 7), new Position(8, 8), new Position(4, 6), new Position(5, 6),
                        new Position(6, 6), new Position(3, 6)
                };
                break;
            }
        }
    }

    public boolean checkInPosition(Block selectedBlock) {
        for (int i = 0; i < positions.length; i++) {
            if (selectedBlock.getPosition().getX() == positions[i].getX() && selectedBlock.getPosition().getY() == positions[i].getY()) {
                return true;
            }
        }
        return false;
    }

    public void removeUnit(Unit unit) {
        units.remove(unit);
    }

    public void removeStructure(Structure structure) {
        structures.remove(structure);
    }

    public boolean checkHasTownHall() {
        for (Structure s : getStructures()) {
            if (s.getClass().getSimpleName().equalsIgnoreCase("TownHall"))
                return true;
        }
        return false;
    }

    public void addIncome() {
        gold += 5;
    }
}