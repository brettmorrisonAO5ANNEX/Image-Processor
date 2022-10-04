package model;

public class Filter {

    //EFFECTS: instantiates a filter object
    public Filter() {
        //stub
    }

    //MODIFIES: img
    //EFFECTS: modifies each pixel to have rgb values of (255-r), (255-g), (255-b)
    public void imageNegative(Image img) {
        //stub
    }

    //MODIFIES: img
    //EFFECTS: creates a mirror image of img by swapping pixel positions within rows of the pixel array
    public void mirrorImage(Image img) {
        //stub
    }

    //MODIFIES: img
    //EFFECTS: alters img by splitting img into grid and averaging rgb values of pixels within each grid
    //         to create pixelated image
    public void pixelateImage(Image img) {
        //stub
    }
}
