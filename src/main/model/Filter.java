package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Arrays;

import static java.lang.Math.*;

//represents a filter with name filterName that can be applied to an image
public class Filter implements Writable {
    private String filterName;

    //REQUIRES: filterType can only be one of: "negative", "mirror", "pixelate"
    //EFFECTS: instantiates a filter object
    public Filter(String filterType) {
        this.filterName = filterType;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", filterName);
        return json;
    }

    //MODIFIES: img
    //EFFECTS: negates each pixel's RGB values in img
    public void negative(Image img) {
        for (int r = 0; r < img.getPixelArray().length; r++) {
            for (int c = 0; c < img.getPixelArray()[0].length; c++) {
                img.getPixelArray()[r][c] = 255 - img.getPixelArray()[r][c];
            }
        }
    }

    //REQUIRES: first mirror call MUST be with row = 0
    //MODIFIES: img
    //EFFECTS: creates mirror of image
    public void mirror(Image img) {
        int[][] mirroredArray = new int[img.getImageWidth() * img.getImageHeight()][3];
        for (int r = 0; r < img.getImageHeight(); r++) {
            int rowStart = r * img.getImageWidth();
            int rowEnd = img.getImageWidth() * (r + 1) - 1;
            for (int i = 0; i < img.getImageWidth(); i++) {
                mirroredArray[i + rowStart] = img.getPixelArray()[rowEnd - i];
            }
        }
        img.pixelArray = mirroredArray;
    }

    //REQUIRES: input rgbComponent must be one of "red" "green" or "blue"
    //MODIFIES: img
    //EFFECTS: creates image with chosen rgb component decreasing in intensity with each row in pix array
    public void colorGradient(String rgbComponent, Image img) {
        int rgbIndex = getRGBindex(rgbComponent);
        int incrementAmount;

        //normalized increment amount to 1 if there are > 255 rows in the image, otherwise assigns increment
        //amount as normal
        if (round((255 / img.getImageHeight())) < 1) {
            incrementAmount = 1;
        } else {
            incrementAmount = round((255 / img.getImageHeight()));
        }

        for (int c = 0; c < img.getImageWidth(); c++) {
            for (int r = 0; r < img.getImageHeight(); r++) {
                int pixIndex = c + (r * img.getImageWidth());
                int currCompIntensity = (r + 1) * incrementAmount;

                img.getPixelArray()[pixIndex][rgbIndex] = (currCompIntensity);
            }
        }
    }

    //EFFECTS: returns position in pixel int[] corresponding to chosen color component
    private int getRGBindex(String rgbComponent) {
        if (rgbComponent.equals("red")) {
            return 0;
        } else if (rgbComponent.equals("green")) {
            return 1;
        } else {
            return 2;
        }
    }

    //MODIFIES: img
    //EFFECTS: creates standard pixelated image from img
    public void pixelate(Image img) {
        //represents: standard number of even divisions (by 2) to be performed on given image
        int numDivisions = getNumDivisions(img);

        //represents: dimensions of each subSection within image pixelArray after being "divided" numDivisions times
        int subSectionWidth = (int) (img.getImageWidth() / pow(2, numDivisions));
        int subSectionHeight = (int) (img.getImageHeight() / pow(2, numDivisions));

        //represents: the number of pseudo rows and pseudo columns in a theoretical array where each subsection is
        //            treated as a singular unit
        int pseudoColumns = img.getImageWidth() / subSectionWidth;
        int pseudoRows = img.getImageHeight() / subSectionHeight;

        //represents: pseudo column-major traversal through theoretical array where each subsection is treated
        //            as a singular unit
        for (int pseuCol = 0; pseuCol < pseudoColumns; pseuCol++) {
            for (int pseuRow = 0; pseuRow < pseudoRows; pseuRow++) {
                gatherAndWriteAverageRGB(img, subSectionWidth, subSectionHeight, pseuCol, pseuRow);
            }
        }
    }

    //EFFECTS: determines standard number of (even) divisions for given image (2 less than the max for an image with
    //         even width and height)
    public int getNumDivisions(Image img) {
        int minDimension = min(img.getImageWidth(), img.getImageHeight());
        int maxDivisions = (int) (log(minDimension) / log(2));

        if (maxDivisions > 2) {
            return maxDivisions - 2;
        } else {
            return 1;
        }
    }

    //MODIFIES: img
    //EFFECTS: gathers average rgb values for each subsection and then writes those values to all pixels in current
    //         subsection
    private void gatherAndWriteAverageRGB(Image img, int ssWidth, int ssHeight, int psueCol, int pseuRow) {
        int redCompAverage = 0;
        int greenCompAverage = 0;
        int blueCompAverage = 0;

        //number of pixels in each subsection (used for calculating average RGB values)
        int numPixels = ssWidth * ssHeight;

        //row-major traversal to reach each pixel in the current subsection
        for (int r = 0; r < ssHeight; r++) {
            for (int c = 0; c < ssWidth; c++) {

                //get the row and column in non-pseudo array of current element
                int scaledCol = c + (psueCol * ssWidth);
                int scaledRow = r + (pseuRow * ssHeight);

                //determine index of current element in non-pseudo array (img pixelArray)
                int currIndex = scaledCol + (scaledRow * img.getImageWidth());

                //sum all RGB values in corresponding variable
                redCompAverage += img.getPixelArray()[currIndex][0];
                greenCompAverage += img.getPixelArray()[currIndex][1];
                blueCompAverage += img.getPixelArray()[currIndex][2];
            }
        }

        //calculate averages and send them to be re-written to image
        redCompAverage = (int) ceil(redCompAverage / numPixels);
        greenCompAverage = (int) ceil(greenCompAverage / numPixels);
        blueCompAverage = (int) ceil(blueCompAverage / numPixels);

        writeAverageRGB(img, ssWidth, ssHeight, psueCol, pseuRow, redCompAverage, greenCompAverage, blueCompAverage);
    }

    //MODIFIES: img
    //EFFECTS: writes each average RGB value to its corresponding index in each pixel within the current subsection
    private void writeAverageRGB(Image img, int ssWidth, int ssHeight,
                                 int psueCol, int pseuRow,
                                 int redCompAverage, int greenCompAverage, int blueCompAverage) {

        //row-major traversal to reach each pixel in the current subsection
        for (int r = 0; r < ssHeight; r++) {
            for (int c = 0; c < ssWidth; c++) {

                //get the row and column in non-pseudo array of current element
                int scaledCol = c + (psueCol * ssWidth);
                int scaledRow = r + (pseuRow * ssHeight);

                //determine index of current element in non-pseudo array (img pixelArray)
                int currIndex = scaledCol + (scaledRow * img.getImageWidth());

                //write each average RGB value to corresponding index in current pixel
                img.getPixelArray()[currIndex][0] = redCompAverage;
                img.getPixelArray()[currIndex][1] = greenCompAverage;
                img.getPixelArray()[currIndex][2] = blueCompAverage;
            }
        }
    }

    public String getFilterName() {
        return this.filterName;
    }
}
