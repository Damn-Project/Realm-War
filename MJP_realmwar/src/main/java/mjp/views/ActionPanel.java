package mjp.views;

import javax.swing.*;
import java.awt.*;

public class ActionPanel extends JPanel {

    private JButton attackButton;
    private JButton moveButton;
    private JButton upgradeButton;

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

        // افزودن دکمه‌ها به پنل
        add(attackButton);
        add(moveButton);
        add(upgradeButton);
    }
}