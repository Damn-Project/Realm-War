package mjp.models;

import java.util.ArrayList;

public class Position {
    int ID;
    int X;
    int Y;
    static int positionID = 1;
    static ArrayList<Position> positions = new ArrayList<>();

    public Position(int x , int y) {
        X = x;
        Y = y;
        this.ID = positionID++;
        positions.add(this);
    }

    public ArrayList<Position> getPositions() {
        return positions;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }
}
