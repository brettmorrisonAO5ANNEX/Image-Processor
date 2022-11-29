//package persistence;
//
//import model.Gallery;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.stream.Stream;
//
//public class JsonReaderGallery {
//    private String source;
//
//    //constructs a reader to read from source file
//    public JsonReaderGallery(String source) {
//        this.source = source;
//    }
//
//    //EFFECTS: reads gallery from file and returns it. Throws IOException if an error occurs while reading
//    //         the gallery data from file
//    public Gallery read() throws IOException {
//        String jsonData = readFile(source);
//        JSONObject jsonObject = new JSONObject(jsonData);
//        return parseGallery(jsonObject);
//    }
//
//    //EFFECTS: reads source file as string and return it
//    public String readFile(String source) throws IOException {
//        StringBuilder contentBuilder = new StringBuilder();
//
//        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
//            stream.forEach(contentBuilder::append);
//        }
//        return contentBuilder.toString();
//    }
//
//    //EFFECTS: parses gallery from JSON object and returns it
//    private Gallery parseGallery(JSONObject jsonObject) {
//        Gallery g = new Gallery();
//        addGallery(g, jsonObject);
//        return g;
//    }
//
//    //MODIFIES: g
//    //EFFECTS: parses gallery from JSON object and adds them to a Gallery instance
//    public void addGallery(Gallery g, JSONObject jsonObject) {
//        JSONArray jsonArray = jsonObject.getJSONArray("gallery");
//        for (Object json: jsonArray) {
//            String nextProjectCopy = (String) json;
//            g.addCopyToGallery(String.valueOf(nextProjectCopy));
//        }
//    }
//}
