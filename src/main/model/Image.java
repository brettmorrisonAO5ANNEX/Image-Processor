package model;

import java.util.ArrayList;
import java.util.List;

// represents an instance of the locally stored image with a list of applied filters
public class Image {
    List<String> listOfFilter;
    int[][] pixels;

    //TODO: create blank canvas by converting 2D array to array of randomly colored pixels
    //REQUIRES: width, height > 0
    //EFFECTS: creates Image object with 2D pixel array with (width) columns, (height) rows, and
    //         empty listOfFilter;
    public Image(int width, int height) {
        //stub
    }

    //TODO: maybe scratch add noise if each new image canvas is already randomized
    //REQUIRES: filter string must be one of "pixelate", "negative", "add noise", "mirror"
    //MODIFIES: this
    //EFFECTS: adds filter to an images listOfFilter field
    public void addFilter(String filter) {
        //stub
    }

    //REQUIRES: listOfFilter is not empty
    //MODIFIES: this
    //EFFECTS: removes the most recently added filter from listOfFilter
    public void undo() {
        //stub
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public void undoAll() {
        //stub
    }

}


