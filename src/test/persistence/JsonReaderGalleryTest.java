package persistence;

import model.CurrentProjects;
import model.Gallery;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderGalleryTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReaderGallery reader = new JsonReaderGallery("./data/noFile.json");
        try {
            Gallery g = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    public void testReaderEmptyGallery() {
        JsonReaderGallery reader = new JsonReaderGallery("./data/testReaderEmptyG.json");

        try {
            Gallery g = reader.read();
            assertTrue(g.getGallery().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderNonEmptyGallery() {
        JsonReaderGallery reader = new JsonReaderGallery("./data/testReaderNonEmptyG.json");

        try {
            Gallery g = reader.read();
            assertEquals("image1AC", g.getGallery().get(0));
            assertEquals("image1BC", g.getGallery().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
