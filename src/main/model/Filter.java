package model;

public class Filter {
    String filterName;

    //REQUIRES: filterType can only be one of: "negative", "mirror", "pixelate"
    //EFFECTS: instantiates a filter object
    public Filter(String filterType) {
        this.filterName = filterType;
    }

    //MODIFIES: img
    //EFFECTS: negates each pixel's RGB values in img
    public void negative(Image img) {
        for (int r = 0; r < img.pixelArray.length; r++) {
            for (int c = 0; c < img.pixelArray[0].length; c++) {
                img.pixelArray[r][c] = 255 - img.pixelArray[r][c];
            }
        }
    }

    //REQUIRES: first mirror call MUST be with row = 0
    //MODIFIES: this
    //EFFECTS: creates mirror of image
    public void mirror(Image img) {
        int[][] mirroredArray = new int[img.width * img.height][3];
        for (int r = 0; r < img.height; r++) {
            int rowStart = r * img.width;
            int rowEnd = img.width * (r + 1) - 1;
            for (int i = 0; i < img.width; i++) {
                mirroredArray[i + rowStart] = img.pixelArray[rowEnd - i];
            }
        }
        img.pixelArray = mirroredArray;
    }

    //REQUIRES: image width and height must both be even
    //MODIFIES: this
    //EFFECTS: creates pixelated version of image according to user's specifications
    public void pixelate() {
        //stub
    }

    public String getFilterName() {
        return this.filterName;
    }
}
