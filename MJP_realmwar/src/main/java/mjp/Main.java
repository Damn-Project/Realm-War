package mjp;


import mjp.controllers.GameController;
import mjp.views.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main extends JFrame{

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameController::new); // tabe baraye end turn benevis
    }
}
// ...pooyan bayr...