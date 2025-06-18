package mjp.views;


import mjp.controllers.GameController;
import mjp.models.Position;
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
    private Block[][] Blocks;
    GameController gameController;


    public Block[][] getBlocks() {
        return Blocks;
    }

    public BlockPanel() {
        setLayout(new GridLayout(10, 10));
        setBorder(BorderFactory.createLineBorder(Color.blue));


        Blocks = new Block[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (((i + j == 9) || (i == j))  && i != 9 && i != 0 && i != 4 && i != 5 ) {
                    Blocks[i][j] = new ForestBlock(new Position(i, j));
                }
                else if ((i == 4 || i == 5) && (j == 4 || j == 5))
                    Blocks[i][j] = new VoidBlock(new Position(i, j));
                else
                    Blocks[i][j] = new EmptyBlock(new Position(i, j));
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                add(Blocks[i][j]);
            }
        }
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
