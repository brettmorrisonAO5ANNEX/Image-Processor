package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//represents test class for currentprojects methods
public class CurrentProjectsTest {
    private CurrentProjects cp;

    @BeforeEach
    public void setUp() {
        cp = new CurrentProjects();
    }

    @Test
    public void testConstructor() {
        assertTrue(cp.getCurrentProjects().isEmpty());
    }

    @Test
    public void testAddCurrentProjectToEmpty() {
        String destination = "./data/testReaderNewImage.json";
        cp.addToCurrentProjects(destination);
        assertEquals(destination, cp.getCurrentProjects().get(0));
    }

    @Test
    public void testAddCurrentProjectToNonEmpty() {
        String destination1 = "./data/testReaderNewImage.json";
        String destination2 = "./data/testReaderPartiallyEditedImage.json";
        cp.addToCurrentProjects(destination1);
        cp.addToCurrentProjects(destination2);
        assertEquals(2, cp.getCurrentProjects().size());
        assertEquals(destination1, cp.getCurrentProjects().get(0));
        assertEquals(destination2, cp.getCurrentProjects().get(1));
    }
}
