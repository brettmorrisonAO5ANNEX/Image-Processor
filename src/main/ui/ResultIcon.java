package ui;

import model.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

//represents icon version of final product to be displayed in the viewResultPanel
public class ResultIcon extends ImageIcon {
    private int[][] pixArray;
    private ViewResultPanel viewResultPanel;

    public ResultIcon(Image myImage, ViewResultPanel viewResultPanel, ImageAppGUI iaGUI) {
        GridBagConstraints c = new GridBagConstraints();
        //setup viewResultPanel to place result
        this.viewResultPanel = viewResultPanel;

        //get updated pixel array for final product
        myImage.processImage();
        pixArray = myImage.getPixelArray();

        //create BufferedImage
        BufferedImage result = new BufferedImage(myImage.getImageWidth(),
                myImage.getImageHeight(),
                BufferedImage.TYPE_INT_RGB);

        //update RGB and creates/adds label out of new icon created from repainted result BufferedImage
        ImageIcon resultIcon = new ImageIcon(paintResult(result));
        JLabel resultLabel = new JLabel(resultIcon);
        resultLabel.setBorder(BorderFactory.createEmptyBorder());

        c.gridwidth = 1;
        c.gridx = 2;

        viewResultPanel.add(resultLabel, c);
        iaGUI.add(viewResultPanel);
    }

    //MODIFIES: result
    //EFFECTS: paints result with rgb values from project pix array
    public BufferedImage paintResult(BufferedImage result) {
        for (int i = 0; i < result.getWidth(); i++) {
            for (int j = 0; j < result.getHeight(); j++) {
                //gets corresponding index in projects pixArray
                int pixArrayIndex = i + result.getWidth() * j;
                int redComp = pixArray[pixArrayIndex][0];
                int greenComp = pixArray[pixArrayIndex][1];
                int blueComp = pixArray[pixArrayIndex][2];
                result.setRGB(i, j, new Color(redComp, greenComp, blueComp).getRGB());
            }
        }
        return result;
    }
}
