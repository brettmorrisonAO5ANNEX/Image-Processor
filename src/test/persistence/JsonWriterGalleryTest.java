package persistence;

import model.CurrentProjects;
import model.Gallery;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterGalleryTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            Gallery g = new Gallery();
            JsonWriterGallery writer
                    = new JsonWriterGallery("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (FileNotFoundException e) {
            //pass
        }
    }

    @Test
    public void testWriterEmptyGallery() {
        try {
            Gallery g = new Gallery();
            JsonWriterGallery writer = new JsonWriterGallery("./data/emptyG.json");
            writer.open();
            writer.write(g);
            writer.close();

            JsonReaderGallery reader = new JsonReaderGallery("./data/emptyG.json");
            g = reader.read();
            assertTrue(g.getGallery().isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterNonEmptyGallery() {
        try {
            Gallery g = new Gallery();
            g.addCopyToGallery("image1AC");
            g.addCopyToGallery("image1BC");
            JsonWriterGallery writer = new JsonWriterGallery("./data/emptyG.json");
            writer.open();
            writer.write(g);
            writer.close();

            JsonReaderGallery reader = new JsonReaderGallery("./data/emptyG.json");
            g = reader.read();
            assertEquals("image1AC", g.getGallery().get(0));
            assertEquals("image1BC", g.getGallery().get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
