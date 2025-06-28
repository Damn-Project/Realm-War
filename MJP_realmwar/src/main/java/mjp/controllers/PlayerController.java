package mjp.controllers;

import mjp.models.Kingdom;
import mjp.models.Player;
import mjp.models.blocks.Block;
import mjp.models.structures.Farm;
import mjp.models.structures.TownHall;

import java.util.ArrayList;

public class PlayerController {
    GameController gameController;

    PlayerController(GameController gameController) {
        this.gameController = gameController;
    }

    public void logPlayers() {
        Block[][] blocks = gameController.getFrame().blockPanel.getBlocks();
        for (int i = 0; i < gameController.getPlayers().size(); i++) {
            switch (i) {
                case 0: {
                    TownHall townHall1 = new TownHall(blocks[0][4], gameController.getPlayers().get(i).getKingdom());
                    TownHall townHall2 = new TownHall(blocks[0][5], gameController.getPlayers().get(i).getKingdom());
                    Farm farm1 = new Farm(blocks[0][3], gameController.getPlayers().get(i).getKingdom());
                    Farm farm2 = new Farm(blocks[0][6], gameController.getPlayers().get(i).getKingdom());
                    gameController.getPlayers().get(i).getKingdom().setStructure(farm1);
                    gameController.getPlayers().get(i).getKingdom().setStructure(farm2);
                    blocks[0][3].setStructure(farm1);
                    blocks[0][6].setStructure(farm2);
                    gameController.getPlayers().get(i).getKingdom().setTownHall(townHall1, townHall2);
                    blocks[0][4].setStructure(townHall1);
                    blocks[0][5].setStructure(townHall2);
                    break;
                }
                case 1: {
                    TownHall townHall1 = new TownHall(blocks[9][4], gameController.getPlayers().get(i).getKingdom());
                    TownHall townHall2 = new TownHall(blocks[9][5], gameController.getPlayers().get(i).getKingdom());
                    Farm farm1 = new Farm(blocks[9][3], gameController.getPlayers().get(i).getKingdom());
                    Farm farm2 = new Farm(blocks[9][6], gameController.getPlayers().get(i).getKingdom());
                    gameController.getPlayers().get(i).getKingdom().setStructure(farm1);
                    gameController.getPlayers().get(i).getKingdom().setStructure(farm2);
                    blocks[9][3].setStructure(farm1);
                    blocks[9][6].setStructure(farm2);
                    gameController.getPlayers().get(i).getKingdom().setTownHall(townHall1, townHall2);
                    blocks[9][4].setStructure(townHall1);
                    blocks[9][5].setStructure(townHall2);
                    break;
                }
                case 2: {
                    TownHall townHall1 = new TownHall(blocks[4][0], gameController.getPlayers().get(i).getKingdom());
                    TownHall townHall2 = new TownHall(blocks[5][0], gameController.getPlayers().get(i).getKingdom());
                    Farm farm1 = new Farm(blocks[3][0], gameController.getPlayers().get(i).getKingdom());
                    Farm farm2 = new Farm(blocks[6][0], gameController.getPlayers().get(i).getKingdom());
                    gameController.getPlayers().get(i).getKingdom().setStructure(farm1);
                    gameController.getPlayers().get(i).getKingdom().setStructure(farm2);
                    blocks[3][0].setStructure(farm1);
                    blocks[6][0].setStructure(farm2);
                    gameController.getPlayers().get(i).getKingdom().setTownHall(townHall1, townHall2);
                    blocks[4][0].setStructure(townHall1);
                    blocks[5][0].setStructure(townHall2);
                    break;
                }
                case 3: {
                    TownHall townHall1 = new TownHall(blocks[4][9], gameController.getPlayers().get(i).getKingdom());
                    TownHall townHall2 = new TownHall(blocks[5][9], gameController.getPlayers().get(i).getKingdom());
                    Farm farm1 = new Farm(blocks[3][9], gameController.getPlayers().get(i).getKingdom());
                    Farm farm2 = new Farm(blocks[6][9], gameController.getPlayers().get(i).getKingdom());
                    gameController.getPlayers().get(i).getKingdom().setStructure(farm1);
                    gameController.getPlayers().get(i).getKingdom().setStructure(farm2);
                    blocks[3][9].setStructure(farm1);
                    blocks[6][9].setStructure(farm2);
                    gameController.getPlayers().get(i).getKingdom().setTownHall(townHall1, townHall2);
                    blocks[4][9].setStructure(townHall1);
                    blocks[5][9].setStructure(townHall2);
                }
            }
        }
    }


    public void makePlayers(int playerCount, ArrayList<String> playerNames) {
        for (int i = 0; i < playerCount; i++) {
            Kingdom kingdom = new Kingdom();
            Player player = new Player(playerNames.get(i), kingdom);
            kingdom.setPlayer(player);
            gameController.getPlayers().add(player);
        }

        gameController.startGame();

    }

}
