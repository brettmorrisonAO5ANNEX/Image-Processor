package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImageTest {
    private Image i;
    private Filter negative = new Filter("negative");
    private Filter mirror = new Filter("mirror");
    private Filter pixelate = new Filter("pixelate");
    private List<int[]> testArrayOneElement;
    private List<int[]> testArrayFourElement;
    private List<int[]> testArraySixteenElement;

    @BeforeEach
    public void setup() {
        testArrayOneElement = new ArrayList<>();
        testArrayFourElement = new ArrayList<>();
        i = new Image(1, 1);

        testArrayOneElement.add(new int[]{255, 255, 255});

        for (int i = 0; i < 4; i++) {
            testArrayFourElement.add(new int[] {255, 255, 255});
        }

        for (int i = 0; i < 16; i++) {
            testArraySixteenElement.add(new int[] {255, 255, 255});
        }
    }

    @Test
    public void constructorTestBoundary() {
        assertEquals(1, i.getImageWidth());
        assertEquals(1, i.getImageHeight());
        assertEquals(0, i.getlistOfFilter().size());
        assertEquals(testArrayOneElement, i.getPixelArray());
    }

    @Test
    public void constructorTestNotBoundary() {
        Image i2 = new Image(2, 2);
        assertEquals(2, i2.getImageWidth());
        assertEquals(2, i2.getImageHeight());
        assertEquals(0, i2.getlistOfFilter().size());
        assertEquals(testArrayFourElement, i2.getPixelArray());
    }

    @Test
    public void addFilterTestToEmpty() {
        i.addFilter(negative);
        assertEquals(1, i.getlistOfFilter().size());
        assertEquals(negative, i.getlistOfFilter().get(0));
    }

    @Test
    public void addFilterTestDuplicate() {
        i.addFilter(mirror);
        assertEquals(1, i.getlistOfFilter().size());
        assertEquals(mirror, i.getlistOfFilter().get(0));
        i.addFilter(mirror);
        assertEquals(2, i.getlistOfFilter().size());
        assertEquals(mirror, i.getlistOfFilter().get(0));
        assertEquals(mirror, i.getlistOfFilter().get(1));
    }

    @Test
    public void addFilterTestNotEmptyNotDuplicate() {
        i.addFilter(mirror);
        assertEquals(1, i.getlistOfFilter().size());
        assertEquals(mirror, i.getlistOfFilter().get(0));
        i.addFilter(negative);
        assertEquals(2, i.getlistOfFilter().size());
        assertEquals(negative, i.getlistOfFilter().get(0));
        assertEquals(mirror, i.getlistOfFilter().get(1));
    }

    @Test
    public void undoLastListOneElement() {
        i.addFilter(pixelate);
        assertEquals(pixelate, i.getlistOfFilter().get(0));
        i.undoLast();
        assertEquals(0, i.getlistOfFilter().size());
    }

    @Test
    public void undoLastListTwoElements() {
        i.addFilter(mirror);
        i.addFilter(negative);
        assertEquals(negative, i.getlistOfFilter().get(0));
        i.undoLast();
        assertEquals(mirror, i.getlistOfFilter().get(0));
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
        assertEquals(pixelate, i.getlistOfFilter().get(0));
        i.removeAllOfOne(pixelate);
        assertEquals(0, i.getlistOfFilter().size());
    }

    @Test
    public void removeAllOfOneMultElementEndOfList() {
        i.addFilter(mirror);
        i.addFilter(mirror);
        i.addFilter(negative);
        i.addFilter(pixelate);
        assertEquals(pixelate, i.getlistOfFilter().get(0));
        assertEquals(negative, i.getlistOfFilter().get(1));
        assertEquals(mirror, i.getlistOfFilter().get(2));
        assertEquals(mirror, i.getlistOfFilter().get(3));
        i.removeAllOfOne(mirror);
        assertEquals(2, i.getlistOfFilter().size());
        assertEquals(pixelate, i.getlistOfFilter().get(0));
        assertEquals(negative, i.getlistOfFilter().get(1));
    }

    @Test
    public void removeAllOfOneMultElementMidOfList() {
        i.addFilter(pixelate);
        i.addFilter(negative);
        i.addFilter(pixelate);
        i.addFilter(mirror);
        assertEquals(mirror, i.getlistOfFilter().get(0));
        assertEquals(pixelate, i.getlistOfFilter().get(1));
        assertEquals(negative, i.getlistOfFilter().get(2));
        assertEquals(pixelate, i.getlistOfFilter().get(3));
        i.removeAllOfOne(pixelate);
        assertEquals(mirror, i.getlistOfFilter().get(0));
        assertEquals(negative, i.getlistOfFilter().get(1));
    }

    @Test
    public void viewEditHistoryEmpty() {
        assertEquals("no filters applied", i.viewEditHistory());
    }

    @Test
    public void viewEditHistoryNotEmpty() {
        String result = "1: mirror 2: pixelate";
        i.addFilter(mirror);
        i.addFilter(pixelate);
        assertEquals(result, i.viewEditHistory());
    }

    //todo: finish filter tests first
    @Test
    public void processImageEmptyListOfFilter() {
    }
}
