package mjp.controllers;

import mjp.views.GameFrame;

public class GameController {
    GameFrame frame;

    public GameController() {
        frame = new GameFrame();
        frame.setVisible(true);
    }
}
