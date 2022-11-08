package ui;

import model.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

//represents icon version of original image before applying any filters/edits
public class OriginalIcon extends ImageIcon {
    private int[][] pixArray;
    private ResultIcon resultIcon;
    private ViewResultPanel viewResultPanel;

    public OriginalIcon(Image myImage, ImageAppGUI iaGUI) {
        GridBagConstraints c = new GridBagConstraints();
        //setup viewResultPanel to place result
        viewResultPanel = new ViewResultPanel();

        //get original pixel array for project
        pixArray = myImage.getPixelArray();

        //create BufferedImage
        BufferedImage original = new BufferedImage(myImage.getImageWidth(),
                myImage.getImageHeight(),
                BufferedImage.TYPE_INT_RGB);

        //update RGB and creates/adds label out of new icon created from repainted result BufferedImage
        ImageIcon originalIcon = new ImageIcon(paintResult(original));
        JLabel originalLabel = new JLabel(originalIcon);
        originalLabel.setBorder(BorderFactory.createEmptyBorder());

        c.gridwidth = 1;
        c.gridx = 0;

        viewResultPanel.add(originalLabel, c);
        createArrowLabel();
        resultIcon = new ResultIcon(myImage, viewResultPanel, iaGUI);
    }

    //MODIFIES: result
    //EFFECTS: paints result with rgb values from project pix array
    public BufferedImage paintResult(BufferedImage original) {
        for (int i = 0; i < original.getWidth(); i++) {
            for (int j = 0; j < original.getHeight(); j++) {
                //gets corresponding index in projects pixArray
                int pixArrayIndex = i + original.getWidth() * j;
                int redComp = pixArray[pixArrayIndex][0];
                int greenComp = pixArray[pixArrayIndex][1];
                int blueComp = pixArray[pixArrayIndex][2];
                original.setRGB(i, j, new Color(redComp, greenComp, blueComp).getRGB());
            }
        }
        return original;
    }

    //MODIFIES viewResultPanel
    //EFFECTS: creates and adds simple array icon to separate original and final products
    private void createArrowLabel() {
        GridBagConstraints c = new GridBagConstraints();
        JLabel arrow = new JLabel("---->");

        c.gridwidth = 1;
        c.gridx = 1;

        viewResultPanel.add(arrow, c);
    }
}
