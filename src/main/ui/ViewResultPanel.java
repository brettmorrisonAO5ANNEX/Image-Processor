package ui;

import javax.swing.*;
import java.awt.*;

//represents panel shown when user processes image to display processed pixel array
public class ViewResultPanel extends JPanel {

    public ViewResultPanel() {
        setBorder(BorderFactory.createEmptyBorder());
        setLayout(new GridBagLayout());
        setBackground(new Color(220, 220, 220));
    }
}
