package persistence;

import model.Filter;
import model.Image;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

//CODE PARTIALLY MODELED AFTER: JsonSerializationDemo:
//                              https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//represents test class for JsonWriter
public class JsonWriterTest {

    @Test
    public void testWriterInvalidFile() {
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
    public void testWriterNewImage() {
        try {
            Image img = new Image(1, 2);
            img.setCompChoice("blue");
            JsonWriter writer = new JsonWriter("./data/testWriterNewImage.json");
            writer.open();
            writer.write(img);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNewImage.json");
            img = reader.read();
            assertTrue(img.getListOfFilter().isEmpty());
            assertTrue(img.getUniqueFiltersUsed().isEmpty());
            assertEquals(2, img.getPixelArray().length);
            assertEquals(1, img.getImageWidth());
            assertEquals(2, img.getImageHeight());
            assertEquals("blue", img.getCompChoice());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterPartiallyEditedImage() {
        try {
            Image img = new Image(4,4);
            img.addFilter(new Filter("negative"));
            img.addFilter(new Filter("pixelate"));
            img.addFilter(new Filter("colorGradient"));
            img.setCompChoice("blue");
            img.addFilter(new Filter("mirror"));
            img.undoLast();
            JsonWriter writer = new JsonWriter("./data/testWriterPartiallyEditedImage.json");
            writer.open();
            writer.write(img);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterPartiallyEditedImage.json");
            img = reader.read();
            assertEquals(3, img.getListOfFilter().size());
            assertEquals("negative", img.getListOfFilter().get(0).getFilterName());
            assertEquals("pixelate", img.getListOfFilter().get(1).getFilterName());
            assertEquals("colorGradient", img.getListOfFilter().get(2).getFilterName());
            assertEquals(3, img.getUniqueFiltersUsed().size());
            assertEquals("negative", img.getUniqueFiltersUsed().get(0));
            assertEquals("pixelate", img.getUniqueFiltersUsed().get(1));
            assertEquals("colorGradient", img.getUniqueFiltersUsed().get(2));
            assertEquals(4, img.getImageWidth());
            assertEquals(4, img.getImageHeight());
            assertEquals("blue", img.getCompChoice());
            assertEquals(16, img.getPixelArray().length);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
