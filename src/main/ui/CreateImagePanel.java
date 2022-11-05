package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TODO: debug so that multiple windows aren't opened when a user clickes to new panel
//TODO: add code credit to oracle
//represents panel that is shown when user is creating a new project
public class CreateImagePanel {
    private JPanel mainPanel;
    private String randomize;

    //MODIFIES: this
    //EFFECTS: creates createImagePanel which is shown to user if create new is chosen from opening menu
    public CreateImagePanel(boolean s) {
        createMainPanel();
        createChooseNameField();
        createChooseDimensions();
        createRandomizeChooser();
        createConfirm();
        createMainFrame(s);
    }

    //MODIFIES: this
    //EFFECTS: creates main panel to house all create image options
    private void createMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder());
        mainPanel.setLayout(new GridBagLayout());
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

        mainPanel.add(chooseName, c);
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
        JTextField chooseWidth = new JTextField(10);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;

        mainPanel.add(chooseWidth, c);
    }

    //MODIFIES: this
    //EFFECTS: creates width text field and adds it to layout
    private void createHeightField() {
        GridBagConstraints c = new GridBagConstraints();
        JTextField chooseHeight = new JTextField(10);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;

        mainPanel.add(chooseHeight, c);
    }

    //MODIFIES: this
    //EFFECTS: creates combo box dropdown so user can choose to randomize project color
    public void createRandomizeChooser() {
        GridBagConstraints c = new GridBagConstraints();

        String[] chooseRandomizeOptions = {"Choose An Option", "yes", "no"};

        ActionListener comboBoxListener = e -> {
            JComboBox cb = (JComboBox)e.getSource();
            randomize = (String)cb.getSelectedItem();
        };

        JComboBox chooseRandomize = new JComboBox(chooseRandomizeOptions);
        chooseRandomize.addActionListener(comboBoxListener);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;

        mainPanel.add(chooseRandomize, c);
    }

    //MODIFIES: this
    //EFFECTS: creates confirm button to create project with specified properties
    private void createConfirm() {
        GridBagConstraints c = new GridBagConstraints();
        JButton confirm = new JButton("Create Project");

        ActionListener confirmListener = e -> {
            //stub
        };

        confirm.addActionListener(confirmListener);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 3;

        mainPanel.add(confirm, c);
    }

    //MODIFIES: this
    //EFFECTS: creates mainFrame and adds mainPanel
    private void createMainFrame(boolean s) {
        JFrame mainFrame = new JFrame("create project");
        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(s);
        mainFrame.pack();
        mainFrame.setBounds(200, 200, 500, 300);
    }
}
