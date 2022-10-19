package persistence;

import model.Image;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

//CODE PARTIALLY MODELED AFTER: JsonSerializationDemo:
//                              https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//represents test class for JsonReader
public class JsonReaderTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noFile.json");
        try {
            Image img = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testReaderNewImage() {
        JsonReader reader = new JsonReader("./data/testReaderNewImage.json");
        try {
            Image img = reader.read();
            assertTrue(img.getListOfFilter().isEmpty());
            assertTrue(img.getUniqueFiltersUsed().isEmpty());
            assertEquals(2, img.getPixelArray().length);
            assertEquals(1, img.getImageWidth());
            assertEquals(2, img.getImageHeight());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderPartiallyEditedImage() {
        try {
            JsonReader reader = new JsonReader("./data/testReaderPartiallyEditedImage.json");
            Image img = reader.read();
            assertEquals(3, img.getListOfFilter().size());
            assertEquals("negative", img.getListOfFilter().get(0).getFilterName());
            assertEquals("mirror", img.getListOfFilter().get(1).getFilterName());
            assertEquals("pixelate", img.getListOfFilter().get(2).getFilterName());
            assertEquals(2, img.getDegreeOfPixelation());
            assertEquals(3, img.getUniqueFiltersUsed().size());
            assertEquals("negative", img.getUniqueFiltersUsed().get(0));
            assertEquals("mirror", img.getUniqueFiltersUsed().get(1));
            assertEquals("pixelate", img.getUniqueFiltersUsed().get(2));
            assertEquals(4, img.getImageWidth());
            assertEquals(4, img.getImageHeight());
            assertEquals(16, img.getPixelArray().length);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
