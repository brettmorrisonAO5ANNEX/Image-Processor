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
        g.addImageToGallery(destination);
        assertEquals(destination, g.getGallery().get(0));
    }

    @Test
    public void testAddCurrentProjectToNonEmpty() {
        String destination1 = "image2A";
        String destination2 = "image2B";
        g.addImageToGallery(destination1);
        g.addImageToGallery(destination2);
        assertEquals(2, g.getGallery().size());
        assertEquals(destination1, g.getGallery().get(0));
        assertEquals(destination2, g.getGallery().get(1));
    }
}
