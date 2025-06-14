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
        moveButton = new JButton("Move");
        upgradeButton = new JButton("Upgrade");

        // افزودن دکمه‌ها به پنل
        add(attackButton);
        add(moveButton);
        add(upgradeButton);
    }
}