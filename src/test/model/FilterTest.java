package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FilterTest {
    private Filter pixelate = new Filter("pixelate");
    private Filter mirror = new Filter("mirror");
    private Filter negative = new Filter("negative");
    private Image imgTestOneElement;
    private Image imgTestNineElement;

    @BeforeEach
    public void setup() {
        imgTestOneElement = new Image(1, 1);
        imgTestNineElement = new Image(3,3);
    }

    @Test
    public void testConstructorForName() {
        assertEquals("pixelate", pixelate.getFilterName());
    }

    @Test
    public void testConstructorForNameAgain() {
        assertEquals("mirror", mirror.getFilterName());
    }

    @Test
    public void testNegativeOneElement(){
        assertEquals(255, imgTestOneElement.pixelArray[0][0]);
        assertEquals(255, imgTestOneElement.pixelArray[0][2]);
        negative.negative(imgTestOneElement);
        assertEquals(0, imgTestOneElement.pixelArray[0][0]);
        assertEquals(0, imgTestOneElement.pixelArray[0][2]);
    }

    @Test
    public void testNegativeNineElement() {
        assertEquals(255, imgTestNineElement.pixelArray[0][0]);
        assertEquals(255, imgTestNineElement.pixelArray[0][2]);
        negative.negative(imgTestNineElement);
        assertEquals(0, imgTestNineElement.pixelArray[0][0]);
        assertEquals(0, imgTestNineElement.pixelArray[8][2]);
    }

    @Test
    public void testMirrorOneElement() {
        imgTestOneElement.pixelArray[0][0] = 1;
        imgTestOneElement.pixelArray[0][1] = 2;
        imgTestOneElement.pixelArray[0][2] = 3;
        assertEquals(1, imgTestOneElement.pixelArray[0][0]);
        assertEquals(2, imgTestOneElement.pixelArray[0][1]);
        assertEquals(3, imgTestOneElement.pixelArray[0][2]);
        imgTestOneElement.addFilter(mirror);
        imgTestOneElement.processImage();
        assertEquals(1, imgTestOneElement.pixelArray[0][0]);
        assertEquals(2, imgTestOneElement.pixelArray[0][1]);
        assertEquals(3, imgTestOneElement.pixelArray[0][2]);
    }

    @Test
    public void testMirrorFourElement() {
        Image i4 = new Image(2, 2);
        i4.pixelArray[0][0] = 1;
        i4.pixelArray[0][1] = 1;
        i4.pixelArray[0][2] = 1;
        i4.pixelArray[1][0] = 2;
        i4.pixelArray[1][1] = 2;
        i4.pixelArray[1][2] = 2;
        i4.pixelArray[2][0] = 3;
        i4.pixelArray[2][1] = 3;
        i4.pixelArray[2][2] = 3;
        i4.pixelArray[3][0] = 4;
        i4.pixelArray[3][1] = 4;
        i4.pixelArray[3][2] = 4;
        assertEquals(1, i4.pixelArray[0][0]);
        assertEquals(2, i4.pixelArray[1][0]);
        assertEquals(3, i4.pixelArray[2][0]);
        assertEquals(4, i4.pixelArray[3][0]);
        i4.addFilter(mirror);
        i4.processImage();
        assertEquals(2, i4.pixelArray[0][0]);
        assertEquals(1, i4.pixelArray[1][0]);
        assertEquals(4, i4.pixelArray[2][0]);
        assertEquals(3, i4.pixelArray[3][0]);
    }

    //TODO: add tests for pixelate (2x2), (4x4)
}