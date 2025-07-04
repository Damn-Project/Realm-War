package mjp;


import mjp.controllers.GameController;
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;

import javax.swing.*;

public class Main{

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameController::new);
    }
}
// ...pooyan bayr...
// ...tahala enghadr tang dide boodi ? ...