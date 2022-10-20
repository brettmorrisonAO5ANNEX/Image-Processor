package persistence;

import model.CurrentProjects;
import model.Image;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriterCurrentProjects {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    //EFFECTS: constructs a JsonWriter to write to destination file
    public JsonWriterCurrentProjects(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECTS: opens writer or throws FileNotFoundException if file cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of currentProjects to file
    public void write(CurrentProjects cp) {
        JSONObject json = cp.toJson();
        saveToFile(json.toString(TAB));
    }

    //MODIFIES: this
    //EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    //MODIFIES: this
    //EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
