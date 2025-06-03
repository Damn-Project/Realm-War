package mjp.models.blocks;

import mjp.models.Position;

import javax.swing.*;
import java.awt.*;

public abstract class Block extends JButton {
    Image icon;
    Color color;
    static String imagePath;
    Position position;

    public Block(Position position) {
        this.position = position;
        imagePath = "/home/sopoyan/Desktop/Realm-War/resources";
    }


}
