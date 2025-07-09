package mjp.views;

import mjp.controllers.GameController;
import mjp.models.Player;
import mjp.models.blocks.Block;
import mjp.models.structures.Tower;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    static GameController gameController;
    static MenuPanel menuPanel;
    private BlockPanel blockPanel;
    private ActionPanel actionPanel;
    JPanel upPanel;
    GridBagConstraints upGBC;
    JPanel downPanel;
    GridBagConstraints downGBC;
    Color color = Color.BLACK;

    public void setGameController(GameController gameController) {
        InfoPanel.gameController = gameController;
    }

    public void setMenuPanel(MenuPanel menuPanel) {
        InfoPanel.menuPanel = menuPanel;
    }

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

    public void reset() {
        downPanel.removeAll();

        downPanel.repaint();
        downPanel.revalidate();
    }

    public void setBlockInfo(Block button) {
        downPanel.removeAll();

        Color buttonInfo;
        blockPanel.removeBorder();

        if (button.hasUnit() || button.hasStructure()) {
            if (button.hasUnit()) {
                buttonInfo = button.getUnit().getKingdom().getMyColor();
            } else
                buttonInfo = button.getStructure().getKingdom().getMyColor();
        } else buttonInfo = color;

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
            if (button.getStructure().getClass().getSimpleName().equalsIgnoreCase("tower")) {
                downGBC.gridx = 2;
                JLabel damageLabel = new JLabel();
                damageLabel.setForeground(buttonInfo);
                Tower t = (Tower) button.getStructure();
                damageLabel.setText(String.valueOf(t.getDamage()));
                downPanel.add(damageLabel, downGBC);
            }
        }

        if (button.getUnit() != null) {
            downGBC.gridy = 2;
            downGBC.gridx = 1;
            JLabel unitName = new JLabel();
            unitName.setForeground(buttonInfo);
            unitName.setText(button.getUnit().getClass().getSimpleName() + "  " + button.getUnit().getLevel());
            downPanel.add(unitName, downGBC);
            downGBC.gridx = 2;
            JLabel damageLabel = new JLabel();
            damageLabel.setForeground(buttonInfo);
            damageLabel.setText(String.valueOf(button.getUnit().getDamage()));
            downPanel.add(damageLabel, downGBC);
        }


        downPanel.revalidate();
        downPanel.repaint();
    }

    public void setPlayerInfo(Player player) {
        upPanel.removeAll();

        upGBC.gridx = 1;
        upGBC.gridy = 0;
        JPanel buttonPanel = getButtonPanel();
        upPanel.add(buttonPanel, upGBC);


        upGBC.gridy = 1;
        upGBC.gridx = 1;
        JLabel playerName = new JLabel();
        playerName.setForeground(color);
        playerName.setText(player.getName());
        upPanel.add(playerName, upGBC);

        upGBC.gridy = 2;
        upGBC.gridx = 1;
        JLabel playerColor = new JLabel();
        playerColor.setForeground(player.getKingdom().getMyColor());
        playerColor.setText("color");
        upPanel.add(playerColor, upGBC);

        upGBC.gridy = 3;
        upGBC.gridx = 1;
        JLabel playerFood = new JLabel();
        playerFood.setForeground(color);
        playerFood.setText("food " + player.getKingdom().getFood());
        upPanel.add(playerFood, upGBC);

        upGBC.gridy = 4;
        upGBC.gridx = 1;
        JLabel playerCoin = new JLabel();
        playerCoin.setForeground(color);
        if (player.getKingdom().getGold() >= 0)
            playerCoin.setText("coin " + player.getKingdom().getGold());
        else playerCoin.setText("0");
        upPanel.add(playerCoin, upGBC);

        upPanel.revalidate();
        upPanel.repaint();
    }

    private static JPanel getButtonPanel() {
        JButton exitButton = new JButton("Exit");
        exitButton.setForeground(Color.gray);
        exitButton.setBackground(Color.BLACK);
        exitButton.addActionListener(e -> {
            gameController.getGameLogger().saveMatch();
            gameController.endTurnTimer.stop();
            gameController.attackTimer.stop();
            menuPanel.initialize();
            gameController.getFrame().cardLayout.show(gameController.getFrame().mainPanel, "MenuPanel");
        });
        JButton pauseButton = new JButton("Pause");
        pauseButton.setForeground(Color.GRAY);
        pauseButton.setBackground(Color.BLACK);
        pauseButton.addActionListener(e -> {
            gameController.endTurnTimer.stop();
            gameController.attackTimer.stop();
            JFrame createFrame = new JFrame("pause");
            createFrame.setLayout(new GridLayout(2, 1));
            createFrame.setResizable(false);
            createFrame.setSize(200, 200);
            createFrame.setLocationRelativeTo(null);
            createFrame.setBackground(Color.BLACK);

            JLabel infoLabel = new JLabel();
            infoLabel.setBackground(Color.black);
            infoLabel.setForeground(Color.BLUE);
            infoLabel.setText("click button to resume");
            createFrame.add(infoLabel);


            JButton resumeButton = new JButton("resume");
            resumeButton.setForeground(Color.black);
            resumeButton.setBackground(Color.GRAY);
            createFrame.add(resumeButton);
            resumeButton.addActionListener(E -> {
                gameController.attackTimer.start();
                gameController.endTurnTimer.start();
                createFrame.setVisible(false);
            });

            createFrame.setVisible(true);
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.setBackground(Color.black);
        buttonPanel.add(exitButton);
        buttonPanel.add(pauseButton);
        return buttonPanel;
    }
}