package persistence;

import model.CurrentProjects;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReaderCurrentProjects {
    private String source;

    //constructs a reader to read from source file
    public JsonReaderCurrentProjects(String source) {
        this.source = source;
    }

    //EFFECTS: reads currentProjects from file and returns it. Throws IOException if an error occurs while reading
    //         the currentProjects data from file
    public CurrentProjects read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCurrentProjects(jsonObject);
    }

    //EFFECTS: reads source file as string and return it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }
        return contentBuilder.toString();
    }

    //EFFECTS: parses current projects from JSON object and returns it
    private CurrentProjects parseCurrentProjects(JSONObject jsonObject) {
        CurrentProjects cp = new CurrentProjects();
        addCurrentProjects(cp, jsonObject);
        return cp;
    }

    //MODIFIES: cp
    //EFFECTS: parses currentProjects from JSON object and adds them to a CurrentProject instance
    public void addCurrentProjects(CurrentProjects cp, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("currentProjects");
        for (Object json: jsonArray) {
            String nextProjectName = (String) json;
            cp.addToCurrentProjects(String.valueOf(nextProjectName));
        }
    }
}
