package mjp.views;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    JPanel mainPanel;
    GamePanel gamePanel;
    BlockPanel blockPanel;
    MenuPanel menuPanel;
    InfoPanel infoPanel;
    CardLayout cardLayout;


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

       cardLayout = new CardLayout();
       mainPanel = new JPanel(cardLayout);
       add(mainPanel);
   }

   public void setStates() {
       gamePanel = new GamePanel();
       blockPanel = new BlockPanel();
       menuPanel = new MenuPanel();
       infoPanel = new InfoPanel();

       gamePanel.setLayout(new BorderLayout());
       gamePanel.add(blockPanel, BorderLayout.CENTER);

       mainPanel.add(gamePanel, "GamePanel");
       mainPanel.add(menuPanel, "MenuPanel");
       cardLayout.show(mainPanel, "GamePanel");

   }


}
