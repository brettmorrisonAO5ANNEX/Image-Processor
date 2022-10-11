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

    //REQUIRES: first mirror call MUST be with row = 0
    //MODIFIES: this
    //EFFECTS: creates mirror of image by swapping pixel positions across the array in each column
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

    public String getFilterName() {
        return this.filterName;
    }
}
