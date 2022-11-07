package ui;

import model.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TODO: add code credit to oracle
//represents panel that is shown when user is creating a new project
public class CreateImagePanel extends JPanel {
    private JTextField chooseWidth;
    private JTextField chooseHeight;
    private String randomize;
    private final ImageAppGUI iaGUI;
    private ToolMenuPanel toolMenuPanel;
    private int height;
    private int width;

    //MODIFIES: this
    //EFFECTS: creates createImagePanel which is shown to user if create new is chosen from opening menu
    public CreateImagePanel(ImageAppGUI iaGUI) {
        super();
        setBorder(BorderFactory.createEmptyBorder());
        setLayout(new GridBagLayout());
        createChooseNameField();
        createChooseDimensions();
        createRandomizeChooser();
        createConfirm();

        this.iaGUI = iaGUI;
    }

    //MODIFIES: this
    //EFFECTS: creates text field element so user can set project name according to regex specifications
    private void createChooseNameField() {
        GridBagConstraints c = new GridBagConstraints();
        JTextField chooseName = new JTextField(10);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;

        add(chooseName, c);
    }

    //MODIFIES: this
    //EFFECTS: creates text boxes for dimensions setting and adds them to main layout
    private void createChooseDimensions() {
        createWidthField();
        createHeightField();
    }

    //MODIFIES: this
    //EFFECTS: creates width text field and adds it to main layout
    private void createWidthField() {
        GridBagConstraints c = new GridBagConstraints();

        chooseWidth = new JTextField(10);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;

        add(chooseWidth, c);
    }

    //MODIFIES: this
    //EFFECTS: creates width text field and adds it to layout
    private void createHeightField() {
        GridBagConstraints c = new GridBagConstraints();
        chooseHeight = new JTextField(10);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;

        add(chooseHeight, c);
    }

    //MODIFIES: this
    //EFFECTS: creates combo box dropdown so user can choose to randomize project color
    public void createRandomizeChooser() {
        GridBagConstraints c = new GridBagConstraints();

        String[] chooseRandomizeOptions = {"Choose An Option", "yes", "no"};

        ActionListener comboBoxListener = e -> {
            JComboBox cb = (JComboBox) e.getSource();
            randomize = (String) cb.getSelectedItem();
        };

        JComboBox chooseRandomize = new JComboBox(chooseRandomizeOptions);
        chooseRandomize.addActionListener(comboBoxListener);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;

        add(chooseRandomize, c);
    }

    //MODIFIES: this
    //EFFECTS: creates confirm button to create project with specified properties
    private void createConfirm() {
        GridBagConstraints c = new GridBagConstraints();
        JButton confirm = new JButton("Create Project");

        ActionListener confirmListener = e -> {
            this.setVisible(false);
            toolMenuPanel = new ToolMenuPanel(iaGUI);
            iaGUI.add(toolMenuPanel);
            toolMenuPanel.setMyImage(createImage());
        };

        confirm.addActionListener(confirmListener);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 3;

        add(confirm, c);
    }

    //MODIFIES: this
    //EFFECTS: creates an image with user specifications and sends it to toolMenuPanel
    private Image createImage() {
        width = Integer.parseInt(chooseWidth.getText());
        height = Integer.parseInt(chooseHeight.getText());
        Image myImage = new Image(width, height);

        if (randomize.equals("yes")) {
            myImage.randomizeColor();
        }
        return myImage;
    }
}
