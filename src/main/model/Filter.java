package model;

import static java.lang.Math.*;

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

    //TODO: [0 =< degPix =< (log(minDim) / log(2))]
    //REQUIRES: image width and height must both be even
    //MODIFIES: this
    //EFFECTS: creates pixelated version of image according to user's specifications
    public void pixelate(Image img, int degPix) {
        int apparentDegPix = createApparentDegPix(img, degPix);
        int subSectionWidth = createSubSectionWidth(img, apparentDegPix);
        int subSectionHeight = createSubsectionHeight(img, apparentDegPix);
        int numElemInSubSection = subSectionWidth * subSectionHeight;
        int[][] downSizedArray = new int[(int) pow(4, apparentDegPix)][3];
        int dimOfDSA = (int) sqrt(downSizedArray.length);
        int[][] upsizedPixelatedArray = new int[img.width * img.height][3];

        //CREATES DOWNSIZED ARRAY
        //iterate as many times as there are rows in the downsized array
        for (int r = 0; r < dimOfDSA; r++) {
            //iterate as many times as there are columns in the downsized array
            //EFFECT: changes element at current position in downsized array to be the corresponding
            //        element in the original img.pixelArray
            for (int c = 0; c < dimOfDSA; c++) {
                int currPosInDSA = (r * numElemInSubSection) + c;
                int corrPosInPixArray = (int) ((c * subSectionWidth) + (r * pow(numElemInSubSection, 2)));
                downSizedArray[currPosInDSA] = img.pixelArray[corrPosInPixArray];
            }
        }

        //CREATES UPSIZED ARRAY THE SAME SIZE AS ORIGINAL img.pixelArray
        //itereate as manu times as there are items in the downsized array
        for (int e = 0; e < downSizedArray.length; e++) {
            //iterate as many times as there are rows in each subspace
            for (int r = 0; r < subSectionHeight; r++) {
                //iterate as many times as there are columns in each subspace
                for (int c = 0; c < subSectionWidth; c++) {
                    int[] currElemInDSA = downSizedArray[e];

                    //TODO: how to incorporate (e) into currPosUSA?

                    int currPosInUSA = ((r * numElemInSubSection) + c);
                    upsizedPixelatedArray[currPosInUSA] = downSizedArray[e];
                }
            }
        }
    }

    //TODO: test this method
    //EFFECTS: returns the apparent degree of pixelation relative to users input - makes user choices for
    //         degree of pixelation more intuitive as degree 0 would intuitively correspond to the lowest amount
    //         of pixelation but this is the reverse in the algorithm, so I chose to invert them
    public static int createApparentDegPix(Image img, int degInt) {
        int apparentDegPix = (int) ((log(min(img.width, img.height)) / log(2)) - degInt);
        return apparentDegPix;
    }

    //TODO: test this method
    //EFFECTS: returns grid subsection width based on apparent degree of pixelation
    public static int createSubSectionWidth(Image img, int apparentDegPix) {
        int subSectionWidth;
        if (apparentDegPix == 0) {
            subSectionWidth = img.width;
        } else {
            subSectionWidth = (int) (img.width / pow(2, apparentDegPix));
        }
        return subSectionWidth;
    }

    //TODO: test this method
    //EFFECTS: returns grid subsection height based on apparent degree of pixelation
    public static int createSubsectionHeight(Image img, int apparentDegPix) {
        int subSectionHeight;
        if (apparentDegPix == 0) {
            subSectionHeight = img.height;
        } else {
            subSectionHeight = (int) (img.height / pow(2, apparentDegPix));
        }
        return subSectionHeight;
    }

    public String getFilterName() {
        return this.filterName;
    }
}
