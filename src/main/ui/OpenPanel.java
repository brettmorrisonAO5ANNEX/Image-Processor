package ui;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;

//TODO: create helper to shorten function
public class OpenPanel extends JPanel {
    private JPanel mainPanel;
    private JPanel logoPanel;
    private JPanel optionPanel;

    private JButton newImage;
    private JButton loadPrev;
    private JButton viewGall;

    private JFrame mainFrame;

    //EFFECTS: creates an opening panel with logo and options for user ot create new, laod previous, or view gallery
    //         (for the last two options, buttons should be un-clickable if no current projects or no gallery projects)
    public OpenPanel() {
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder());
        mainPanel.setLayout(new GridLayout(0,1));

        logoPanel = new JPanel();
        logoPanel.setBorder(BorderFactory.createEmptyBorder());
        logoPanel.setLayout(new GridLayout(0, 1));
        mainPanel.add(logoPanel);

        optionPanel = new JPanel();
        optionPanel.setBorder(BorderFactory.createEmptyBorder());
        optionPanel.setLayout(new GridBagLayout());
        mainPanel.add(optionPanel);

        createButtons(optionPanel);

        mainFrame = new JFrame();
        mainFrame.setTitle("image.(in)");
        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        mainFrame.pack();
        mainFrame.setBounds(200, 200, 500, 300);
    }

    //MODIFIES: this
    //EFFECTS: creates all new, load previous, and view gallery buttons
    public void createButtons(JPanel optionPanel) {
        createNewImageButton(optionPanel);
        createLoadPrevButton(optionPanel);
        createViewGallButton(optionPanel);
    }

    //MODIFIES: this
    //EFFECTS: creates button for creating new project with specified constraints for GridBagLayout
    public void createNewImageButton(JPanel optionPanel) {
        GridBagConstraints c = new GridBagConstraints();

        newImage = new JButton("New Project");
        c.fill = GridBagConstraints.HORIZONTAL;
//        c.ipadx = 10;
//        c.ipady = 10;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;

        optionPanel.add(newImage, c);
    }

    //MODIFIES: this
    //EFFECTS: creates load previous button with specified constraints
    public void createLoadPrevButton(JPanel optionPanel) {
        GridBagConstraints c = new GridBagConstraints();

        loadPrev = new JButton("Load Project");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;

        optionPanel.add(loadPrev, c);
    }

    //MODIFIES: this
    //EFFECTS: creates view gallery button with specified constraints
    public void createViewGallButton(JPanel optionPanel) {
        GridBagConstraints c = new GridBagConstraints();

        viewGall = new JButton("View Gallery");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;

        optionPanel.add(viewGall, c);
    }
}