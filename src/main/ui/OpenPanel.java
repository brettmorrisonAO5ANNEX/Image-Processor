package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenPanel extends ImageIcon {
    private boolean show = true;

    private CreateImagePanel createImagePanel;

    private JPanel mainPanel;
    private JPanel logoPanel;
    private JPanel optionPanel;

    private JButton newImage;
    private JButton loadPrev;
    private JButton viewGall;

    private JFrame mainFrame;

    private JLabel logo;

    //EFFECTS: creates an opening panel with logo and options for user ot create new, laod previous, or view gallery
    //         (for the last two options, buttons should be un-clickable if no current projects or no gallery projects)
    public OpenPanel() {
        createMainPanel();
        createButtons();
        createMainFrame();
    }

    //MODIFIES: this
    //EFFECTS: creates main panel to that houses subpanels: logoPanel and optionPanel
    public void createMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder());
        mainPanel.setLayout(new GridLayout(0,1));

        createAddLogoPanel();
        createAddOptionPanel();
    }

    //MODIFIES: this
    //EFFECTS: creates logo panel and adds it to mainPanel
    public void createAddLogoPanel() {
        createLogo();

        logoPanel = new JPanel();
        logoPanel.setBorder(BorderFactory.createEmptyBorder());
        logoPanel.setLayout(new GridLayout(0, 1));
        logoPanel.add(logo);

        mainPanel.add(logoPanel);
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
        mainPanel.add(optionPanel);
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

        newImage = new JButton("New Project");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;

        ActionListener newImageAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createImagePanel = new CreateImagePanel(true);
                show = false;
            }
        };

        newImage.addActionListener(newImageAction);
        optionPanel.add(newImage, c);
    }

    //MODIFIES: this
    //EFFECTS: creates load previous button with specified constraints
    public void createLoadPrevButton() {
        GridBagConstraints c = new GridBagConstraints();

        loadPrev = new JButton("Load Project");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;

        ActionListener loadPrevAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //testing functionality
                logo.setText("loading previous");
            }
        };

        loadPrev.addActionListener(loadPrevAction);
        optionPanel.add(loadPrev, c);
    }

    //MODIFIES: this
    //EFFECTS: creates view gallery button with specified constraints
    public void createViewGallButton() {
        GridBagConstraints c = new GridBagConstraints();

        viewGall = new JButton("View Gallery");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;

        ActionListener viewGallAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //testing functionality
                logo.setText("viewing gallery");
            }
        };

        viewGall.addActionListener(viewGallAction);
        optionPanel.add(viewGall, c);
    }

    //MODIFIES: this
    //EFFECTS: creates main frame for opening page
    public void createMainFrame() {
        mainFrame = new JFrame();

        mainFrame.setTitle("image.(in)");
        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(show);
        mainFrame.pack();
        mainFrame.setBounds(200, 200, 500, 300);
    }
}