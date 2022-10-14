package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// represents an instance of the locally stored image with a list of applied filters
public class Image {
    List<Filter> listOfFilter;
    List<String> uniqueFiltersUsed;
    int[][] pixelArray;
    int height;
    int width;
    String imageResult;

    //TODO: update effects clauses to include info about what outputs are produced and how any input values are
    //      changed
    //TODO: check test coverage for all branches
    //TODO: update tests to cover removeFromUnique();
    //TODO: setup starting color constant (or parameter) for single point of control of what color each pixel is
    //      instantiated to be
    //TODO: undo/undotype/undoall should only allow a user to input data IF there are filters in listOfFilter
    //TODO: add cancel option to return back to menu

    //REQUIRES: width, height > 0
    //EFFECTS: instantiates Image object with

    public Image(int width, int height) {
        this.listOfFilter = new ArrayList<>();
        this.uniqueFiltersUsed = new ArrayList<>();
        this.width = width;
        this.height = height;
        this.pixelArray = new int[width * height][3];
        this.imageResult = "";

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
        this.addIfUnique(filter);
    }

    //MODIFIES: this
    //EFFECTS: adds filterName to uniqueFiltersUsed
    public void addIfUnique(Filter filter) {
        String filterType = filter.getFilterName();
        if (!this.uniqueFiltersUsed.contains(filterType)) {
            this.uniqueFiltersUsed.add(filterType);
        }
    }

    //REQUIRES: listOfFilter is not empty
    //MODIFIES: this
    //EFFECTS: removes the most recently added filter from listOfFilter and also removes the corresponding
    //         filter from uniqueFiltersUsed
    public void undoLast() {
        int lastIndex = this.listOfFilter.size() - 1;
        String lastFilter = this.listOfFilter.get(lastIndex).getFilterName();
        this.listOfFilter.remove(lastIndex);
        this.removeFromUnique(lastFilter);
    }

    //REQUIRES: listOfFilter is not empty
    //MODIFIES: this
    //EFFECTS: clears an images listOfFilter and removes it from uniqueFiltersUsed
    public void undoAll() {
        for (int i = 0; i < this.listOfFilter.size(); i++) {
            this.listOfFilter.clear();
            this.uniqueFiltersUsed.clear();
        }
    }

    //REQUIRES: listOfFilter contains filter matching filterName at least once
    //MODIFIES: this
    //EFFECTS: clears all instances of a type of filter from an images listOfFilter and removes the corresponding
    //         filter from uniqueFiltersUsed
    public void removeAllOfType(String filterName) {
        this.listOfFilter.removeIf(f -> (f.getFilterName().equals(filterName)));
        this.removeFromUnique(filterName);
    }

    //REQUIRES: filterName is one of: "mirror" "negative" or "pixelate"
    //MODIFIES: this
    //EFFECTS: removes a given filter name from an Images uniqueFiltersUsed list
    public void removeFromUnique(String filterName) {
        this.uniqueFiltersUsed.remove(listOfFilter);
    }

    //EFFECTS: returns a string containing all filters in order of when they were applied
    public String viewEditHistory() {
        int size = this.listOfFilter.size();
        String[] history = new String[size];

        if (this.listOfFilter.size() == 0) {
            return "[no filters applied]";
        } else {
            for (int i = 0; i < size; i++) {
                String item = this.listOfFilter.get(i).getFilterName();
                history[i] = (i + 1) + ": " + item;
            }
            return Arrays.toString(history);
        }
    }

    //MODIFIES: this
    //EFFECTS: applies each filter in this.listOfFilter to this
    public void processImage() {
        for (Filter filter: this.listOfFilter) {
            if (filter.getFilterName().equals("negative")) {
                filter.negative(this);
            } else if (filter.getFilterName().equals("mirror")) {
                filter.mirror(this);
            }
        }
    }


    //REQUIRES: processImage() has been called on image instance and method is initialized with 0
    //MODIFIES: this
    //EFFECTS: creates imageResult based on processed pixelArray
    public String createVisArray(int row) {
        int currentRowStart = row * width;
        int nextRowStart = currentRowStart + width;

        if (row == height) {
            return imageResult;
        } else {
            this.imageResult = this.createRow(currentRowStart, nextRowStart)
                               +  "\n" + this.createVisArray(row + 1);
        }
        return this.imageResult;
    }

    //MODIFIES: this
    //EFFECTS: creates a string version of an individual pixel within an image's pixel array
    public String createRow(int start, int end) {
        String row = "";
        for (int i = start; i < end; i++) {
            row = row + Arrays.toString(this.pixelArray[i]);
        }
        return row;
    }

    public List<Filter> getListOfFilter() {
        return this.listOfFilter;
    }

    public List<String> getUniqueFiltersUsed() {
        return this.uniqueFiltersUsed;
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


