package ui;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.log;
import static java.lang.Math.min;

//represents add filter panel that is shown to user when they choose the add filter option from tool menu
public class AddFilterPanel extends JPanel {
    private ToolMenuPanel toolMenuPanel;
    private int width;
    private int height;

    //MODIFIES: this
    //EFFECTS: creates add filter panel for given session/project
    public AddFilterPanel(ToolMenuPanel toolMenuPanel, int w, int h) {
        super();
        this.toolMenuPanel = toolMenuPanel;
        this.width = w;
        this.height = h;

        setBorder(BorderFactory.createEmptyBorder());
        setLayout(new GridBagLayout());
        createAndAddButtons();
    }

    //MODIFIES: this
    //EFFECTS: creates buttons and drop down menus for each filter type
    private void createAndAddButtons() {
        createMirrorButton();
        createNegativeButton();
        createPixelateDropDown();
    }

    //MODIFIES: this
    //EFFECTS: creates button for mirror filter option
    private void createMirrorButton() {
        GridBagConstraints c = new GridBagConstraints();
        JButton mirror = new JButton("Mirror");

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;

        add(mirror, c);
    }

    //MODIFIES: this
    //EFFECTS: creates button for negative filter option
    private void createNegativeButton() {
        GridBagConstraints c = new GridBagConstraints();
        JButton negative = new JButton("Negative");

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;

        add(negative, c);
    }

    //MODIFIES: this
    //EFFECTS: creates pixelate dropdown with degree of pixelation option for user
    private void createPixelateDropDown() {
        GridBagConstraints c = new GridBagConstraints();
        int minDim = min(width, height);
        int maxDegPix = (int) (log(minDim) / log(2));
        String[] degPixOptions = new String[maxDegPix + 1];
        degPixOptions[0] = "Pixelate";

        for (int i = 1; i < maxDegPix + 1; i++) {
            degPixOptions[i] = Integer.toString(i);
        }

        JComboBox pixelate = new JComboBox(degPixOptions);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        add(pixelate, c);
    }
}
