package persistence;

import model.CurrentProjects;
import model.Image;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderCurrentProjectsTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReaderCurrentProjects reader = new JsonReaderCurrentProjects("./data/noFile.json");
        try {
            CurrentProjects cp = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    public void testReaderEmptyCurrentProjects() {
        JsonReaderCurrentProjects reader = new JsonReaderCurrentProjects("./data/testReaderEmptyCP.json");

        try {
            CurrentProjects cp = reader.read();
            assertTrue(cp.getCurrentProjects().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderNonEmptyCurrentProjects() {
        JsonReaderCurrentProjects reader = new JsonReaderCurrentProjects("./data/testReaderNonEmptyCP.json");

        try {
            CurrentProjects cp = reader.read();
            assertEquals("image1A", cp.getCurrentProjects().get(0));
            assertEquals("image1B", cp.getCurrentProjects().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
