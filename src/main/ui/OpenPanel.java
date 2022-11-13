package ui;

import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

//represents the opening panel that is shown to user when image.(in) is initially run
public class OpenPanel extends JPanel {
    private JPanel optionPanel;
    private JLabel logo;
    private final ImageAppGUI iaGUI;
    private CreateImagePanel createImagePanel;
    private ToolMenuPanel toolMenuPanel;
    private String fileSource = null;

    private JsonReader jsonReader;

    //EFFECTS: creates an opening panel with logo and options for user ot create new, laod previous, or view gallery
    //         (for the last two options, buttons should be un-clickable if no current projects or no gallery projects)
    public OpenPanel(ImageAppGUI iaGUI) {
        super();

        this.iaGUI = iaGUI;

        setBorder(BorderFactory.createEmptyBorder());
        setLayout(new GridLayout(0,1));
        createAddLogoPanel();
        createAddOptionPanel();
        createButtons();
    }

    //MODIFIES: this
    //EFFECTS: creates logo panel and adds it to this
    public void createAddLogoPanel() {
        createLogo();

        JPanel logoPanel = new JPanel();
        logoPanel.setBorder(BorderFactory.createEmptyBorder());
        logoPanel.setLayout(new GridLayout(0, 1));
        logoPanel.setBackground(new Color(206, 226, 255));
        logoPanel.add(logo);

        add(logoPanel);
    }

    //MODIFIES: this
    //EFFECTS: creates JLabel representation of application logo
    private void createLogo() {
        ImageIcon icon = new ImageIcon("./data/UI/logo.png");
        logo = new JLabel(icon, JLabel.CENTER);
    }

    //MODIFIES: this
    //EFFECTS: creates option panel and adds it to mainPanel
    public void createAddOptionPanel() {
        optionPanel = new JPanel();
        optionPanel.setBorder(BorderFactory.createEmptyBorder());
        optionPanel.setLayout(new GridBagLayout());
        optionPanel.setBackground(new Color(50, 135, 251));
        add(optionPanel);
    }

    //MODIFIES: this
    //EFFECTS: creates all new, load previous, and view gallery buttons
    public void createButtons() {
        createNewImageButton();

        //for use when creating custom Images from use chosen (locally saved) images
        createCustomImageButtonAndDropdown();
        //

        createLoadButtonAndDropdown();
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
        String[] options = {"<Templates>", "dog", "map", "ubc", "wolf", "lab", "ahh"};

        ActionListener comboBoxListener = e -> {
            JComboBox cb = (JComboBox) e.getSource();
            fileSource = (String) cb.getSelectedItem();
            createCustomImage(fileSource);
        };

        JComboBox customOption = new JComboBox(options);
        customOption.addActionListener(comboBoxListener);

        c.fill = GridBagConstraints.HORIZONTAL;
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
    //EFFECTS: creates button and dropdown for load previous
    private void createLoadButtonAndDropdown() {
        createLoadPrevButton();
        createLoadPrevDropdown();
    }

    //MODIFIES: this
    //EFFECTS: creates load previous button with specified constraints
    private void createLoadPrevButton() {
        GridBagConstraints c = new GridBagConstraints();
        JButton loadPrev = new JButton("Load");

        ActionListener loadListener = e -> loadPrevious();

        loadPrev.addActionListener(loadListener);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;
        optionPanel.add(loadPrev, c);
    }

    //MODIFIES: this
    //EFFECTS: creates load previous dropdown with all project options to choose from
    public void createLoadPrevDropdown() {
        GridBagConstraints c = new GridBagConstraints();

        JComboBox loadPrev = new JComboBox(createOptions());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;

        ActionListener loadPrevAction = e -> {
            JComboBox cb = (JComboBox) e.getSource();
            String choice = (String) cb.getSelectedItem();
            String destination = iaGUI.getFileBegin() + choice + iaGUI.getFileEnd();
            iaGUI.setJsonReader(destination);
            iaGUI.setJsonWriter(destination);
            this.jsonReader = iaGUI.getJsonReader();
        };

        loadPrev.addActionListener(loadPrevAction);
        optionPanel.add(loadPrev, c);
    }

    //MODIFIES: this
    //EFFECTS: reads chosen project, throws error if error occurs
    private void loadPrevious() {
        try {
            iaGUI.setMyImage(jsonReader.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        moveToTool();
    }

    //MODIFIES: this
    //EFFECTS: creates array of all previous projects to load from iAGUI.currentProjects
    private String[] createOptions() {
        if (iaGUI.getProjects().getCurrentProjects().isEmpty()) {
            return new String[]{"N/A"};
        } else {
            int numOptions = iaGUI.getProjects().getCurrentProjects().size();
            String[] options = new String[numOptions + 1];
            options[0] = "<Options>";
            int count = 1;

            for (String project : iaGUI.getProjects().getCurrentProjects()) {
                options[count] = project;
                count++;
            }

            return options;
        }
    }

    //MODIFIES: this
    //EFFECTS: creates view gallery button with specified constraints
    public void createViewGallButton() {
        GridBagConstraints c = new GridBagConstraints();

        JButton viewGall = new JButton("View Gallery");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 3;

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