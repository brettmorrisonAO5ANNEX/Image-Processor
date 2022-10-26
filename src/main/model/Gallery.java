package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//represents a gallery for all images a user commits to final processing
public class Gallery implements Writable {
    private List<String> gallery;

    //EFFECTS: constructs a gallery with an empty list of jsonFile destinations
    public Gallery() {
        this.gallery = new ArrayList<>();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("gallery", gallery);
        return json;
    }

    //EFFECTS: returns project copies as a JSON array
    private JSONArray galleryToJson() {
        JSONArray jsonArray = new JSONArray();
        for (String projectCopy: gallery) {
            jsonArray.put(projectCopy);
        }
        return jsonArray;
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
