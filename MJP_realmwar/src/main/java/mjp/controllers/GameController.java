package mjp.controllers;

import mjp.models.Kingdom;
import mjp.models.Player;
import mjp.views.GameFrame;

import java.util.ArrayList;

public class GameController {
    GameFrame frame;
    ArrayList<Player> players;

    public GameController() {
        frame = new GameFrame();
        frame.menuPanel.setGameController(this);



        frame.setVisible(true);
    }

    public void makePlayers(int playerCount, ArrayList<String> playerNames) {
        for (int i = 0; i < playerCount; i++) {
            Kingdom kingdom = new Kingdom();
            Player player = new Player(playerNames.get(i), kingdom);
            this.players.add(player);
        }
    }
}
