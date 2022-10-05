package model;

import java.util.ArrayList;
import java.util.List;

// represents an instance of the locally stored image with a list of applied filters
public class Image {
    List<Filter> listOfFilter;
    int[][] pixels;

    //TODO: create blank canvas by converting 2D array to array of randomly colored pixels
    //REQUIRES: width, height > 0
    //EFFECTS: creates Image object with 2D pixel array with (width) columns, (height) rows, and
    //         empty listOfFilter;
    public Image(int width, int height) {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: adds filter to an images listOfFilter field
    public void addFilter(Filter filter) {
        //stub
    }

    //REQUIRES: listOfFilter is not empty
    //MODIFIES: this
    //EFFECTS: removes the most recently added filter from listOfFilter
    public void undo() {
        //stub
    }

    //REQUIRES: listOfFilter is not empty
    //MODIFIES: this
    //EFFECTS: clears an images listOfFilter
    public void undoAll() {
        //stub
    }

    //EFFECTS: returns a string containing all filters in order of when they were applied
    public void viewEditHistory() {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: applies each filter in this.listOfFilter to this and updates pixels accordingly
    public void processImage() {
        //stub
    }

    public List<Filter> getlistOfFilter() {
        return this.listOfFilter;
    }

    public int[][] getPixels() {
        return this.pixels;
    }

}


