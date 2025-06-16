package mjp;


import mjp.controllers.GameController;
import mjp.views.GameFrame;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameController());
    }
}
// push test
