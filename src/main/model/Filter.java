package model;

public class Filter {
    String filterName;

    //REQUIRES: filterType is one of "pixelate", "negative", "mirror"
    //EFFECTS: creates a filter object with fiterName = filterType
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
    //EFFECTS:
    public void mirror(Image img){
        //stub
    }
}
