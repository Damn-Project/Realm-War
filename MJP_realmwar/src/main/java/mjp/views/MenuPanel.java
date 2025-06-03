package mjp.views;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private JButton newGameButton;
    private JButton loadGameButton;
    private JButton exitButton;

    public MenuPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        newGameButton = new JButton("New Game");
        loadGameButton = new JButton("Load Game");
        exitButton = new JButton("Exit");

        add(newGameButton);
        add(loadGameButton);
        add(exitButton);
    }

    public JButton getNewGameButton() {
        return newGameButton;
    }

    public JButton getLoadGameButton() {
        return loadGameButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }
}
