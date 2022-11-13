package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//represents panel shown when user processes image to display processed pixel array
public class ViewResultPanel extends JPanel {
    private JPanel coverPanel;
    private int progress = 0;

    public ViewResultPanel() {
        setBorder(BorderFactory.createEmptyBorder());
        setLayout(new GridBagLayout());
        setBackground(new Color(206, 226, 255));
    }
}