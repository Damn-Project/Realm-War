package mjp.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;

public class MenuPanel extends JPanel {
    private JButton newGameButton;
    private JButton loadGameButton;
    private JButton exitButton;
    JPanel centerPAnel;

    public MenuPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        centerPAnel = new JPanel();
        centerPAnel.setBackground(Color.BLUE);

        JPanel formPAnel = new JPanel();
        formPAnel.setBackground(WHITE);
        formPAnel.setLayout(new FlowLayout(FlowLayout.CENTER));

        centerPAnel.setLayout(new BoxLayout(centerPAnel, BoxLayout.Y_AXIS));
        centerPAnel.setBackground(WHITE);
        centerPAnel.setBorder(new EmptyBorder(40, 40, 40, 40));
        centerPAnel.setPreferredSize(new Dimension(350, 300));


        newGameButton = new JButton("New Game");
        newGameButton.setBackground(BLACK);
        newGameButton.setForeground(Color.CYAN);
        newGameButton.setFont(new Font("Arial", Font.BOLD, 14));
        newGameButton.setFocusPainted(false);
        newGameButton.setBorder(new EmptyBorder(12, 0, 12, 0));
        newGameButton.setBorder(BorderFactory.createLineBorder(BLACK));
        newGameButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        newGameButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        loadGameButton = new JButton("Load Game");
        loadGameButton.setBackground(BLACK);
        loadGameButton.setForeground(Color.CYAN);
        loadGameButton.setFont(new Font("Arial", Font.BOLD, 14));
        loadGameButton.setFocusPainted(false);
        loadGameButton.setBorder(new EmptyBorder(12, 0, 12, 0));
        loadGameButton.setBorder(BorderFactory.createLineBorder(BLACK));
        loadGameButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        loadGameButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(12, 5));
        exitButton.setBackground(BLACK);
        exitButton.setForeground(Color.CYAN);
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.setFocusPainted(false);
        exitButton.setBorder(new EmptyBorder(12, 0, 12, 0));
        exitButton.setBorder(BorderFactory.createLineBorder(BLACK));
        exitButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        exitButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        centerPAnel.add(newGameButton);
        centerPAnel.add(Box.createVerticalStrut(5));
        centerPAnel.add(loadGameButton);
        centerPAnel.add(Box.createVerticalStrut(5));
        centerPAnel.add(exitButton);

        formPAnel.add(centerPAnel);

        ImageIcon icon3 = null;
        try {
            ImageIcon icon = new ImageIcon("/home/sopoyan/Documents/pooyan folder/wall paper/metro_exodus_wallpaper_by_da_gamecovers_dgeg2dp.jpg");
            Image icon2;
            icon2 = icon.getImage().getScaledInstance(650, 220, Image.SCALE_SMOOTH);
            icon3 = new ImageIcon(icon2);
        } catch (Exception e) {
            System.out.println("way");
        }
        JLabel imageLabel = new JLabel(icon3);
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout());
        imagePanel.add(imageLabel);

        add(imagePanel);
        add(formPAnel);


        exitButton.addActionListener(e -> {
            System.exit(0);
        });
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
