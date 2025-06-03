package mjp.views;


import mjp.models.blocks.Block;
import mjp.models.blocks.EmptyBlock;
import mjp.models.blocks.ForestBlock;
import mjp.models.blocks.VoidBlock;
import mjp.models.structures.Structure;
import mjp.models.units.Unit;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BlockPanel extends JPanel {
    Block[][] Blocks;


    public BlockPanel() {
        setLayout(new GridLayout(10, 10));
        setBorder(BorderFactory.createLineBorder(Color.blue));


        Blocks = new Block[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (((i + j == 9) || (i == j))  && i != 9 && i != 0 && i != 4 && i != 5 ) {
                    Blocks[i][j] = new ForestBlock();
                }
                else if ((i == 4 || i == 5) && (j == 4 || j == 5))
                    Blocks[i][j] = new VoidBlock();
                else
                    Blocks[i][j] = new EmptyBlock();
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                add(Blocks[i][j]);
            }
        }

    }
}
