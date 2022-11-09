package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

// represents an instance of an image with a list of applied filters
public class Image implements Writable {
    private List<Filter> listOfFilter;
    private List<String> uniqueFiltersUsed;
    int[][] pixelArray;
    private int width;
    private int height;
    private String compChoice;
    private String imageResult;

    //REQUIRES: width, height > 0
    //EFFECTS: instantiates Image object with pixelArrray of length width*height and default values of
    //         "" for image result and 0 for degreeOfPixelation
    public Image(int width, int height) {
        this.listOfFilter = new ArrayList<>();
        this.uniqueFiltersUsed = new ArrayList<>();
        this.pixelArray = new int[width * height][3];
        this.width = width;
        this.height = height;
        this.imageResult = "";
        for (int r = 0; r < this.pixelArray.length; r++) {
            for (int c = 0; c < this.pixelArray[0].length; c++) {
                this.pixelArray[r][c] = 255;
            }
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("listOfFilter", listOfFilterToJson());
        json.put("width", width);
        json.put("height", height);
        json.put("compChoice", compChoice);
        json.put("imageResult", imageResult);
        return json;
    }

    //EFFECTS: returns filters used in this image as a JSON array
    private JSONArray listOfFilterToJson() {
        JSONArray jsonArrayLOF = new JSONArray();
        for (Filter f: listOfFilter) {
            jsonArrayLOF.put(f.toJson());
        }
        return jsonArrayLOF;
    }

    //MODIFIES: this
    //EFFECTS: adds filter to an images listOfFilter field
    public void addFilter(Filter filter) {
        this.listOfFilter.add(filter);
        this.addIfUnique(filter);
    }

    //MODIFIES: this
    //EFFECTS: adds filterName to uniqueFiltersUsed if it is not already in the list
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
        if (0 < this.listOfFilter.size()) {
            int lastIndex = this.listOfFilter.size() - 1;
            String lastFilter = this.listOfFilter.get(lastIndex).getFilterName();
            this.listOfFilter.remove(lastIndex);
            this.removeFromUnique(lastFilter);
        }
    }

    //REQUIRES: listOfFilter is not empty
    //MODIFIES: this
    //EFFECTS: clears an images listOfFilter and removes it from uniqueFiltersUsed
    public void undoAll() {
        if (0 < this.listOfFilter.size()) {
            for (int i = 0; i < this.listOfFilter.size(); i++) {
                this.listOfFilter.clear();
                this.uniqueFiltersUsed.clear();
            }
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
        if (filterName.equals("mirror") || filterName.equals("negative") || filterName.equals("pixelate")) {
            this.uniqueFiltersUsed.remove(filterName);
        }
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
                if (item.equals("colorGradient")) {
                    item = item + "(gradient for: " + this.compChoice + ")";
                }
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
            } else if (filter.getFilterName().equals("colorGradient")) {
                filter.colorGradient(compChoice, this);
            } else {
                filter.pixelate(this);
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
        StringBuilder row = new StringBuilder();
        for (int i = start; i < end; i++) {
            row.append(Arrays.toString(this.pixelArray[i]));
        }
        return row.toString();
    }

    //SOURCE FOR RANDOM INT-IN-RANGE GENERATION:
    //https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
    //MODIFIES: this
    //EFFECTS: randomizes each pixel's RGB value in this.pixelArray to create image variety
    public void randomizeColor() {
        int totalNumElem = this.width * this.height;
        for (int r = 0; r < totalNumElem; r++) {
            for (int c = 0; c < 3; c++) {
                this.pixelArray[r][c] = ThreadLocalRandom.current().nextInt(0, 256);
            }
        }
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
    

    public void setImageResult(String imageResult) {
        this.imageResult = imageResult;
    }

    public String getImageResult() {
        return this.imageResult;
    }

    public String getCompChoice() {
        return this.compChoice;
    }

    public void setCompChoice(String chosenComp) {
        this.compChoice = chosenComp;
    }
}


