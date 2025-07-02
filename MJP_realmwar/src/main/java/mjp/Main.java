package mjp;


import mjp.controllers.GameController;

import javax.swing.*;

public class Main extends JFrame{

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameController::new); // tabe baraye end turn benevis
    }
}
// ...pooyan bayr...
// ...tahala enghadr tang dide boodi ? ...