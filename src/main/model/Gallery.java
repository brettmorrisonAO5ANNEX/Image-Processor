package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//represents a gallery for all images a user commits to final processing (saves a copy of each processed image
//with default fields so a user can choose to edit again from the copy)
public class Gallery implements Writable {
    private List<String> gallery;

    //EFFECTS: constructs a gallery with an empty list of project name copies
    public Gallery() {
        this.gallery = new ArrayList<>();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("gallery", gallery);
        return json;
    }

//    //EFFECTS: returns project copies as a JSON array
//    private JSONArray galleryToJson() {
//        JSONArray jsonArray = new JSONArray();
//        for (String projectCopy: gallery) {
//            jsonArray.put(projectCopy);
//        }
//        return jsonArray;
//    }

    //REQUIRES: string is a valid project name
    //MODIFIES: this
    //EFFECTS: adds the given json file into gallery
    public void addCopyToGallery(String projectName) {
        this.gallery.add(projectName);
    }

    public List<String> getGallery() {
        return this.gallery;
    }

}
