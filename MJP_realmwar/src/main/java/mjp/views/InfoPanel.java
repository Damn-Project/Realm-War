package mjp.views;

import mjp.models.Player;
import mjp.models.blocks.Block;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private BlockPanel blockPanel;
    private ActionPanel actionPanel;
    JPanel upPanel;
    GridBagConstraints upGBC;
    JPanel downPanel;
    GridBagConstraints downGBC;
    Color color = Color.BLACK;

    public ActionPanel getActionPanel() {
        return actionPanel;
    }

    public void setActionPanel(ActionPanel actionPanel) {
        this.actionPanel = actionPanel;
    }

    public void setBlockPanel(BlockPanel blockPanel) {
        this.blockPanel = blockPanel;
    }

    public BlockPanel getBlockPanel() {
        return blockPanel;
    }

    public InfoPanel() {
        setLayout(new GridLayout(2, 1));
        setPreferredSize(new Dimension(200, 550));
        setBackground(new Color(200, 200, 200));

        upPanel = new JPanel(new GridBagLayout());
        upPanel.setBackground(Color.GRAY);
        upGBC = new GridBagConstraints();
        upGBC.insets = new Insets(10, 10, 10, 10);

        downPanel = new JPanel(new GridBagLayout());
        downPanel.setBackground(Color.GRAY);
        downGBC = new GridBagConstraints();
        downGBC.insets = new Insets(10, 10, 10, 10);

        this.add(upPanel);
        this.add(downPanel);
    }

    public void setVoidBlockText() {
        downPanel.removeAll();
        downGBC.gridx = 1;
        downGBC.gridy = 1;
        JLabel voidBlock = new JLabel();
        voidBlock.setForeground(Color.BLACK);
        voidBlock.setText("Void Block !");
        downPanel.add(voidBlock, downGBC);
        downPanel.revalidate();
        downPanel.repaint();
    }

    public void setBlockInfo(Block button) {
        downPanel.removeAll();

        Color buttonInfo;
        blockPanel.removeBorder();

        if (button.hasUnit() || button.hasStructure()) {
            if (button.hasUnit()) {
                buttonInfo = button.getUnit().getKingdom().getMyColor();
            }else
                buttonInfo = button.getStructure().getKingdom().getMyColor();
        }else buttonInfo = color;

        button.setBorder(BorderFactory.createLineBorder(buttonInfo));
        downGBC.gridy = 0;
        downGBC.gridx = 1;
        JLabel blockName = new JLabel();
        blockName.setForeground(buttonInfo);
        blockName.setText(button.getClass().getSimpleName() + "!");
        downPanel.add(blockName, downGBC);

        if (button.getStructure() != null) {
            downGBC.gridy = 1;
            downGBC.gridx = 1;
            JLabel structureName = new JLabel();
            structureName.setForeground(buttonInfo);
            structureName.setText(button.getStructure().getClass().getSimpleName() + "  " + button.getStructure().getLevel());
            downPanel.add(structureName, downGBC);
        }

        if (button.getUnit() != null) {
            downGBC.gridy = 2;
            downGBC.gridx = 1;
            JLabel unitName = new JLabel();
            unitName.setForeground(buttonInfo);
            unitName.setText(button.getUnit().getClass().getSimpleName() + "  " + button.getUnit().getLevel());
            downPanel.add(unitName, downGBC);
        }


        downPanel.revalidate();
        downPanel.repaint();
    }

    public void setPlayerInfo(Player player) {
        upPanel.removeAll();

        upGBC.gridy = 0;
        upGBC.gridx = 1;
        JLabel playerName = new JLabel();
        playerName.setForeground(color);
        playerName.setText(player.getName());
        upPanel.add(playerName, upGBC);

        upGBC.gridy = 1;
        upGBC.gridx = 1;
        JLabel playerColor = new JLabel();
        playerColor.setForeground(player.getKingdom().getMyColor());
        playerColor.setText("color");
        upPanel.add(playerColor, upGBC);

        upGBC.gridy = 2;
        upGBC.gridx = 1;
        JLabel playerFood = new JLabel();
        playerFood.setForeground(color);
        playerFood.setText("food " + player.getKingdom().getFood());
        upPanel.add(playerFood, upGBC);

        upGBC.gridy = 3;
        upGBC.gridx = 1;
        JLabel playerCoin = new JLabel();
        playerCoin.setForeground(color);
        playerCoin.setText("coin " + player.getKingdom().getGold());
        upPanel.add(playerCoin, upGBC);

        upPanel.revalidate();
        upPanel.repaint();
    }
}