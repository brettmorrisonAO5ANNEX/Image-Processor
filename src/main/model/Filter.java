package model;

public class Filter {
    String filterName;
    int numFilterInstantiations;

    //REQUIRES: filterType can only be instanited 3 times with each intance having a unique name of:
    //          "negative", "mirror", or "pixelate"
    //EFFECTS: creates a filter object with filterName = filterType
    public Filter(String filterType){
        //stub
    }

    //MODIFIES: img
    //EFFECTS: creates sub 2D arrays within img.get(pixels) and averages the RGB values within each sub array to
    //         get a new pixel array with pixelated appearance
    public void pixelate(Image img){
        //stub
    }

    //MODIFIES: img
    //EFFECTS: modifies each pixel in pixels to have RBG values of (255-R), (255-G), (255-B)
    public void negative(Image img){
        //stub
    }

    //MODIFIES: img
    //EFFECTS: reverses pixel position in each row of pixels
    public void mirror(Image img){
        //stub
    }

    public String getFilterName() {
        return ""; //stub
    }
}
