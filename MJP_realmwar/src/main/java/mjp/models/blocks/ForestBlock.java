package mjp.models.blocks;

import mjp.models.Position;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ForestBlock extends Block {

    public ForestBlock(Position position) {
        super(position);
        color = Color.GREEN;
        setBackground(color);
        try {
            icon = new ImageIcon(getClass().getResource(imagePath + "/Forest.jpg")).getImage();
        }catch (Exception e) {
            System.out.println("icon does not upload");
        }
    }

}
