package ui;

import model.Filter;
import model.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//represents add filter panel that is shown to user when they choose the add filter option from tool menu
public class AddFilterPanel extends JPanel {
    private final Filter mirrorFilter;
    private final Filter negativeFilter;
    private final Filter colorGradient;
    private final Filter pixelateFilter;
    private ToolMenuPanel toolMenuPanel;
    private ImageAppGUI iaGUI;
    private Image myImage;
    private JPanel logoPanel;
    private JPanel optionPanel;
    private int width;
    private int height;

    //MODIFIES: this
    //EFFECTS: creates add filter panel for given session/project
    public AddFilterPanel(ToolMenuPanel toolMenuPanel, int w, int h) {
        super();
        mirrorFilter = new Filter("mirror");
        negativeFilter = new Filter("negative");
        colorGradient = new Filter("colorGradient");
        pixelateFilter = new Filter("pixelate");
        this.toolMenuPanel = toolMenuPanel;
        this.iaGUI = toolMenuPanel.getImageAppGUI();
        this.myImage = iaGUI.getMyImage();
        this.width = w;
        this.height = h;

        setBorder(BorderFactory.createEmptyBorder());
        setLayout(new GridLayout(0, 1));
        createSubPanels();
    }

    //MODIFIES: this
    //EFFECTS: creates logo and option panels then adds them to this
    private void createSubPanels() {
        createLogoPanel();
        createOptionPanel();
    }

    //MODIFIES: this
    //EFFECTS: creates and adds logo panel to this
    public void createLogoPanel() {
        ImageIcon filterLogo = new ImageIcon("./data/UI/filter.png");
        logoPanel = new JPanel();
        logoPanel.add(new JLabel(filterLogo, JLabel.CENTER));
        logoPanel.setBackground(new Color(206, 226, 255));

        add(logoPanel);
    }

    //MODIFIES: this
    //EFFECTS: creates and adds option panel to this
    private void createOptionPanel() {
        optionPanel = new JPanel();
        optionPanel.setLayout(new GridBagLayout());
        optionPanel.setBackground(new Color(50, 135, 251));

        createAndAddButtons();

        add(optionPanel);
    }

    //MODIFIES: this
    //EFFECTS: creates buttons and drop down menus for each filter type
    private void createAndAddButtons() {
        createMirrorButton();
        createNegativeButton();
        createColorGradientOption();
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

        optionPanel.add(mirror, c);
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

        optionPanel.add(negative, c);
    }

    //MODIFIES: this
    //EFFECTS: creates colorGradient button and corresponding rgbComponent dropdown menu
    private void createColorGradientOption() {
        createColorGradientDropDown();
        createColorGradientButton();
    }

    //MODIFIES: this
    //EFFECTS: creates dropdown with options for user to pick which rgb component to apply gradient to
    private void createColorGradientDropDown() {
        GridBagConstraints c = new GridBagConstraints();
        String[] rgbCompOptions = {"", "red", "green", "blue"};

        ActionListener compListener = e -> {
            JComboBox cb = (JComboBox) e.getSource();
            String chosenComp = (String) cb.getSelectedItem();
            myImage.setCompChoice(chosenComp);
        };

        JComboBox colorGradient = new JComboBox(rgbCompOptions);
        colorGradient.addActionListener(compListener);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;

        optionPanel.add(colorGradient, c);
    }

    //MODIFIES: this
    //EFFECTS: creates colorGradient button that applies colorGradient filter then returns to tool menu
    private void createColorGradientButton() {
        GridBagConstraints c = new GridBagConstraints();

        ActionListener gradientListener = e -> {
            myImage.addFilter(colorGradient);
            myImage.addIfUnique(colorGradient);
            returnToToolMenu();
        };

        JButton gradientButton = new JButton("Gradient");
        gradientButton.addActionListener(gradientListener);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;

        optionPanel.add(gradientButton, c);
    }

    //MODIFIES: this, myImage
    //EFFECTS: creates apply button that applies pixelate filter and returns to tool menu
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
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 3;

        optionPanel.add(pixelate, c);
    }

    //MODIFIES: this
    //EFFECTS: returns to toolMenuPanel
    private void returnToToolMenu() {
        toolMenuPanel.updateHistoryPanel();
        iaGUI.remove(this);
        toolMenuPanel.setVisible(true);
    }
}
