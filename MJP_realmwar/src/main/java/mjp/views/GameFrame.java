package mjp.views;

import mjp.models.Player;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public JPanel mainPanel;
    public GamePanel gamePanel;
    public BlockPanel blockPanel;
    public MenuPanel menuPanel;
    public InfoPanel infoPanel;
    public CardLayout cardLayout;
    public ActionPanel actionPanel;


    public GameFrame() {
        initializeFrame();
        setStates();
    }

    public void initializeFrame() {
        setTitle("REALM WAR");
        setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 550);
        setLocationRelativeTo(null);
        setResizable(true);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);
    }

    public void setStates() {
        gamePanel = new GamePanel();
        actionPanel = new ActionPanel();
        blockPanel = new BlockPanel();
        menuPanel = new MenuPanel(this);
        infoPanel = new InfoPanel();

        gamePanel.add(blockPanel, BorderLayout.CENTER);
        gamePanel.add(actionPanel, BorderLayout.SOUTH);
        gamePanel.add(infoPanel, BorderLayout.EAST);

        mainPanel.add(gamePanel, "GamePanel");
        mainPanel.add(menuPanel, "MenuPanel");
        cardLayout.show(mainPanel, "MenuPanel");

    }

    public void showGamePanel() {
        cardLayout.show(mainPanel, "GamePanel");
        this.revalidate();
        this.repaint();
    }

    public void showMassageSelectBlock() {
        JOptionPane.showMessageDialog(this, "choose a block !");
    }

    public void showMassageHasNoStrORUnit() {
        JOptionPane.showMessageDialog(this, "this Block has no unit or structure to upgrade !");
    }

    public void showLackOfCoinMassage() {
        JOptionPane.showMessageDialog(this, "lack of money !");
    }

    public void showMessageInOtherBlock() {
        JOptionPane.showMessageDialog(this, "select your own block!");
    }

    public void showMessageSelectYourOwnUnit() {
        JOptionPane.showMessageDialog(this, "select your own unit !");
    }

    public void showMessageHasNoUnit() {
        JOptionPane.showMessageDialog(this, "select block witch has unit !");
    }

    public void showMessageOnYourPosition() {
        JOptionPane.showMessageDialog(this, "you have selected your own position !");
    }

    public void showMessageOutOfRadius() {
        JOptionPane.showMessageDialog(this, "out of unit moving area !");
    }

    public void showMessageHasUnit() {
        JOptionPane.showMessageDialog(this, "this block already has unit !");
    }

    public void showMessageOthersStruct() {
        JOptionPane.showMessageDialog(this, "you can't stay on other's structure !");
    }

    public void showMessageCanNotMakeStructureOnOthersUnit() {
        JOptionPane.showMessageDialog(this, "suddenly this block is placed by other's unit !");
    }

    public void showMessageLAckOfFood(String name) {
        JOptionPane.showMessageDialog(this, "lack of food for " + name + " !");
    }

    public void showMessageYouWin(Player players) {
        JOptionPane.showMessageDialog(this, "************ " + players.getName() + " win !  ***********");
    }
}