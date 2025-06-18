package mjp.controllers;

import mjp.models.Kingdom;
import mjp.models.Player;
import mjp.models.blocks.Block;
import mjp.models.structures.Farm;
import mjp.models.structures.TownHall;
import mjp.views.GameFrame;

import java.util.ArrayList;

public class GameController {
    GameFrame frame;
    ArrayList<Player> players = new ArrayList<>();

    public GameController() {
        frame = new GameFrame();
        frame.menuPanel.setGameController(this);
        frame.blockPanel.setGameController(this);


        frame.setVisible(true);
    }

    public void makePlayers(int playerCount, ArrayList<String> playerNames) {
        for (int i = 0; i < playerCount; i++) {
            Kingdom kingdom = new Kingdom();
            Player player = new Player(playerNames.get(i), kingdom);
            kingdom.setPlayer(player);
            this.players.add(player);
        }
    }

    public void logPlayers() {
        Block[][] blocks = frame.blockPanel.getBlocks();
        for (int i = 0; i < players.size(); i++) {
            switch (i) {
                case 0: {
                    TownHall townHall1 = new TownHall(blocks[0][4], players.get(i).getKingdom());
                    TownHall townHall2 = new TownHall(blocks[0][5], players.get(i).getKingdom());
                    Farm farm1 = new Farm(blocks[0][3], players.get(i).getKingdom());
                    Farm farm2 = new Farm(blocks[0][6], players.get(i).getKingdom());
                    players.get(i).getKingdom().setStructure(farm1);
                    players.get(i).getKingdom().setStructure(farm2);
                    blocks[0][3].setStructure(farm1);
                    blocks[0][6].setStructure(farm2);
                    players.get(i).getKingdom().setTownHall(townHall1, townHall2);
                    blocks[0][4].setStructure(townHall1);
                    blocks[0][5].setStructure(townHall2);
                    break;
                }
                case 1: {
                    TownHall townHall1 = new TownHall(blocks[9][4], players.get(i).getKingdom());
                    TownHall townHall2 = new TownHall(blocks[9][5], players.get(i).getKingdom());
                    Farm farm1 = new Farm(blocks[9][3], players.get(i).getKingdom());
                    Farm farm2 = new Farm(blocks[9][6], players.get(i).getKingdom());
                    players.get(i).getKingdom().setStructure(farm1);
                    players.get(i).getKingdom().setStructure(farm2);
                    blocks[9][3].setStructure(farm1);
                    blocks[9][6].setStructure(farm2);
                    players.get(i).getKingdom().setTownHall(townHall1, townHall2);
                    blocks[9][4].setStructure(townHall1);
                    blocks[9][5].setStructure(townHall2);
                    break;
                }
                case 2: {
                    TownHall townHall1 = new TownHall(blocks[4][0], players.get(i).getKingdom());
                    TownHall townHall2 = new TownHall(blocks[5][0], players.get(i).getKingdom());
                    Farm farm1 = new Farm(blocks[3][0], players.get(i).getKingdom());
                    Farm farm2 = new Farm(blocks[6][0], players.get(i).getKingdom());
                    players.get(i).getKingdom().setStructure(farm1);
                    players.get(i).getKingdom().setStructure(farm2);
                    blocks[3][0].setStructure(farm1);
                    blocks[6][0].setStructure(farm2);
                    players.get(i).getKingdom().setTownHall(townHall1, townHall2);
                    blocks[4][0].setStructure(townHall1);
                    blocks[5][0].setStructure(townHall2);
                    break;
                }
                case 3: {
                    TownHall townHall1 = new TownHall(blocks[4][9], players.get(i).getKingdom());
                    TownHall townHall2 = new TownHall(blocks[5][9], players.get(i).getKingdom());
                    Farm farm1 = new Farm(blocks[3][9], players.get(i).getKingdom());
                    Farm farm2 = new Farm(blocks[6][9], players.get(i).getKingdom());
                    players.get(i).getKingdom().setStructure(farm1);
                    players.get(i).getKingdom().setStructure(farm2);
                    blocks[3][9].setStructure(farm1);
                    blocks[6][9].setStructure(farm2);
                    players.get(i).getKingdom().setTownHall(townHall1, townHall2);
                    blocks[4][9].setStructure(townHall1);
                    blocks[5][9].setStructure(townHall2);
                }
            }
        }
    }

    public boolean isEnd() {
        return players.size() == 1;
    }
}
