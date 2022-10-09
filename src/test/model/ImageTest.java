package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImageTest {
    private Image i;
    private Filter negative = new Filter("negative");
    private Filter mirror = new Filter("mirror");
    private Filter pixelate = new Filter("pixelate");

    @BeforeEach
    public void runBefore() {
        i = new Image(1, 1);
    }

    @Test
    public void constructorTestBoundary() {
        assertEquals(1, i.getImageWidth());
        assertEquals(1, i.getImageHeight());
        assertEquals(0, i.getlistOfFilter().size());
        assertEquals(1, i.pixelArray.length);
        assertEquals(3, i.pixelArray[0].length);
        assertEquals(255, i.pixelArray[0][0]);
    }

    @Test
    public void constructorTestNotBoundary() {
        Image i2 = new Image(2, 2);
        assertEquals(2, i2.getImageWidth());
        assertEquals(2, i2.getImageHeight());
        assertEquals(0, i2.getlistOfFilter().size());
        assertEquals(4, i2.pixelArray.length);
        assertEquals(3, i.pixelArray[0].length);
        assertEquals(255, i.pixelArray[0][0]);
    }

    @Test
    public void addFilterTestToEmpty() {
        i.addFilter(negative);
        assertEquals(1, i.getlistOfFilter().size());
        assertEquals("negative", i.getlistOfFilter().get(0).getFilterName());
    }

    @Test
    public void addFilterTestDuplicate() {
        i.addFilter(mirror);
        assertEquals(1, i.getlistOfFilter().size());
        assertEquals("mirror", i.getlistOfFilter().get(0).getFilterName());
        i.addFilter(mirror);
        assertEquals(2, i.getlistOfFilter().size());
        assertEquals("mirror", i.getlistOfFilter().get(0).getFilterName());
        assertEquals("mirror", i.getlistOfFilter().get(1).getFilterName());
    }

    @Test
    public void addFilterTestNotEmptyNotDuplicate() {
        i.addFilter(mirror);
        assertEquals(1, i.getlistOfFilter().size());
        assertEquals("mirror", i.getlistOfFilter().get(0).getFilterName());
        i.addFilter(negative);
        assertEquals(2, i.getlistOfFilter().size());
        assertEquals("mirror", i.getlistOfFilter().get(0).getFilterName());
        assertEquals("negative", i.getlistOfFilter().get(1).getFilterName());
    }

    @Test
    public void undoLastListOneElement() {
        i.addFilter(pixelate);
        assertEquals("pixelate", i.getlistOfFilter().get(0).getFilterName());
        i.undoLast();
        assertEquals(0, i.getlistOfFilter().size());
    }

    @Test
    public void undoLastListTwoElements() {
        i.addFilter(mirror);
        i.addFilter(negative);
        assertEquals("mirror", i.getlistOfFilter().get(0).getFilterName());
        assertEquals("negative", i.getlistOfFilter().get(1).getFilterName());
        i.undoLast();
        assertEquals("mirror", i.getlistOfFilter().get(0).getFilterName());
    }

    @Test
    public void undoAllListOneElement() {
        i.addFilter(mirror);
        assertEquals(1, i.getlistOfFilter().size());
        i.undoAll();
        assertEquals(0, i.getlistOfFilter().size());
    }

    @Test
    public void undoAllListTwoElement() {
        i.addFilter(mirror);
        i.addFilter(pixelate);
        assertEquals(2, i.getlistOfFilter().size());
        i.undoAll();
        assertEquals(0, i.getlistOfFilter().size());
    }

    @Test
    public void removeAllOfOneOneElement() {
        i.addFilter(pixelate);
        assertEquals("pixelate", i.getlistOfFilter().get(0).getFilterName());
        i.removeAllOfType(pixelate.getFilterName());
        assertEquals(0, i.getlistOfFilter().size());
    }

    @Test
    public void removeAllOfTypeMultipleElementBeginningOfList() {
        i.addFilter(mirror);
        i.addFilter(mirror);
        i.addFilter(negative);
        i.addFilter(pixelate);
        assertEquals("mirror", i.getlistOfFilter().get(0).getFilterName());
        assertEquals("mirror", i.getlistOfFilter().get(1).getFilterName());
        assertEquals("negative", i.getlistOfFilter().get(2).getFilterName());
        assertEquals("pixelate", i.getlistOfFilter().get(3).getFilterName());
        i.removeAllOfType(mirror.getFilterName());
        assertEquals(2, i.getlistOfFilter().size());
        assertEquals("negative", i.getlistOfFilter().get(0).getFilterName());
        assertEquals("pixelate", i.getlistOfFilter().get(1).getFilterName());
    }

    @Test
    public void removeAllOfTypeMultipleElementMiddleOfList() {
        i.addFilter(pixelate);
        i.addFilter(negative);
        i.addFilter(pixelate);
        i.addFilter(mirror);
        assertEquals("pixelate", i.getlistOfFilter().get(0).getFilterName());
        assertEquals("negative", i.getlistOfFilter().get(1).getFilterName());
        assertEquals("pixelate", i.getlistOfFilter().get(2).getFilterName());
        assertEquals("mirror", i.getlistOfFilter().get(3).getFilterName());
        i.removeAllOfType(pixelate.getFilterName());
        assertEquals(2, i.getlistOfFilter().size());
        assertEquals("negative", i.getlistOfFilter().get(0).getFilterName());
        assertEquals("mirror", i.getlistOfFilter().get(1).getFilterName());
    }

    @Test
    public void viewEditHistoryEmpty() {
        assertEquals("no filters applied", i.viewEditHistory());
    }

    @Test
    public void viewEditHistoryNotEmpty() {
        String result = "[1: mirror, 2: pixelate]";
        i.addFilter(mirror);
        i.addFilter(pixelate);
        assertEquals(2, i.getlistOfFilter().size());
        assertEquals(result, i.viewEditHistory());
    }

    //todo: finish filter tests first
    @Test
    public void processImageEmptyListOfFilter() {
    }
}
