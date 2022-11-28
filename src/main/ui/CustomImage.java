package ui;

import model.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//represents a custom image that a user chooses (stores locally in data file) and is converted to Image type
//so filters can be applied to it
public class CustomImage {
    private String fileSource;
    private Image customImage;

    public CustomImage(String fileSource) {
        this.fileSource = fileSource;
    }

    //MODIFIES: this
    //EFFECTS: writes pixel data from chosen image to new int[][] which is then used to construct a new Image object
    //         for the custom image
    public Image writeCustomToImage() throws FailToWriteCustom {
        BufferedImage custom = null;

        try {
            custom = ImageIO.read(new File(fileSource));
        } catch (IOException e) {
            throw new FailToWriteCustom();
        }

        //reads custom RGB data from chosen image and converts it into int[][] to set as image RGB
        getAndSetRGG(custom);
        return customImage;
    }

    //INSPIRATION SOURCE:
    //https://www.tutorialspoint.com/how-to-get-pixels-rgb-values-of-an-image-using-java-opencv-library
    //MODIFIES: this
    //EFFECTS: retrieves pixel data from custom image and writes it to new int[][] then sets that array in new Image
    //         object
    private void getAndSetRGG(BufferedImage custom) {
        //creates pixel array with proper dimensions for custom image
        int[][] customArray = new int[custom.getWidth() * custom.getHeight()][3];

        //creates new Image object with proper dimensions
        int width = custom.getWidth();
        int height = custom.getHeight();
        Image customImage = new Image(width, height);
        this.customImage = customImage;

        //iterates through custom image to gather pixel data
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                //get current index in array from row and column values
                int currIndex = c + (r * custom.getWidth());

                //gets color of current pixel in custom image
                int currPixel = custom.getRGB(c, r);

                //creates color from pixel data in currPixel in custom image
                Color currColor = new Color(currPixel, true);

                //retrieves individual RGB values from currPixel color
                int redComp = currColor.getRed();
                int greenComp = currColor.getGreen();
                int blueComp = currColor.getBlue();

                //set corresponding RGB val in custom Array
                customArray[currIndex][0] = redComp;
                customArray[currIndex][1] = greenComp;
                customArray[currIndex][2] = blueComp;
            }
        }

        //update customImage array with new image array from custom image
        customImage.setPixelArray(customArray);
    }

    public Image getCustomImage() {
        return this.customImage;
    }
}
