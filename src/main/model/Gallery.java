package model;

import java.util.ArrayList;
import java.util.List;

//represents a gallery for all images a user commits to final processing
public class Gallery {
    private List<String> gallery;

    //EFFECTS: constructs a gallery with an empty list of jsonFile destinations
    public Gallery() {
        this.gallery = new ArrayList<>();
    }

    //REQUIRES: string is a valid project name
    //MODIFIES: this
    //EFFECTS: adds the given json file into gallery
    public void addImageToGallery(String projectName) {
        this.gallery.add(projectName);
    }

    public List<String> getGallery() {
        return this.gallery;
    }
}
