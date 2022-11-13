package ui;

import ui.CustomImageCreation.CustomImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicReference;

//represents the opening panel that is shown to user when image.(in) is initially run
public class OpenPanel extends JPanel {
    private JPanel optionPanel;
    private JLabel logo;
    private final ImageAppGUI iaGUI;
    private CreateImagePanel createImagePanel;
    private ToolMenuPanel toolMenuPanel;
    private String fileSource = null;

    //EFFECTS: creates an opening panel with logo and options for user ot create new, laod previous, or view gallery
    //         (for the last two options, buttons should be un-clickable if no current projects or no gallery projects)
    public OpenPanel(ImageAppGUI iaGUI) {
        super();
        setBorder(BorderFactory.createEmptyBorder());
        setLayout(new GridLayout(0,1));
        createAddLogoPanel();
        createAddOptionPanel();
        createButtons();

        this.iaGUI = iaGUI;
    }

    //MODIFIES: this
    //EFFECTS: creates logo panel and adds it to this
    public void createAddLogoPanel() {
        createLogo();

        JPanel logoPanel = new JPanel();
        logoPanel.setBorder(BorderFactory.createEmptyBorder());
        logoPanel.setLayout(new GridLayout(0, 1));
        logoPanel.add(logo);

        add(logoPanel);
    }

    //MODIFIES: this
    //EFFECTS: creates JLabel representation of application logo
    private void createLogo() {
        ImageIcon icon = new ImageIcon("./data/logo.png");
        logo = new JLabel(icon, JLabel.CENTER);
    }

    //MODIFIES: this
    //EFFECTS: creates option panel and adds it to mainPanel
    public void createAddOptionPanel() {
        optionPanel = new JPanel();
        optionPanel.setBorder(BorderFactory.createEmptyBorder());
        optionPanel.setLayout(new GridBagLayout());
        add(optionPanel);
    }

    //MODIFIES: this
    //EFFECTS: creates all new, load previous, and view gallery buttons
    public void createButtons() {
        createNewImageButton();

        //for use when creating custom Images from use chosen (locally saved) images
        createCustomImageButtonAndDropdown();
        //

        createLoadPrevButton();
        createViewGallButton();
    }

    //MODIFIES: this
    //EFFECTS: creates button for creating new project with specified constraints for GridBagLayout
    public void createNewImageButton() {
        GridBagConstraints c = new GridBagConstraints();

        JButton newImage = new JButton("New Project");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;

        ActionListener newImageAction = e -> {
            moveToCreate();
        };

        newImage.addActionListener(newImageAction);
        optionPanel.add(newImage, c);
    }

    //MODIFIES: this
    //EFFECTS: creates custom button and dropDown with choices of custom images
    private void createCustomImageButtonAndDropdown() {
        createCustomButton();
        createCustomDropdown();
    }

    //MODIFIES: this
    //EFFECTS: creates button for confirming creation of custom image
    private void createCustomButton() {
        GridBagConstraints c = new GridBagConstraints();

        ActionListener customListener = e -> {
            moveToTool();
        };

        JButton createCustom = new JButton("Custom");
        createCustom.addActionListener(customListener);

        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;

        optionPanel.add(createCustom, c);
    }

    //MODIFIES: this
    //EFFECTS: creates JComboBox with keywords for each custom image choice (all locally saved image files)
    private void createCustomDropdown() {
        GridBagConstraints c = new GridBagConstraints();
        String[] options = {"", "dog", "map", "ubc", "wolf", "lab", "ahh"};

        ActionListener comboBoxListener = e -> {
            JComboBox cb = (JComboBox) e.getSource();
            fileSource = (String) cb.getSelectedItem();
            createCustomImage(fileSource);
        };

        JComboBox customOption = new JComboBox(options);
        customOption.addActionListener(comboBoxListener);

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;

        optionPanel.add(customOption, c);
    }

    //MODIFIES: iaGUI
    //EFFECTS: creates Image object from custom image choice
    private void createCustomImage(String fileName) {
        String destinationFile = "./data/custom/" + fileSource + ".png";
        CustomImage customImage = new CustomImage(destinationFile);
        customImage.writeCustomToImage();
        iaGUI.setMyImage(customImage.getCustomImage());
    }

    //MODIFIES: this
    //EFFECTS: creates load previous button with specified constraints
    public void createLoadPrevButton() {
        GridBagConstraints c = new GridBagConstraints();

        JButton loadPrev = new JButton("Load Project");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;

        ActionListener loadPrevAction = e -> {
            //testing functionality
            logo.setText("loading previous");
        };

        loadPrev.addActionListener(loadPrevAction);
        optionPanel.add(loadPrev, c);
    }

    //MODIFIES: this
    //EFFECTS: creates view gallery button with specified constraints
    public void createViewGallButton() {
        GridBagConstraints c = new GridBagConstraints();

        JButton viewGall = new JButton("View Gallery");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;

        ActionListener viewGallAction = e -> {
            //testing functionality
            logo.setText("viewing gallery");
        };

        viewGall.addActionListener(viewGallAction);
        optionPanel.add(viewGall, c);
    }

    //MODIFIES: this
    //EFFECTS: leaves open panel and opens create image panel
    public void moveToCreate() {
        this.setVisible(false);

        createImagePanel = new CreateImagePanel(iaGUI);
        iaGUI.add(createImagePanel);
    }

    //MODIFIES: this
    //EFFECTS: leaves open panel and opens tool menu panel
    public void moveToTool() {
        this.setVisible(false);

        toolMenuPanel = new ToolMenuPanel(iaGUI);
        iaGUI.add(toolMenuPanel);
    }
}