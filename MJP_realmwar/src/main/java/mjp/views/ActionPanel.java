package mjp.views;

import javax.swing.*;
import java.awt.*;

public class ActionPanel extends JPanel {

    private JButton attackButton;
    private JButton moveButton;
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
        setPreferredSize(new Dimension(150,100 ));
        setBackground(new Color(200, 200, 200));

        attackButton = new JButton("Attack");
        attackButton.setBackground(Color.BLACK);
        attackButton.setForeground(Color.BLUE);
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
        add(attackButton);
        add(moveButton);
        add(upgradeButton);
        add(createButton);


        createButton.addActionListener(e -> {
            // ساخت یک پنجره‌ی جدید
            JFrame createFrame = new JFrame("Create Building");
            createFrame.setSize(200, 300);
            createFrame.setLocationRelativeTo(null); // وسط صفحه باز بشه
            createFrame.setLayout(new BorderLayout());

            JLabel label = new JLabel("Choose the type");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            JPanel labelPanel = new JPanel(new BorderLayout());
            labelPanel.add(label, BorderLayout.CENTER);


            JPanel radioPanel = new JPanel(new GridLayout(3, 1));
            JRadioButton Barrackbutton = new JRadioButton("Barrack");
            JRadioButton Farmbutton = new JRadioButton("Farm");
            JRadioButton Towerbutton = new JRadioButton("Tower");


            ButtonGroup group = new ButtonGroup();
            group.add(Barrackbutton);
            group.add(Farmbutton);
            group.add(Towerbutton);

            radioPanel.add(Barrackbutton);
            radioPanel.add(Farmbutton);
            radioPanel.add(Towerbutton);



            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton attackButton = new JButton("ok");
            buttonPanel.add(attackButton);



            createFrame.add(labelPanel, BorderLayout.NORTH);
            createFrame.add(radioPanel, BorderLayout.CENTER);
            createFrame.add(buttonPanel, BorderLayout.SOUTH);


            createFrame.setVisible(true);
        });

    }
}