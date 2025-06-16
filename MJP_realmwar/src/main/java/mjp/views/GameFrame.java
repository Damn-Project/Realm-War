package mjp.views;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public JPanel mainPanel;
    public GamePanel gamePanel;
    public BlockPanel blockPanel;
    public MenuPanel menuPanel;
    public InfoPanel infoPanel;
    public CardLayout cardLayout;
<<<<<<< HEAD

=======
>>>>>>> 97b8f9b465b2de189ac83cdbc68a2366ddec4c52
    ActionPanel actionPanel;



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


}
