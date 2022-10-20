package model;

import java.util.ArrayList;
import java.util.List;

//represents the set of all projects a user has saved but not committed to final processing
public class CurrentProjects {
    private List<String> currentProjects;

    //EFFECTS: constructs a new CurrentProjects with an empty list of current projects
    public CurrentProjects() {
        this.currentProjects = new ArrayList<>();
    }

    //REQUIRES: string is a destination to a valid json file
    //MODIFIES: this
    //EFFECTS: adds the destination of a current project to the current project list
    public void addToCurrentProjects(String fileDestination) {
        this.currentProjects.add(fileDestination);
    }

    public List<String> getCurrentProjects() {
        return this.currentProjects;
    }
}
