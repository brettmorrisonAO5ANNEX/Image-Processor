package model;

import java.util.ArrayList;
import java.util.List;

// represents an instance of the locally stored image with a list of applied filters
public class Image {
    List<Filter> listOfFilter;
    int height;
    int width;
    List<int[]> pixelArray;

    //REQUIRES: width, height > 0
    //EFFECTS: creates Image object with 2D pixel array with (width) columns, (height) rows, and
    //         empty listOfFilter (each pixel has color value in hex code)
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
    public void undoLast() {
        //stub
    }

    //REQUIRES: listOfFilter is not empty
    //MODIFIES: this
    //EFFECTS: clears an images listOfFilter
    public void undoAll() {
        //stub
    }

    //REQUIRES: listOfFilter contains filter matching filterName at least once
    //MODIFIES: this
    //EFFECTS: clears all instances of a type of filter from an images listOfFilter
    public void removeAllOfOne(Filter filter) {
        //stub
    }

    //EFFECTS: returns a string containing all filters in order of when they were applied
    public String viewEditHistory() {
        return "";
    }

    //MODIFIES: this
    //EFFECTS: applies each filter in this.listOfFilter to this and updates pixels accordingly
    public void processImage() {
        //stub
    }

    public List<Filter> getlistOfFilter() {
        return this.listOfFilter;
    }

    public List<int[]> getPixelArray() {
        return this.pixelArray;
    }

    public int getImageHeight() {
        return this.height;
    }

    public int getImageWidth() {
        return this.width;
    }
}


