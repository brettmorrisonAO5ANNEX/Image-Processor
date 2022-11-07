package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//represents the opening panel that is shown to user when image.(in) is initially run
public class OpenPanel extends JPanel {
    private JPanel optionPanel;

    private JLabel logo;

    private final ImageAppGUI iaGUI;
    private CreateImagePanel createImagePanel;

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

    //TODO: constrain logo
    //MODIFIES: this
    //EFFECTS: creates JLabel representation of application logo
    private void createLogo() {
//        ImageIcon icon = new ImageIcon("./data/tobs.jpg");
//        logo = new JLabel(icon, JLabel.CENTER);
        logo = new JLabel("", JLabel.CENTER);
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
            this.setVisible(false);

            createImagePanel = new CreateImagePanel(iaGUI);
            iaGUI.add(createImagePanel);
        };

        newImage.addActionListener(newImageAction);
        optionPanel.add(newImage, c);
    }

    //MODIFIES: this
    //EFFECTS: creates load previous button with specified constraints
    public void createLoadPrevButton() {
        GridBagConstraints c = new GridBagConstraints();

        JButton loadPrev = new JButton("Load Project");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;

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
        c.gridy = 1;

        ActionListener viewGallAction = e -> {
            //testing functionality
            logo.setText("viewing gallery");
        };

        viewGall.addActionListener(viewGallAction);
        optionPanel.add(viewGall, c);
    }

//    //MODIFIES: this
//    //EFFECTS: creates main frame for opening page
//    public void createMainFrame() {
//        mainFrame = new JFrame();
//
//        mainFrame.setTitle("image.(in)");
//        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
//        mainFrame.add(mainPanel);
//        mainFrame.setVisible(show);
//        mainFrame.pack();
//        mainFrame.setBounds(200, 200, 500, 300);
//    }
}