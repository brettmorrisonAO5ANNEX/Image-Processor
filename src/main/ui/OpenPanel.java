package ui;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;

//TODO: create helper to shorten function
public class OpenPanel extends JPanel {
    private JPanel mainPanel;
    private JPanel logoPanel;
    private JPanel optionPanel;

    private JFrame openFrame;

    //EFFECTS: creates an opening panel with logo and options for user ot create new, laod previous, or view gallery
    //         (for the last two options, buttons should be un-clickable if no current projects or no gallery projects)
    public OpenPanel() {
        //constraints object used to set constraints for each button in optionPanel
        GridBagConstraints c = new GridBagConstraints();

        //test to see if logo dimension will be ok
        JButton testLogo = new JButton("Image.(in)");

        JButton newImage = new JButton("New Project");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 10;
        c.ipady = 10;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;

        JButton loadPrev = new JButton("Load Project");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;

        JButton viewGall = new JButton("View Gallery");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;

        logoPanel = new JPanel();
        logoPanel.setBorder(BorderFactory.createEmptyBorder());
        logoPanel.setLayout(new GridLayout(0, 1));
        //test to see if logo dimension will be ok
        logoPanel.add(testLogo);

        optionPanel = new JPanel();
        optionPanel.setBorder(BorderFactory.createEmptyBorder());
        optionPanel.setLayout(new GridBagLayout());
        // create constraints to keep new above load and view (but view and load shoule be next to eachother
        optionPanel.add(newImage);
        optionPanel.add(loadPrev);
        optionPanel.add(viewGall);

        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder());
        mainPanel.setLayout(new GridLayout(0,1));
        mainPanel.add(logoPanel);
        mainPanel.add(optionPanel);


//        openPanel = new JPanel();
//        openPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
//        openPanel.setLayout(new GridLayout(0, 1));
//        openPanel.add(newImage);
//        openPanel.add(loadPrev);
//        openPanel.add(viewGall);
//
//        openFrame = new JFrame();
//        openFrame.setBounds(100, 100, 100, 100);
//        openFrame.setTitle("Image.(in)");
//        openFrame.setDefaultCloseOperation(openFrame.EXIT_ON_CLOSE);
//        openFrame.setVisible(true);
//        openFrame.setSize(400, 400);
//        openFrame.add(openPanel);
    }
}
