package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Filter.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

//represents test cases for Filter class methods
class FilterTest {
    private Filter pixelate = new Filter("pixelate");
    private Filter mirror = new Filter("mirror");
    private Filter negative = new Filter("negative");
    private Filter colorGradient = new Filter("colorGradient");
    private Image imgTestOneElement;
    private Image imgTestEightElement;
    private Image imgTestNineElement;
    private Image imgTestSixteenElement;

    @BeforeEach
    public void setup() {
        imgTestOneElement = new Image(1, 1);
        imgTestEightElement = new Image(2, 4);
        imgTestNineElement = new Image(3,3);
        imgTestSixteenElement = new Image(4, 4);
    }

    @Test
    public void testSetImageResultEmptyString() {
        imgTestOneElement.setImageResult("");
        assertEquals("", imgTestOneElement.getImageResult());
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
        assertEquals(255, imgTestOneElement.getPixelArray()[0][0]);
        assertEquals(255, imgTestOneElement.getPixelArray()[0][2]);
        negative.negative(imgTestOneElement);
        assertEquals(0, imgTestOneElement.getPixelArray()[0][0]);
        assertEquals(0, imgTestOneElement.getPixelArray()[0][2]);
    }

    @Test
    public void testNegativeNineElement() {
        assertEquals(255, imgTestNineElement.getPixelArray()[0][0]);
        assertEquals(255, imgTestNineElement.getPixelArray()[0][2]);
        negative.negative(imgTestNineElement);
        assertEquals(0, imgTestNineElement.getPixelArray()[0][0]);
        assertEquals(0, imgTestNineElement.getPixelArray()[8][2]);
    }

    @Test
    public void testMirrorOneElement() {
        imgTestOneElement.getPixelArray()[0][0] = 1;
        imgTestOneElement.getPixelArray()[0][1] = 2;
        imgTestOneElement.getPixelArray()[0][2] = 3;
        assertEquals(1, imgTestOneElement.getPixelArray()[0][0]);
        assertEquals(2, imgTestOneElement.getPixelArray()[0][1]);
        assertEquals(3, imgTestOneElement.getPixelArray()[0][2]);
        imgTestOneElement.addFilter(mirror);
        imgTestOneElement.processImage();
        assertEquals(1, imgTestOneElement.getPixelArray()[0][0]);
        assertEquals(2, imgTestOneElement.getPixelArray()[0][1]);
        assertEquals(3, imgTestOneElement.getPixelArray()[0][2]);
    }

    @Test
    public void testMirrorFourElement() {
        Image i4 = new Image(2, 2);
        i4.getPixelArray()[0][0] = 1;
        i4.getPixelArray()[0][1] = 1;
        i4.getPixelArray()[0][2] = 1;
        i4.getPixelArray()[1][0] = 2;
        i4.getPixelArray()[1][1] = 2;
        i4.getPixelArray()[1][2] = 2;
        i4.getPixelArray()[2][0] = 3;
        i4.getPixelArray()[2][1] = 3;
        i4.getPixelArray()[2][2] = 3;
        i4.getPixelArray()[3][0] = 4;
        i4.getPixelArray()[3][1] = 4;
        i4.getPixelArray()[3][2] = 4;
        assertEquals(1, i4.getPixelArray()[0][0]);
        assertEquals(2, i4.getPixelArray()[1][0]);
        assertEquals(3, i4.getPixelArray()[2][0]);
        assertEquals(4, i4.getPixelArray()[3][0]);
        i4.addFilter(mirror);
        i4.processImage();
        assertEquals(2, i4.getPixelArray()[0][0]);
        assertEquals(1, i4.getPixelArray()[1][0]);
        assertEquals(4, i4.getPixelArray()[2][0]);
        assertEquals(3, i4.getPixelArray()[3][0]);
    }

    @Test
    public void testColorGradientOneByOneRed() {
        assertEquals(255, imgTestOneElement.getPixelArray()[0][0]);
        colorGradient.colorGradient("red", imgTestOneElement);
        assertEquals(255, imgTestOneElement.getPixelArray()[0][0]);
    }

    @Test
    public void testColorGradientTwoByFourGreen() {
        assertEquals(255, imgTestEightElement.getPixelArray()[0][1]);
        assertEquals(255, imgTestEightElement.getPixelArray()[1][1]);
        assertEquals(255, imgTestEightElement.getPixelArray()[2][1]);
        assertEquals(255, imgTestEightElement.getPixelArray()[3][1]);
        assertEquals(255, imgTestEightElement.getPixelArray()[4][1]);
        assertEquals(255, imgTestEightElement.getPixelArray()[5][1]);
        assertEquals(255, imgTestEightElement.getPixelArray()[6][1]);
        assertEquals(255, imgTestEightElement.getPixelArray()[7][1]);
        colorGradient.colorGradient("green", imgTestEightElement);
        assertEquals(63, imgTestEightElement.getPixelArray()[0][1]);
        assertEquals(63, imgTestEightElement.getPixelArray()[1][1]);
        assertEquals(126, imgTestEightElement.getPixelArray()[2][1]);
        assertEquals(126, imgTestEightElement.getPixelArray()[3][1]);
        assertEquals(189, imgTestEightElement.getPixelArray()[4][1]);
        assertEquals(189, imgTestEightElement.getPixelArray()[5][1]);
        assertEquals(252, imgTestEightElement.getPixelArray()[6][1]);
        assertEquals(252, imgTestEightElement.getPixelArray()[7][1]);
    }

    @Test
    public void testColorGradientTwoByTwoBlue() {
        Image testTwoByTwo = new Image (2, 3);
        assertEquals(255, testTwoByTwo.getPixelArray()[0][2]);
        assertEquals(255, testTwoByTwo.getPixelArray()[1][2]);
        assertEquals(255, testTwoByTwo.getPixelArray()[2][2]);
        assertEquals(255, testTwoByTwo.getPixelArray()[3][2]);
        assertEquals(255, testTwoByTwo.getPixelArray()[4][2]);
        assertEquals(255, testTwoByTwo.getPixelArray()[5][2]);
        colorGradient.colorGradient("blue", testTwoByTwo);
        assertEquals(85, testTwoByTwo.getPixelArray()[0][2]);
        assertEquals(85, testTwoByTwo.getPixelArray()[1][2]);
        assertEquals(170, testTwoByTwo.getPixelArray()[2][2]);
        assertEquals(170, testTwoByTwo.getPixelArray()[3][2]);
        assertEquals(255, testTwoByTwo.getPixelArray()[4][2]);
        assertEquals(255, testTwoByTwo.getPixelArray()[5][2]);
    }
}