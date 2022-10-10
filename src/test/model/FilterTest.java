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
    private Image imgTestSixteenElement;

    @BeforeEach
    public void setup() {
        imgTestOneElement = new Image(1, 1);
        imgTestNineElement = new Image(3,3);
        imgTestSixteenElement = new Image(4, 4);

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

}