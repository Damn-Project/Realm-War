package mjp.controllers;

import mjp.models.Kingdom;
import mjp.models.Player;
import mjp.models.blocks.Block;
import mjp.models.structures.Farm;
import mjp.models.structures.Structure;
import mjp.models.structures.Tower;
import mjp.models.units.Knight;
import mjp.models.units.Spearman;
import mjp.models.units.Swordman;
import mjp.models.units.Unit;
import mjp.utils.GameLogger;
import mjp.views.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class GameController {
    GameLogger gameLogger;
    PlayerController playerController;
    Unitcontroller unitcontroller;
    StructureController structureController;
    private final GameFrame frame;
    private MenuPanel menuPanel;
    private InfoPanel infoPanel;
    private ActionPanel actionPanel;
    private BlockPanel blockPanel;
    private ArrayList<Player> players = new ArrayList<>();
    private Block selected1;
    private Block selected2;
    Queue<Player> turn = new LinkedList<>();

    Player onTurn;
    private boolean moveButtonSelected;
    private ArrayList<Block> attackingBlocks;
    public Timer attackTimer;
    public Timer endTurnTimer;
    Boolean barrackWork = false;
    boolean gameIsRunning = false;

    public void setMoveButtonSelected(boolean moveButtonSelected) {
        this.moveButtonSelected = moveButtonSelected;
    }

    public GameFrame getFrame() {
        return frame;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Block getSelected1() {
        return selected1;
    }

    public Block getSelected2() {
        return selected2;
    }


    public void setSelected1(Block selected) {
        if (moveButtonSelected) {
            setSelected2(selected);
            moveUnits2(getSelected2());
        } else {
            this.selected1 = selected;
        }
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public Unitcontroller getUnitcontroller() {
        return unitcontroller;
    }

    public StructureController getStructureController() {
        return structureController;
    }

    public void setSelected2(Block selected2) {
        this.selected2 = selected2;
    }

    public ArrayList<Block> getAttackingBlocks() {
        return attackingBlocks;
    }

    public void setAttackingBlock(Block attackingBlock) {
        attackingBlocks.add(attackingBlock);
    }

    public void removeAttackingBlock(Block block) {
        attackingBlocks.remove(block);
    }

    public GameController() {
        frame = new GameFrame();
        gameLogger = new GameLogger(this);
        playerController = new PlayerController(this);
        unitcontroller = new Unitcontroller(this);
        structureController = new StructureController(this);
        attackingBlocks = new ArrayList<>();
        attackTimer = new Timer(4000, e -> {
            attackOnTimer();
        });
        endTurnTimer = new Timer(30000, e -> {
            endTurn();
        });
        attackTimer.setRepeats(true);
        attackTimer.start();
        endTurnTimer.setRepeats(true);
        endTurnTimer.start();
        setPanels();
        interAction();

        frame.setVisible(true);
    }

    public void setPanels() {
        menuPanel = frame.menuPanel;
        infoPanel = frame.infoPanel;
        actionPanel = frame.actionPanel;
        blockPanel = frame.blockPanel;
    }

    public void interAction() {
        menuPanel.setGameController(this);
        blockPanel.setGameController(this);
        blockPanel.setInfoPanel(infoPanel);
        infoPanel.setBlockPanel(blockPanel);
        blockPanel.setActionPanel(actionPanel);
        actionPanel.setBlockPanel(blockPanel);
        actionPanel.setGameController(this);
        actionPanel.setInfoPanel(infoPanel);
        infoPanel.setActionPanel(actionPanel);
        infoPanel.setGameController(this);
        infoPanel.setMenuPanel(menuPanel);
        actionPanel.setGameFrame(frame);
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    public GameLogger getGameLogger() {
        return gameLogger;
    }

    public void isEnd() {
        if (players.size() == 1) {
            frame.showMessageYouWin(players.get(0));
            endTurnTimer.stop();
            attackTimer.stop();
        }
    }

    public void moveUnits2(Block block) {
        if (block.hasUnit()) {
            frame.showMessageHasUnit();
        } else if (block.hasStructure()) {
            if (!block.getStructure().getKingdom().getPlayer().equals(onTurn)) {
                frame.showMessageOthersStruct();
            } else {
                blockPanel.moveUnit(getSelected1(), getSelected2());
            }
        } else if (block.getPosition().getY() == selected1.getPosition().getY() && block.getPosition().getX() == selected1.getPosition().getX()) {
            frame.showMessageOnYourPosition();
        } else if (!blockPanel.checkRadius(selected1, selected2)) {
            frame.showMessageOutOfRadius();
        } else {
            blockPanel.moveUnit(getSelected1(), getSelected2());
        }
        setMoveButtonSelected(false);
    }

    public void startGame() {
        turn = new LinkedList<Player>();
        turn.addAll(players);
        endTurn();
//        startTimer();
    }

    public Player getOnTurn() {
        return onTurn;
    }

    public void endTurn() {
        if (turn != null) {
            onTurn = turn.poll();
            turn.add(onTurn);
            infoPanel.setPlayerInfo(onTurn);
            makeFoodInEndTurn();
            decreaseFoodOnEndTurn();
            makeUnitInBarrack();
            addIncomeInEndTurn();
        }
        endTurnTimer.restart();
    }

    // ************************   should use in game   ***********************
    public void attackOnTimer() {
        witchOfNeighborsAreEnemy();
        Random randomInt = new Random();
        for (Block b : attackingBlocks) {
            if (b.hasStructure()) {
                for (Block target : b.getEnemies()) {
                    if (target.hasUnit()) {
                        attackTowerToUnit(b, target);
                    }
                }
            }
            if (b.hasUnit()) {
                if (!b.getEnemies().isEmpty()) {
                    int targetInt = randomInt.nextInt(0, b.getEnemies().size());
                    if (b.getEnemies().get(targetInt).hasStructure()) {
                        attackUnitToStruct(b, b.getEnemies().get(targetInt));
                    } else if (b.getEnemies().get(targetInt).hasUnit()) {
                        attackUnitToUnit(b, b.getEnemies().get(targetInt));
                    }
                }
            }
        }
        checkAlive();
    }

    public void witchOfNeighborsAreEnemy() {
        for (Block b : getAttackingBlocks()) {
            b.setEnemies();
        }
    }

    public void attackUnitToUnit(Block attacking, Block attacked) {
        if (attacked.hasUnit()) {
            attacked.getUnit().decreaseHealth(attacking.getUnit().attack());
        }
//        checkAlive();
    }

    public void attackUnitToStruct(Block attacking, Block attacked) {
        if (attacked.hasStructure()) {
            attacked.getStructure().decreaseHealth(attacking.getUnit().attack());
        }
//        checkAlive();
    }

    public void attackTowerToUnit(Block attacking, Block attacked) {
        if (attacked.hasUnit()) {
            Tower tower = (Tower) attacking.getStructure();
            attacked.getUnit().decreaseHealth(tower.attack());
        }
//        checkAlive();
    }

    public void decreaseFoodOnEndTurn() {
        for (Player p : players) {
            int food = 0;
            for (Unit u : p.getKingdom().getUnits()) {
                food += u.getFoodCost();
            }
            if (!p.getKingdom().decreaseFood(food)) {
                p.getKingdom().penalty();
                frame.showMessageLAckOfFood(p.getName());
            }
        }
    }

    public void makeFoodInEndTurn() {
        for (Player p : players) {
            int food = 0;
            for (Structure s : p.getKingdom().getStructures()) {
                if (s.getClass().getSimpleName().equalsIgnoreCase("Farm")) {
                    Farm f = (Farm) s;
                    food += f.getMakeFood();
                }
            }
            p.getKingdom().addFood(food);
        }
    }

    public void checkAlive() {
        unitcontroller.unitsCheckAlive();
        structureController.structuresCheckAlive();
    }

    public void makeUnitInBarrack() {
        if (!barrackWork) {
            barrackWork = true;
        } else {
            barrackWork = false;
            for (Player p : players) {
                for (Structure s : p.getKingdom().getStructures()) {
                    if (s.getClass().getSimpleName().equalsIgnoreCase("Barrack")) {
                        if (s.getBlock().getFreeBlockInNeighbors() != null) {
                            switch (s.getLevel()) {
                                case 1: {
                                    Spearman spearman = new Spearman(s.getBlock().getFreeBlockInNeighbors(), p.getKingdom());
                                    unitcontroller.makeUnit(spearman, p, s.getBlock().getFreeBlockInNeighbors());
                                    break;
                                }
                                case 2: {
                                    Swordman swordman = new Swordman(s.getBlock().getFreeBlockInNeighbors(), p.getKingdom());
                                    unitcontroller.makeUnit(swordman, p, s.getBlock().getFreeBlockInNeighbors());
                                    break;
                                }
                                case 3: {
                                    Knight knight = new Knight(s.getBlock().getFreeBlockInNeighbors(), p.getKingdom());
                                    unitcontroller.makeUnit(knight, p, s.getBlock().getFreeBlockInNeighbors());
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void addIncomeInEndTurn() {
        for (Player p : players) {
            p.getKingdom().addIncome();
        }
    }

    public boolean getGameIsRunning() {
        return gameIsRunning;
    }

    public void setPlayersUndo() {
        this.players = new ArrayList<>();
    }

    public void reset() {
        getFrame().blockPanel.reset();
        if (getFrame().blockPanel.getBlocks()[0][0].getNeighbors().isEmpty()) {
            getFrame().blockPanel.setBlocksNeighbors();
        }
        getFrame().infoPanel.reset();
        attackingBlocks = new ArrayList<>();
        attackTimer.start();
        endTurnTimer.start();
        if (!gameIsRunning)
            gameIsRunning = true;
    }

    public void fillAttributes() {
        fillForPlayers();
        fillForKingdoms();
        fillForStructures();
        fillForUnits();
    }

    public void fillForPlayers() {
        for (Player p : Player.getPlayers()) {
            for (Kingdom k : Kingdom.getKingdoms()) {
                if (p.getKingdomID() == k.getID()) {
                    p.setKingdom(k);
                    break;
                }
            }

            switch (p.getID()) {
                case 1: {
                    p.setColor(Color.BLUE);
                }
                case 2: {
                    p.setColor(Color.RED);
                }
                case 3: {
                    p.setColor(Color.GREEN);
                }
                case 4: {
                    p.setColor(Color.YELLOW);
                }
            }
            turn.add(p);
            endTurn();
            infoPanel.setPlayerInfo(onTurn);
        }
    }

    public void fillForKingdoms() {
        for (Kingdom k : Kingdom.getKingdoms()) {

            for (Player p : Player.getPlayers()) {
                if (k.getPlayerID() == p.getID()) {
                    k.setPlayer(p);
                    k.setMyColor(p.getColor());
                    break;
                }
            }

            for (Structure s : Structure.getStructures()) {
                if (k.getStructuresID().contains(s.getID())) {
                    k.setStructure(s);
                }
            }

            for (Unit u : Unit.getUnits()) {
                if (k.getUnitsID().contains(u.getID())) {
                    k.setUnit(u);
                }
            }

            k.setPositions();
            switch (k.getID()) {
                case 1: {
                    k.setMyColor(Color.BLUE);
                }
                case 2: {
                    k.setMyColor(Color.RED);
                }
                case 3: {
                    k.setMyColor(Color.GREEN);
                }
                case 4: {
                    k.setMyColor(Color.YELLOW);
                }
            }
        }
    }

    public void fillForStructures() {
        for (Structure s : Structure.getStructures()) {

            for (Kingdom k : Kingdom.getKingdoms()) {
                if (s.getKingdomID() == k.getID()) {
                    s.setKingdom(k);
                    break;
                }
            }

            Block[][] blocks = BlockPanel.staticGetBlocks();
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (s.getBlockID() == blocks[i][j].getID()) {
                        s.setBlock(blocks[i][j]);
                        blocks[i][j].setStructure(s);
                        s.setPosition(blocks[i][j].getPosition());
                        break;
                    }
                }
            }
            System.gc();
            s.makeLoader();

            if (s.getClass().getSimpleName().equalsIgnoreCase("Tower"))
                setAttackingBlock(s.getBlock());
        }
    }

    public void fillForUnits() {
        for (Unit u : Unit.getUnits()) {

            for (Kingdom k : Kingdom.getKingdoms()) {
                if (u.getKingdomID() == k.getID()) {
                    u.setKingdom(k);
                    break;
                }
            }

            Block[][] blocks = BlockPanel.staticGetBlocks();
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (u.getBlockID() == blocks[i][j].getID()) {
                        u.setBlock(blocks[i][j]);
                        blocks[i][j].setUnit(u);
                        u.setPosition(blocks[i][j].getPosition());
                        break;
                    }
                }
            }
            System.gc();
            u.makeLoader();
            setAttackingBlock(u.getBlock());
        }
    }
}