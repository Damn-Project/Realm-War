package mjp.views;

import mjp.controllers.GameController;
import mjp.models.blocks.Block;
import mjp.models.structures.Barrack;
import mjp.models.structures.Farm;
import mjp.models.structures.Tower;
import mjp.models.units.Knight;
import mjp.models.units.Spearman;
import mjp.models.units.Swordman;

import javax.swing.*;
import java.awt.*;

public class ActionPanel extends JPanel {
    private GameController gameController;
    private GameFrame gameFrame;
    private BlockPanel blockPanel;
    private InfoPanel infoPanel;

    private final JButton endTurnButton;
    private JButton moveButton;

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    public void setInfoPanel(InfoPanel infoPanel) {
        this.infoPanel = infoPanel;
    }

    public GameController getGameController() {
        return gameController;
    }

    public BlockPanel getBlockPanel() {
        return blockPanel;
    }

    private JButton upgradeButton;
    private JButton createButton;


    Color primaryColor = new Color(30, 30, 30); // مشکی مات
    Color accentColor = new Color(200, 0, 0);   // قرمز
    Color borderColor = new Color(212, 175, 55); // طلایی


    public ActionPanel() {
        // تنظیم Layout به FlowLayout
        //setLayout(new FlowLayout());
        setLayout(new GridLayout(1, 3));
        // ساخت دکمه‌ها
        setPreferredSize(new Dimension(150, 100));
        setBackground(new Color(200, 200, 200));

        endTurnButton = new JButton("EndTurn");
        endTurnButton.setBackground(Color.BLACK);
        endTurnButton.setForeground(Color.BLUE);
        moveButton = new JButton("Move");
        moveButton.setBackground(Color.BLACK);
        moveButton.setForeground(Color.BLUE);
        upgradeButton = new JButton("Upgrade");
        upgradeButton.setBackground(Color.BLACK);
        upgradeButton.setForeground(Color.BLUE);
        createButton = new JButton("Creat");
        createButton.setBackground(Color.BLACK);
        createButton.setForeground(Color.BLUE);

        // افزودن دکمه‌ها به پنل
        add(endTurnButton);
        add(moveButton);
        add(upgradeButton);
        add(createButton);


        createButton.addActionListener(e -> structORunit());
        upgradeButton.addActionListener(e -> upGrade());
        moveButton.addActionListener(e -> moveUnits1(gameController.getSelected1()));
        endTurnButton.addActionListener(e -> gameController.endTurn());

    }

    public void structORunit() {
        if (gameController.getSelected1() == null) {
            gameController.getFrame().showMassageSelectBlock();
            return;
        }
        if (!gameController.getOnTurn().getKingdom().checkInPosition(gameController.getSelected1())) {
            gameFrame.showMessageInOtherBlock();
            return;
        }
        JFrame createFrame = new JFrame("Create structure or unit");
        createFrame.setResizable(false);
        createFrame.setSize(200, 200);
        createFrame.setLocationRelativeTo(null);
        createFrame.setLayout(new BorderLayout());

        JLabel label = new JLabel("Choose the type");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.add(label, BorderLayout.CENTER);

        JPanel radioPanel = new JPanel(new GridLayout(2, 1));
        JRadioButton structure = new JRadioButton("Structure");
        JRadioButton unit = new JRadioButton("Unit");

        ButtonGroup group = new ButtonGroup();
        group.add(structure);
        group.add(unit);

        radioPanel.add(structure);
        radioPanel.add(unit);
        structure.setSelected(true);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton attackButton = new JButton("ok");
        attackButton.addActionListener(e -> {
            if (!gameController.getOnTurn().getKingdom().checkInPosition(gameController.getSelected1())) {
                gameFrame.showMessageTimeIsEnd();
                return;
            }
            if (structure.isSelected()) {
                makeStructureFrame();
            } else {
                makeUnitFrame();
            }
            createFrame.setVisible(false);
        });
        buttonPanel.add(attackButton);


        createFrame.add(labelPanel, BorderLayout.NORTH);
        createFrame.add(radioPanel, BorderLayout.CENTER);
        createFrame.add(buttonPanel, BorderLayout.SOUTH);

        createFrame.setVisible(true);
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setBlockPanel(BlockPanel blockPanel) {
        this.blockPanel = blockPanel;
    }

    public void makeStructureFrame() {
        JFrame createFrame = new JFrame("Create structure");
        createFrame.setResizable(false);
        createFrame.setSize(200, 300);
        createFrame.setLocationRelativeTo(null); // وسط صفحه باز بشه
        createFrame.setLayout(new BorderLayout());

        JLabel label = new JLabel("Choose the type");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.add(label, BorderLayout.CENTER);


        JPanel radioPanel = new JPanel(new GridLayout(3, 1));
        JRadioButton barrack = new JRadioButton("Barrack");
        JRadioButton farm = new JRadioButton("Farm");
        JRadioButton tower = new JRadioButton("Tower");


        ButtonGroup group = new ButtonGroup();
        group.add(barrack);
        group.add(farm);
        group.add(tower);

        radioPanel.add(barrack);
        radioPanel.add(farm);
        radioPanel.add(tower);

        barrack.setSelected(true);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton attackButton = new JButton("ok");
        attackButton.addActionListener(e -> {
            if (!gameController.getOnTurn().getKingdom().checkInPosition(gameController.getSelected1())) {
                gameFrame.showMessageTimeIsEnd();
                return;
            }
            if (barrack.isSelected()) {
                if (gameController.getOnTurn().getKingdom().getGold() < Barrack.createCost) {
                    gameFrame.showLackOfCoinMassage();
                } else {
                    Barrack barrack1 = new Barrack(gameController.getSelected1(), gameController.getOnTurn().getKingdom());
                    gameController.getStructureController().makeStructure(barrack1, gameController.getOnTurn(), gameController.getSelected1());
                }
            } else if (farm.isSelected()) {
                if (gameController.getOnTurn().getKingdom().getGold() < Farm.createCost) {
                    gameFrame.showLackOfCoinMassage();
                } else {
                    Farm farm1 = new Farm(gameController.getSelected1(), gameController.getOnTurn().getKingdom());
                    gameController.getStructureController().makeStructure(farm1, gameController.getOnTurn(), gameController.getSelected1());
                }
            } else {
                if (gameController.getOnTurn().getKingdom().getGold() < Spearman.createCost) {
                    gameFrame.showLackOfCoinMassage();
                } else {
                    Tower tower1 = new Tower(gameController.getSelected1(), gameController.getOnTurn().getKingdom());
                    gameController.getStructureController().makeStructure(tower1, gameController.getOnTurn(), gameController.getSelected1());
                }
            }
            createFrame.setVisible(false);
            gameController.setSelected1(null);
        });
        buttonPanel.add(attackButton);


        createFrame.add(labelPanel, BorderLayout.NORTH);
        createFrame.add(radioPanel, BorderLayout.CENTER);
        createFrame.add(buttonPanel, BorderLayout.SOUTH);


        createFrame.setVisible(true);
    }

    public void makeUnitFrame() {
        JFrame createFrame = new JFrame("Create unit");
        createFrame.setResizable(false);
        createFrame.setSize(200, 300);
        createFrame.setLocationRelativeTo(null);
        createFrame.setLayout(new BorderLayout());

        JLabel label = new JLabel("Choose the type");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.add(label, BorderLayout.CENTER);


        JPanel radioPanel = new JPanel(new GridLayout(3, 1));
        JRadioButton spearMan = new JRadioButton("spearMan");
        JRadioButton swordMan = new JRadioButton("swordMan");
        JRadioButton knight = new JRadioButton("knight");


        ButtonGroup group = new ButtonGroup();
        group.add(spearMan);
        group.add(swordMan);
        group.add(knight);
        spearMan.setSelected(true);

        radioPanel.add(spearMan);
        radioPanel.add(swordMan);
        radioPanel.add(knight);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton attackButton = new JButton("ok");
        attackButton.addActionListener(e -> {
            if (!gameController.getOnTurn().getKingdom().checkInPosition(gameController.getSelected1())) {
                gameFrame.showMessageTimeIsEnd();
                return;
            }
            if (spearMan.isSelected()) {
                if (gameController.getOnTurn().getKingdom().getGold() < Spearman.createCost) {
                    gameFrame.showLackOfCoinMassage();
                } else {
                    Spearman spearman = new Spearman(gameController.getSelected1(), gameController.getOnTurn().getKingdom());
                    gameController.getUnitcontroller().makeUnit(spearman, gameController.getOnTurn(), gameController.getSelected1());
                }
            } else if (swordMan.isSelected()) {
                if (gameController.getOnTurn().getKingdom().getGold() < Swordman.createCost) {
                    gameFrame.showLackOfCoinMassage();
                } else {
                    Swordman swordman = new Swordman(gameController.getSelected1(), gameController.getOnTurn().getKingdom());
                    gameController.getUnitcontroller().makeUnit(swordman, gameController.getOnTurn(), gameController.getSelected1());
                }
            } else {
                if (gameController.getOnTurn().getKingdom().getGold() < Knight.createCost) {
                    gameFrame.showLackOfCoinMassage();
                } else {
                    Knight knight1 = new Knight(gameController.getSelected1(), gameController.getOnTurn().getKingdom());
                    gameController.getUnitcontroller().makeUnit(knight1, gameController.getOnTurn(), gameController.getSelected1());
                }
            }
            createFrame.setVisible(false);
            gameController.setSelected1(null);
            gameController.setSelected1(null);
        });
        buttonPanel.add(attackButton);


        createFrame.add(labelPanel, BorderLayout.NORTH);
        createFrame.add(radioPanel, BorderLayout.CENTER);
        createFrame.add(buttonPanel, BorderLayout.SOUTH);


        createFrame.setVisible(true);
    }

    public void upGrade() {
        Block selectedBlock = gameController.getSelected1();
        if (selectedBlock == null)
            gameController.getFrame().showMassageSelectBlock();
        else {
            if (selectedBlock.hasUnit() && selectedBlock.hasStructure()) {
                if (!selectedBlock.getUnit().getKingdom().getPlayer().equals(gameController.getOnTurn())) {
                    gameFrame.showMessageInOtherBlock();
                    return;
                }
                JFrame createFrame = new JFrame("Create unit");
                createFrame.setResizable(false);
                createFrame.setSize(200, 300);
                createFrame.setLocationRelativeTo(null);
                createFrame.setLayout(new BorderLayout());

                JLabel label = new JLabel("Choose the type");
                label.setHorizontalAlignment(SwingConstants.CENTER);
                JPanel labelPanel = new JPanel(new BorderLayout());
                labelPanel.add(label, BorderLayout.CENTER);


                JPanel radioPanel = new JPanel(new GridLayout(2, 1));
                JRadioButton structure = new JRadioButton("structure");
                JRadioButton unit = new JRadioButton("unit");

                ButtonGroup group = new ButtonGroup();
                group.add(structure);
                group.add(unit);
                structure.setSelected(true);

                radioPanel.add(structure);
                radioPanel.add(unit);


                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                JButton attackButton = new JButton("ok");
                attackButton.addActionListener(e -> {
                    if (structure.isSelected()) {
                        gameController.getStructureController().upGradeStructure(selectedBlock);
                    } else if (unit.isSelected()) {
                        gameController.getUnitcontroller().upGradeUnit(selectedBlock);
                    }
                    createFrame.setVisible(false);
                    gameController.setSelected1(null);

                });
                buttonPanel.add(attackButton);


                createFrame.add(labelPanel, BorderLayout.NORTH);
                createFrame.add(radioPanel, BorderLayout.CENTER);
                createFrame.add(buttonPanel, BorderLayout.SOUTH);


                createFrame.setVisible(true);
            } else if (selectedBlock.hasStructure()) {
                if (!selectedBlock.getStructure().getKingdom().getPlayer().equals(gameController.getOnTurn())) {
                    gameFrame.showMessageInOtherBlock();
                    return;
                }
                gameController.getStructureController().upGradeStructure(selectedBlock);
            } else if (selectedBlock.hasUnit()) {
                if (!selectedBlock.getUnit().getKingdom().getPlayer().equals(gameController.getOnTurn())) {
                    gameFrame.showMessageInOtherBlock();
                    return;
                }
                gameController.getUnitcontroller().upGradeUnit(selectedBlock);
            } else
                gameController.getFrame().showMassageHasNoStrORUnit();
            gameController.setSelected1(null);
        }
    }

    public void moveUnits1(Block block) {
        if (block == null) {
            gameController.getFrame().showMassageSelectBlock();
            return;
        }
        if (!block.hasUnit()) {
            gameFrame.showMessageHasNoUnit();
        } else if (!block.getUnit().getKingdom().getPlayer().equals(gameController.getOnTurn())) {
            gameFrame.showMessageSelectYourOwnUnit();
        }else {
            gameController.setMoveButtonSelected(true);
        }
    }
}