package model;

public class Filter {
    String filterName;

    //REQUIRES: filterType can only be one of: "negative", "mirror", "pixelate"
    //EFFECTS: creates a filter object with filterName = filterType
    public Filter(String filterType) {
        this.filterName = filterType;
    }

    //MODIFIES: img
    //EFFECTS: modifies each pixel in pixels to have RBG values of (255-R), (255-G), (255-B)
    public void negative(Image img) {
        for (int r = 0; r < img.pixelArray.length; r++) {
            for (int c = 0; c < img.pixelArray[0].length; c++) {
                img.pixelArray[r][c] = 255 - img.pixelArray[r][c];
            }
        }
    }

//    //MODIFIES: img
//    //EFFECTS: creates sub 2D arrays within img.get(pixels) and averages the RGB values within each sub array to
//    //         get a new pixel array with pixelated appearance
//    public void pixelate(Image img){
//        //stub
//
//    //MODIFIES: img
//    //EFFECTS: reverses pixel position in each row of pixels
//    public void mirror(Image img){
//        //stub
//    }

    public String getFilterName() {
        return this.filterName;
    }
}
