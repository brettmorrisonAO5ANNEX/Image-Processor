package persistence;

import model.CurrentProjects;
import model.Image;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterCurrentProjectsTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            CurrentProjects cp = new CurrentProjects();
            JsonWriterCurrentProjects writer
                    = new JsonWriterCurrentProjects("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (FileNotFoundException e) {
            //pass
        }
    }

    @Test
    public void testWriterEmptyCurrentProjects() {
        try {
            CurrentProjects cp = new CurrentProjects();
            JsonWriterCurrentProjects writer = new JsonWriterCurrentProjects("./data/emptyCP.json");
            writer.open();
            writer.write(cp);
            writer.close();

            JsonReaderCurrentProjects reader = new JsonReaderCurrentProjects("./data/emptyCP.json");
            cp = reader.read();
            assertTrue(cp.getCurrentProjects().isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterNonEmptyCurrentProjects() {
        try {
            CurrentProjects cp = new CurrentProjects();
            cp.addToCurrentProjects("image1A");
            cp.addToCurrentProjects("image1B");
            JsonWriterCurrentProjects writer = new JsonWriterCurrentProjects("./data/emptyCP.json");
            writer.open();
            writer.write(cp);
            writer.close();

            JsonReaderCurrentProjects reader = new JsonReaderCurrentProjects("./data/emptyCP.json");
            cp = reader.read();
            assertEquals("image1A", cp.getCurrentProjects().get(0));
            assertEquals("image1B", cp.getCurrentProjects().get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
