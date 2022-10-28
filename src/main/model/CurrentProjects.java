package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//represents the set of all projects a user has saved but not committed to final processing
public class CurrentProjects implements Writable {
    private List<String> currentProjects;

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("currentProjects", currentProjects);
        return json;
    }

//    //EFFECTS: returns project names used as a JSON array
//    private JSONArray currentProjectsToJson() {
//        JSONArray jsonArray = new JSONArray();
//        for (String projectName: currentProjects) {
//            jsonArray.put(projectName);
//        }
//        return jsonArray;
//    }

    //EFFECTS: constructs a new CurrentProjects with an empty list of current projects
    public CurrentProjects() {
        this.currentProjects = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds the destination of a current project to the current project list
    public void addToCurrentProjects(String fileDestination) {
        this.currentProjects.add(fileDestination);
    }

    public List<String> getCurrentProjects() {
        return this.currentProjects;
    }
}
