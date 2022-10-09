package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// represents an instance of the locally stored image with a list of applied filters
public class Image {
    List<Filter> listOfFilter;
    int height;
    int width;
    int[][] pixelArray;

    //todo: setup starting color constant (or parameter) for single point of control of what color each pixel is
    //      instantiated to be

    //REQUIRES: width, height > 0
    //EFFECTS: creates Image object with 2D pixel array with (width) columns, (height) rows, and
    //         empty listOfFilter (each pixel has color value in hex code)
    public Image(int width, int height) {
        this.listOfFilter = new ArrayList<>();
        this.width = width;
        this.height = height;
        this.pixelArray = new int[width * height][3];

        for (int r = 0; r < this.pixelArray.length; r++) {
            for (int c = 0; c < this.pixelArray[0].length; c++) {
                this.pixelArray[r][c] = 255;
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: adds filter to an images listOfFilter field
    public void addFilter(Filter filter) {
        this.listOfFilter.add(filter);
    }

    //REQUIRES: listOfFilter is not empty
    //MODIFIES: this
    //EFFECTS: removes the most recently added filter from listOfFilter
    public void undoLast() {
        int size = this.listOfFilter.size() - 1;
        this.listOfFilter.remove(size);
    }

    //REQUIRES: listOfFilter is not empty
    //MODIFIES: this
    //EFFECTS: clears an images listOfFilter
    public void undoAll() {
        for (int i = 0; i < this.listOfFilter.size(); i++) {
            this.listOfFilter.clear();
        }
    }

    //REQUIRES: listOfFilter contains filter matching filterName at least once
    //MODIFIES: this
    //EFFECTS: clears all instances of a type of filter from an images listOfFilter
    public void removeAllOfType(String filterName) {
        this.listOfFilter.removeIf(f -> (f.getFilterName() == filterName));
    }

    //EFFECTS: returns a string containing all filters in order of when they were applied
    public String viewEditHistory() {
        int size = this.listOfFilter.size();
        String[] history = new String[size];

        if (this.listOfFilter.size() == 0) {
            return "no filters applied";
        } else {
            for (int i = 0; i < size; i++) {
                String item = this.listOfFilter.get(i).getFilterName();
                history[i] = (i + 1) + ": " + item;
            }
            return Arrays.toString(history);
        }
    }

    //MODIFIES: this
    //EFFECTS: applies each filter in this.listOfFilter to this and updates pixels accordingly
    public void processImage() {
        for (Filter filter: this.listOfFilter) {
            if (filter.getFilterName() == "negative") {
                filter.negative(this);
            }
        }
    }

    public List<Filter> getlistOfFilter() {
        return this.listOfFilter;
    }

    public int[][] getPixelArray() {
        return this.pixelArray;
    }

    public int getImageHeight() {
        return this.height;
    }

    public int getImageWidth() {
        return this.width;
    }
}


