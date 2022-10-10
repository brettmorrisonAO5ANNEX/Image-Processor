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
    public void testConstructorBoundary() {
        assertEquals(1, i.getImageWidth());
        assertEquals(1, i.getImageHeight());
        assertEquals(0, i.getListOfFilter().size());
        assertEquals(0, i.getUniqueFiltersUsed().size());
        assertEquals(1, i.pixelArray.length);
        assertEquals(3, i.pixelArray[0].length);
        assertEquals(255, i.pixelArray[0][0]);
    }

    @Test
    public void testsConstructorNotBoundary() {
        Image i2 = new Image(2, 2);
        assertEquals(2, i2.getImageWidth());
        assertEquals(2, i2.getImageHeight());
        assertEquals(0, i2.getListOfFilter().size());
        assertEquals(0, i2.getUniqueFiltersUsed().size());
        assertEquals(4, i2.pixelArray.length);
        assertEquals(3, i2.pixelArray[0].length);
        assertEquals(255, i2.pixelArray[0][0]);
    }

    @Test
    public void testAddFilterToEmpty() {
        i.addFilter(negative);
        assertEquals(1, i.getListOfFilter().size());
        assertEquals("negative", i.getListOfFilter().get(0).getFilterName());
    }

    @Test
    public void testAddFilterDuplicate() {
        i.addFilter(mirror);
        assertEquals(1, i.getListOfFilter().size());
        assertEquals("mirror", i.getListOfFilter().get(0).getFilterName());
        i.addFilter(mirror);
        assertEquals(2, i.getListOfFilter().size());
        assertEquals("mirror", i.getListOfFilter().get(0).getFilterName());
        assertEquals("mirror", i.getListOfFilter().get(1).getFilterName());
    }

    @Test
    public void testAddFilterNotEmptyNotDuplicate() {
        i.addFilter(mirror);
        assertEquals(1, i.getListOfFilter().size());
        assertEquals("mirror", i.getListOfFilter().get(0).getFilterName());
        i.addFilter(negative);
        assertEquals(2, i.getListOfFilter().size());
        assertEquals("mirror", i.getListOfFilter().get(0).getFilterName());
        assertEquals("negative", i.getListOfFilter().get(1).getFilterName());
    }

    @Test
    public void testAddIfUniqueIsUnique() {
        assertEquals(0, i.getUniqueFiltersUsed().size());
        i.addFilter(negative);
        assertEquals(1, i.getUniqueFiltersUsed().size());
        assertEquals("negative", i.getUniqueFiltersUsed().get(0));
        i.addIfUnique(mirror);
        assertEquals(2, i.getUniqueFiltersUsed().size());
        assertEquals("negative", i.getUniqueFiltersUsed().get(0));
        assertEquals("mirror", i.getUniqueFiltersUsed().get(1));
    }

    @Test
    public void testAddIfUniqueNotUnique() {
        assertEquals(0, i.getUniqueFiltersUsed().size());
        i.addFilter(pixelate);
        assertEquals(1, i.getUniqueFiltersUsed().size());
        assertEquals("pixelate", i.getUniqueFiltersUsed().get(0));
        i.addFilter(pixelate);
        assertEquals(1, i.getUniqueFiltersUsed().size());
        assertEquals("pixelate", i.getUniqueFiltersUsed().get(0));
    }


    @Test
    public void testUndoLastListOneElement() {
        i.addFilter(pixelate);
        assertEquals("pixelate", i.getListOfFilter().get(0).getFilterName());
        i.undoLast();
        assertEquals(0, i.getListOfFilter().size());
    }

    @Test
    public void testUndoLastListTwoElements() {
        i.addFilter(mirror);
        i.addFilter(negative);
        assertEquals("mirror", i.getListOfFilter().get(0).getFilterName());
        assertEquals("negative", i.getListOfFilter().get(1).getFilterName());
        i.undoLast();
        assertEquals("mirror", i.getListOfFilter().get(0).getFilterName());
    }

    @Test
    public void testUndoAllListOneElement() {
        i.addFilter(mirror);
        assertEquals(1, i.getListOfFilter().size());
        i.undoAll();
        assertEquals(0, i.getListOfFilter().size());
    }

    @Test
    public void testUndoAllListTwoElement() {
        i.addFilter(mirror);
        i.addFilter(pixelate);
        assertEquals(2, i.getListOfFilter().size());
        i.undoAll();
        assertEquals(0, i.getListOfFilter().size());
    }

    @Test
    public void testRemoveAllOfOneOneElement() {
        i.addFilter(pixelate);
        assertEquals("pixelate", i.getListOfFilter().get(0).getFilterName());
        i.removeAllOfType(pixelate.getFilterName());
        assertEquals(0, i.getListOfFilter().size());
    }

    @Test
    public void testRemoveAllOfTypeMultipleElementBeginningOfList() {
        i.addFilter(mirror);
        i.addFilter(mirror);
        i.addFilter(negative);
        i.addFilter(pixelate);
        assertEquals("mirror", i.getListOfFilter().get(0).getFilterName());
        assertEquals("mirror", i.getListOfFilter().get(1).getFilterName());
        assertEquals("negative", i.getListOfFilter().get(2).getFilterName());
        assertEquals("pixelate", i.getListOfFilter().get(3).getFilterName());
        i.removeAllOfType(mirror.getFilterName());
        assertEquals(2, i.getListOfFilter().size());
        assertEquals("negative", i.getListOfFilter().get(0).getFilterName());
        assertEquals("pixelate", i.getListOfFilter().get(1).getFilterName());
    }

    @Test
    public void testRemoveAllOfTypeMultipleElementMiddleOfList() {
        i.addFilter(pixelate);
        i.addFilter(negative);
        i.addFilter(pixelate);
        i.addFilter(mirror);
        assertEquals("pixelate", i.getListOfFilter().get(0).getFilterName());
        assertEquals("negative", i.getListOfFilter().get(1).getFilterName());
        assertEquals("pixelate", i.getListOfFilter().get(2).getFilterName());
        assertEquals("mirror", i.getListOfFilter().get(3).getFilterName());
        i.removeAllOfType(pixelate.getFilterName());
        assertEquals(2, i.getListOfFilter().size());
        assertEquals("negative", i.getListOfFilter().get(0).getFilterName());
        assertEquals("mirror", i.getListOfFilter().get(1).getFilterName());
    }

    @Test
    public void testViewEditHistoryEmpty() {
        assertEquals("[no filters applied]", i.viewEditHistory());
    }

    @Test
    public void testViewEditHistoryNotEmpty() {
        String result = "[1: mirror, 2: pixelate]";
        i.addFilter(mirror);
        i.addFilter(pixelate);
        assertEquals(2, i.getListOfFilter().size());
        assertEquals(result, i.viewEditHistory());
    }

    @Test
    public void testProcessImageEmptyListOfFilter() {
        assertEquals(255, i.getPixelArray()[0][0]);
        assertEquals(255, i.getPixelArray()[0][2]);
        i.processImage();
        assertEquals(255, i.getPixelArray()[0][0]);
        assertEquals(255, i.getPixelArray()[0][2]);
    }

    @Test
    public void testProcessImageOneInListOfFilter() {
        i.addFilter(negative);
        assertEquals(255, i.getPixelArray()[0][0]);
        assertEquals(255, i.getPixelArray()[0][2]);
        i.processImage();
        assertEquals(0, i.getPixelArray()[0][0]);
        assertEquals(0, i.getPixelArray()[0][2]);
    }

    @Test
    public void testProcessImageMoreThanOneInListOfFilter() {
        i.addFilter(negative);
        i.addFilter(negative);
        assertEquals(255, i.getPixelArray()[0][0]);
        assertEquals(255, i.getPixelArray()[0][2]);
        i.processImage();
        assertEquals(255, i.getPixelArray()[0][0]);
        assertEquals(255, i.getPixelArray()[0][2]);
    }

    @Test
    public void testCreatVisArrayOneElement() {
        String result = "[0, 0, 0]\n" + "";
        i.addFilter(negative);
        i.processImage();
        assertEquals(result, i.createVisArray(0));
    }

    @Test
    public void testCreateVisArrayFourElement() {
        String result = "[0, 0, 0][0, 0, 0]\n" + "[0, 0, 0][0, 0, 0]\n" + "";
        Image i4 = new Image (2,2);
        i4.addFilter(negative);
        i4.processImage();
        assertEquals(result, i4.createVisArray(0));
    }

    @Test
    public void testCreateRowOneElement() {
        String row = "[255, 255, 255]";
        assertEquals(row, i.createRow(0, i.width));
    }

    @Test
    public void testCreateRowSixTeenElement() {
        String row = "[255, 255, 255] [255, 255, 255] [255, 255, 255] [255, 255, 255]";
        Image i16 = new Image(4,4);
        String createdRow = i16.createRow(0, i.width);
    }
}
