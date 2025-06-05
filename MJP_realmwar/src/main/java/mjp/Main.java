package mjp;


import mjp.views.GameFrame;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameFrame().setVisible(true));
    }
}
