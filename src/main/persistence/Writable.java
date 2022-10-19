package persistence;

import org.json.JSONObject;

//CODE MODELED AFTER: JsonSerializationDemo:
//                    https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//represents an interface for toJson() method which is implemented by Image and Filter
public interface Writable {
    //EFFECTS: returns this as JSON object

    JSONObject toJson();
}

