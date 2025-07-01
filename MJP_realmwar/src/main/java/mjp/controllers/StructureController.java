package mjp.controllers;

import mjp.models.Player;
import mjp.models.blocks.Block;
import mjp.models.structures.Structure;

import java.util.ArrayList;

public class StructureController {
    GameController gameController;

    StructureController(GameController gameController) {
        this.gameController = gameController;
    }

    public void makeStructure(Structure structure, Player player, Block block) {
        if (block.hasUnit()) {
            if (!block.getUnit().getKingdom().getPlayer().equals(player)) {
                gameController.getFrame().showMessageCanNotMakeStructureOnOthersUnit();
                return;
            }
        }
        block.setStructure(structure);
        player.getKingdom().setStructure(structure);
        block.iconSetCheck();
        if (structure.getClass().getSimpleName().equalsIgnoreCase("Tower"))
            gameController.getAttackingBlocks().add(block);
//        endTurn();
    }

    public void upGradeStructure(Block selected) {
        if (selected.getStructure().getKingdom().getGold() < selected.getStructure().getLevelUpGradeCost())
            gameController.getFrame().showLackOfCoinMassage();
        else {
            selected.getStructure().levelUpgrade();
            gameController.getInfoPanel().setBlockInfo(selected);
            gameController.getInfoPanel().setPlayerInfo(gameController.getOnTurn());
//            endTurn();
        }
    }

    public void structuresCheckAlive() {
        Player diedP = null;
        for (Player p : gameController.getPlayers()) {
            ArrayList<Structure> died = new ArrayList<>();
            for (Structure s : p.getKingdom().getStructures()) {
                if (s.getHealth() <= 0) {
                    s.getBlock().structureDied();
                    died.add(s);
                    if (s.getClass().getSimpleName().equalsIgnoreCase("Tower")) {
                        gameController.getAttackingBlocks().remove(s.getBlock());
                    }
                }
            }
            for (int i = 0; i < died.size(); i++) {
                p.getKingdom().removeStructure(died.get(i));
                if (died.get(i).getClass().getSimpleName().equalsIgnoreCase("townHall")) {
                    if (!p.getKingdom().checkHasTownHall()) {
                        diedP = p;
                        gameController.isEnd();
                    }
                }
            }
        }
        if (diedP != null) {
            gameController.getPlayers().remove(diedP);
            gameController.turn.remove(diedP);
            gameController.isEnd();
        }
    }

}
