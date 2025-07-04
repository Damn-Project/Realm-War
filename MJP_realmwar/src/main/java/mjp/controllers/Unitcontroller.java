package mjp.controllers;

import mjp.models.Player;
import mjp.models.blocks.Block;
import mjp.models.units.Unit;

import java.util.ArrayList;

public class Unitcontroller {
    GameController gameController;

    Unitcontroller(GameController gameController) {
        this.gameController = gameController;
    }

    public void makeUnit(Unit unit, Player player, Block block) {
        block.setUnit(unit);
        player.getKingdom().setUnit(unit);
        block.iconSetCheck();
        gameController.getAttackingBlocks().add(block);
//        endTurn();
        gameController.getInfoPanel().setPlayerInfo(gameController.getOnTurn());
    }

    public void upGradeUnit(Block selected) {
        if (selected.getUnit().getKingdom().getGold() < selected.getUnit().getLevelUpGradeCost())
            gameController.getFrame().showLackOfCoinMassage();
        else {
            selected.getUnit().levelUpgrade();
            gameController.getInfoPanel().setBlockInfo(selected);
            gameController.getInfoPanel().setPlayerInfo(gameController.getOnTurn());
//            endTurn();
        }
    }

    public void unitsCheckAlive() {
        for (Player p : gameController.getPlayers()) {
            ArrayList<Unit> died = new ArrayList<>();
            for (Unit u : p.getKingdom().getUnits()) {
                if (u.getHealth() <= 0) {
                    u.getBlock().unitDied();
                    died.add(u);
                    gameController.getAttackingBlocks().remove(u.getBlock());
                }
            }
            for (int i = 0; i < died.size(); i++) {
                p.getKingdom().removeUnit(died.get(i));
            }
        }
    }

}
