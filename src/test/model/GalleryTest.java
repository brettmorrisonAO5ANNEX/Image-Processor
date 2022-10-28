package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//represents test class for all gallery methods
public class GalleryTest {
    private Gallery g;

    @BeforeEach
    public void setUp() {
        g = new Gallery();
    }

    @Test
    public void testConstructor() {
        assertTrue(g.getGallery().isEmpty());
    }

    @Test
    public void testAddCurrentProjectToEmpty() {
        String destination = "image1A";
        g.addCopyToGallery(destination);
        assertEquals(destination, g.getGallery().get(0));
    }

    @Test
    public void testAddCurrentProjectToNonEmpty() {
        String destination1 = "image2A";
        String destination2 = "image2B";
        g.addCopyToGallery(destination1);
        g.addCopyToGallery(destination2);
        assertEquals(2, g.getGallery().size());
        assertEquals(destination1, g.getGallery().get(0));
        assertEquals(destination2, g.getGallery().get(1));
    }
}
