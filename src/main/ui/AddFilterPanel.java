package ui;

import model.Filter;
import model.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static java.lang.Math.log;
import static java.lang.Math.min;

//represents add filter panel that is shown to user when they choose the add filter option from tool menu
public class AddFilterPanel extends JPanel {
    private final Filter mirrorFilter;
    private final Filter negativeFilter;
    private final Filter pixelateFilter;
    private ToolMenuPanel toolMenuPanel;
    private ImageAppGUI iaGUI;
    private Image myImage;
    private int width;
    private int height;

    //MODIFIES: this
    //EFFECTS: creates add filter panel for given session/project
    public AddFilterPanel(ToolMenuPanel toolMenuPanel, int w, int h) {
        super();
        mirrorFilter = new Filter("mirror");
        negativeFilter = new Filter("negative");
        pixelateFilter = new Filter("pixelate");
        this.toolMenuPanel = toolMenuPanel;
        this.iaGUI = toolMenuPanel.getImageAppGUI();
        this.myImage = iaGUI.getMyImage();
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
        createPixelateButton();
    }

    //MODIFIES: this, myImage
    //EFFECTS: creates button for mirror filter option
    private void createMirrorButton() {
        GridBagConstraints c = new GridBagConstraints();

        ActionListener mirrorListener = e -> {
            myImage.addFilter(mirrorFilter);
            myImage.addIfUnique(mirrorFilter);
            returnToToolMenu();
        };

        JButton mirror = new JButton("Mirror");
        mirror.addActionListener(mirrorListener);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;

        add(mirror, c);
    }

    //MODIFIES: this, myImage
    //EFFECTS: creates button for negative filter option
    private void createNegativeButton() {
        GridBagConstraints c = new GridBagConstraints();

        ActionListener negativeListener = e -> {
            myImage.addFilter(negativeFilter);
            myImage.addIfUnique(negativeFilter);
            returnToToolMenu();
        };

        JButton negative = new JButton("Negative");
        negative.addActionListener(negativeListener);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;

        add(negative, c);
    }

    //TODO: add listener to apply deg pix to image
    //MODIFIES: this, myImage
    //EFFECTS: creates pixelate dropdown with degree of pixelation option for user
    private void createPixelateDropDown() {
        GridBagConstraints c = new GridBagConstraints();
        int minDim = min(width, height);
        int maxDegPix = (int) (log(minDim) / log(2));
        String[] degPixOptions = new String[maxDegPix + 1];
        degPixOptions[0] = "";

        for (int i = 1; i < maxDegPix + 1; i++) {
            degPixOptions[i] = Integer.toString(i);
        }

        ActionListener degPixListener = e -> {
            JComboBox cb = (JComboBox) e.getSource();
            int degPix = (int) cb.getSelectedItem();
            myImage.setDegreeOfPixelation(degPix);
        };

        JComboBox pixelate = new JComboBox(degPixOptions);
        pixelate.addActionListener(degPixListener);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        add(pixelate, c);
    }

    //MODIFIES: this, myImage
    //EFFECTS: creates apply button that applies chosen filter and returns to tool menu
    public void createPixelateButton() {
        GridBagConstraints c = new GridBagConstraints();

        ActionListener pixelateListener = e -> {
            myImage.addFilter(pixelateFilter);
            myImage.addIfUnique(pixelateFilter);
            returnToToolMenu();
        };

        JButton pixelate = new JButton("Pixelate");
        pixelate.addActionListener(pixelateListener);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;
        add(pixelate, c);
    }

    //MODIFIES: this
    //EFFECTS: returns to toolMenuPanel
    private void returnToToolMenu() {
        iaGUI.remove(this);
        toolMenuPanel.setVisible(true);
    }
}
