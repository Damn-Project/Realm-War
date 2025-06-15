package mjp.views;

import mjp.controllers.GameController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import static java.awt.Color.*;

public class MenuPanel extends JPanel {
    private JButton newGameButton;
    private JButton loadGameButton;
    private JButton exitButton;
    JPanel centerPAnel;
    public int playerCount;
    private ArrayList<String> playerNames;
    public GameFrame gameFrame;
    GameController gameController;

    public MenuPanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        initialize();
    }

    public void initialize() {
        this.removeAll();
        setLayout(new GridLayout(2, 1));
        centerPAnel = new JPanel();
        centerPAnel.setBackground(Color.BLUE);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(WHITE);
        formPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

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

        formPanel.add(centerPAnel);

        ImageIcon icon3 = null;
        try {
            ImageIcon icon = new ImageIcon("/home/sopoyan/Documents/pooyan folder/wall paper/metro_exodus_wallpaper_by_da_gamecovers_dgeg2dp.jpg");
            Image icon2;
            icon2 = icon.getImage().getScaledInstance(650, 320, Image.SCALE_SMOOTH);
            icon3 = new ImageIcon(icon2);
        } catch (Exception e) {
            System.out.println("way");
        }
        JLabel imageLabel = new JLabel(icon3);
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout());
        imagePanel.add(imageLabel);

        add(imagePanel);
        add(formPanel);


        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        newGameButton.addActionListener(e -> {
            createAndShowStartPanel();
        });

        this.revalidate();
        this.repaint();
    }

    public void createAndShowStartPanel() {
        this.removeAll();
        this.setLayout(new BorderLayout());
        // First panel - Player count selection
        JPanel countPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Select Number of Players:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        countPanel.add(titleLabel, gbc);

        ButtonGroup group = new ButtonGroup();
        JRadioButton[] countButtons = new JRadioButton[4];

        for (int i = 1; i < 4; i++) {
            countButtons[i] = new JRadioButton((i + 1) + " Player");
            group.add(countButtons[i]);
            gbc.gridy = i + 1;
            gbc.gridwidth = 1;
            countPanel.add(countButtons[i], gbc);
        }
        countButtons[1].setSelected(true); // Default to 1 player

        JButton nextButton = new JButton("Next");
        nextButton.setBackground(GRAY);
        nextButton.setForeground(BLACK);
        gbc.gridy = 5;
        gbc.gridwidth = 5;
        countPanel.add(nextButton, gbc);

        nextButton.addActionListener(e -> {
            for (int i = 1; i < 4; i++) {
                if (countButtons[i].isSelected()) {
                    playerCount = i + 1;
                    break;
                }
            }
            showNameInputDialog();
        });

        JButton backButton = new JButton("back");
        backButton.setBackground(GRAY);
        backButton.setForeground(BLACK);
        gbc.gridy = 6;
        gbc.gridwidth = 5;
        countPanel.add(backButton, gbc);

        backButton.addActionListener(e -> {
            initialize();
        });

        this.add(countPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    private void showNameInputDialog() {
        this.removeAll();
        this.setLayout(new BorderLayout());

        JPanel namePanel = new JPanel(new GridLayout(playerCount + 3, 1, 10, 10));
        namePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel instructionLabel = new JLabel("Enter Player Names:");
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        namePanel.add(instructionLabel);

        JTextField[] nameFields = new JTextField[playerCount];
        for (int i = 0; i < playerCount; i++) {
            JPanel fieldPanel = new JPanel(new BorderLayout());
            JLabel playerLabel = new JLabel("Player " + (i + 1) + ":");
            nameFields[i] = new JTextField(20);
            fieldPanel.add(playerLabel, BorderLayout.WEST);
            fieldPanel.add(nameFields[i], BorderLayout.CENTER);
            namePanel.add(fieldPanel);
        }

        JButton startButton = new JButton("Start Game");
        startButton.setBackground(GRAY);
        startButton.setForeground(BLACK);
        startButton.addActionListener(e -> {
            playerNames = new ArrayList<>();
            for (JTextField field : nameFields) {
                String name = field.getText().trim();
                playerNames.add(name.isEmpty() ? "Player " + (playerNames.size() + 1) : name);
            }
            gameController.makePlayers(playerCount, playerNames);
            gameFrame.showGamePanel();
            gameController.logPlayers();
        });

        JButton backButton = new JButton("back");
        backButton.setBackground(GRAY);
        backButton.setForeground(BLACK);
        backButton.addActionListener(e -> {
            createAndShowStartPanel();
        });

        namePanel.add(startButton);
        namePanel.add(backButton);
        this.add(namePanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
