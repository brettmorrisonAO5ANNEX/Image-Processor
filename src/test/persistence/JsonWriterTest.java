package persistence;

import model.Image;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Image img = new Image(1, 1);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (FileNotFoundException e) {
           //pass
        }
    }

    @Test
    void testWriterNewImage() {
        //stub
    }

    @Test
    void testWriterPartiallyEditedImage() {
        //stub
    }
}
