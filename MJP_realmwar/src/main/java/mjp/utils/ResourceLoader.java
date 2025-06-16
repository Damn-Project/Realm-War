package mjp.utils;

import javax.swing.*;
import java.awt.*;

public class ResourceLoader {
    public ImageIcon[] imageIcons = new ImageIcon[10];

    public ResourceLoader() {
        loadImage();
    }

    public void loadImage() {
        ImageIcon img;
        Image scaledImg;
        try {
            img = new ImageIcon(getClass().getResource("/Barrack.jpg"));
            scaledImg = img.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            imageIcons[1] = new ImageIcon(scaledImg);

            img = new ImageIcon(getClass().getResource("/Farm.jpg"));
            scaledImg = img.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            imageIcons[2] = new ImageIcon(scaledImg);

            img = new ImageIcon(getClass().getResource("/Forest.jpg"));
            scaledImg = img.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            imageIcons[3] = new ImageIcon(scaledImg);

            img = new ImageIcon(getClass().getResource("/Knight.jpg"));
            scaledImg = img.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            imageIcons[4] = new ImageIcon(scaledImg);

            img = new ImageIcon(getClass().getResource("/Peasant.jpg"));
            scaledImg = img.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            imageIcons[5] = new ImageIcon(scaledImg);

            img = new ImageIcon(getClass().getResource("/Spearman.jpg"));
            scaledImg = img.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            imageIcons[6] = new ImageIcon(scaledImg);

            img = new ImageIcon(getClass().getResource("/Swordman.jpg"));
            scaledImg = img.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            imageIcons[7] = new ImageIcon(scaledImg);

            img = new ImageIcon(getClass().getResource("/Tower.jpg"));
            scaledImg = img.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            imageIcons[8] = new ImageIcon(scaledImg);

            img = new ImageIcon(getClass().getResource("/Townhall.jpg"));
            scaledImg = img.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            imageIcons[9] = new ImageIcon(scaledImg);
        } catch (Exception e) {
            System.out.println("does not load");
        }
    }
}
