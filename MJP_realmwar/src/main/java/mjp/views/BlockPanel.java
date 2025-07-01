package mjp.views;


import mjp.controllers.GameController;
import mjp.models.Position;
import mjp.models.blocks.Block;
import mjp.models.blocks.EmptyBlock;
import mjp.models.blocks.ForestBlock;
import mjp.models.blocks.VoidBlock;

import javax.swing.*;
import java.awt.*;


public class BlockPanel extends JPanel {
    private static Block[][] Blocks;
    GameController gameController;
    private InfoPanel infoPanel;
    private ActionPanel actionPanel;
    static Color borderColor = new Color(212, 175, 55); // طلایی
    static Color primaryColor = new Color(30, 30, 30);// مشکی مات


    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    public ActionPanel getActionPanel() {
        return actionPanel;
    }

    public void setActionPanel(ActionPanel actionPanel) {
        this.actionPanel = actionPanel;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setInfoPanel(InfoPanel infoPanel) {
        this.infoPanel = infoPanel;
    }

    public Block[][] getBlocks() {
        return Blocks;
    }

    public BlockPanel() {
        setLayout(new GridLayout(10, 10));


        Blocks = new Block[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (((i + j == 9) || (i == j)) && i != 9 && i != 0 && i != 4 && i != 5) {
                    Blocks[i][j] = new ForestBlock(new Position(i, j));
                    Block b = Blocks[i][j];
                    Blocks[i][j].setBorder(BorderFactory.createLineBorder(borderColor));
                    Blocks[i][j].setBackground(primaryColor);
                    Blocks[i][j].addActionListener(e -> {
                        infoPanel.setBlockInfo(b);
                        gameController.setSelected1(b);
                    });
                } else if ((i == 4 || i == 5) && (j == 4 || j == 5)) {
                    Blocks[i][j] = new VoidBlock(new Position(i, j));
                    Blocks[i][j].addActionListener(e -> this.getInfoPanel().setVoidBlockText());
                } else {
                    Blocks[i][j] = new EmptyBlock(new Position(i, j));
                    Blocks[i][j].setBorder(BorderFactory.createLineBorder(borderColor));
                    Blocks[i][j].setBackground(primaryColor);
                    Block b = Blocks[i][j];
                    Blocks[i][j].addActionListener(e -> {
                        infoPanel.setBlockInfo(b);
                        gameController.setSelected1(b);
                    });
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                add(Blocks[i][j]);
            }
        }
        removeBorder();
        setBlocksNeighbors();
    }

    public void setBlocksNeighbors() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = -1; k < 2; k++) {
                    for (int l = -1; l < 2; l++) {
                        if (k == 0 && l == 0)
                            continue;
                        if (isInRangePosition(i + k) && isInRangePosition(j + l)) {
                            Blocks[i][j].addNeighbor(Blocks[i + k][j + l]);
                        }
                    }
                }
            }
        }
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void removeBorder() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (!Blocks[i][j].hasUnit() && !Blocks[i][j].hasStructure())
                    Blocks[i][j].setBorder(BorderFactory.createLineBorder(borderColor));
            }
        }
    }

    public boolean checkRadius(Block block1, Block block2) {
        if ((block1.getPosition().getX() > block2.getPosition().getX() + 1) || block1.getPosition().getX() < block2.getPosition().getX() - 1)
            return false;
        else if ((block1.getPosition().getY() > block2.getPosition().getY() + 1) || block1.getPosition().getY() < block2.getPosition().getY() - 1)
            return false;
        else
            return true;
    }

    public void moveUnit(Block block1, Block block2) {
        block2.setUnit(block1.getUnit());
        block2.getUnit().setBlock(block2);
        gameController.setAttackingBlock(block2);
        gameController.removeAttackingBlock(block1);
        block1.removeUnit();
        block1.iconSetCheck();
        block2.iconSetCheck();
    }

    public boolean isInRangePosition(int q) {
        return (q >= 0 && q <= 9);
    }

    public void reset() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Blocks[i][j].removeUnit();
                Blocks[i][j].structureDied();
            }
        }
    }
}