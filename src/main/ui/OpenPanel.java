package ui;

import javax.swing.*;
import java.awt.*;

public class OpenPanel extends JPanel {
    private JPanel openPanel;
    private JFrame openFrame;

    public OpenPanel() {
        JButton newImage = new JButton("Create New Image");
        JButton loadPrev = new JButton("Load Previous Project");
        JButton viewGall = new JButton("View Gallery");

        openPanel = new JPanel();
        openPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        openPanel.setLayout(new GridLayout(0, 1));
        openPanel.add(newImage);
        openPanel.add(loadPrev);
        openPanel.add(viewGall);

        openFrame = new JFrame();
        openFrame.setBounds(100, 100, 100, 100);
        openFrame.setTitle("Image.(in)");
        openFrame.setDefaultCloseOperation(openFrame.EXIT_ON_CLOSE);
        openFrame.setVisible(true);
        openFrame.setSize(400, 400);
        openFrame.add(openPanel);
    }
}
