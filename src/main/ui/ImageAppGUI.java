package ui;

import model.CurrentProjects;
import model.Gallery;
import model.Image;

import javax.swing.*;

//represents the main GUI of image.(in)
public class ImageAppGUI extends JFrame {
    private CurrentProjects currentProjects;
    private Gallery gallery;
    private Image myImage;

    private OpenPanel openPanel;
    private CreateImagePanel createImagePanel;

    //EFFECTS: sets up editing window from which user can choose to create new project or choose to
    //         load previous or view finished projects
    public ImageAppGUI() {
        super("image.(in)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createImagePanel = new CreateImagePanel(this);
        openPanel = new OpenPanel(createImagePanel, this);
        add(openPanel);
        pack();
        setVisible(true);
        setBounds(200, 200, 500, 300);
    }
}
