package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

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

    //TODO: add tests for pixelate (2x4), (4x4) with differnt d's
    @Test
    public void testPixelateFourByTwoDZero() {
        //test 4x2 (rowxcol) arrray with each sub array having elements corresponding to its index + 1
        Image twoByFour = new Image(2, 4);
        for (int r = 0; r < twoByFour.pixelArray.length; r++) {
            for (int c = 0; c < twoByFour.pixelArray[0].length; c++) {
                twoByFour.pixelArray[r][c] = r + 1;
            }
        }
        twoByFour.addFilter(pixelate);
        pixelate.pixelate(twoByFour, 0);
        assertEquals(1, twoByFour.pixelArray[0][0]);
        assertEquals(1, twoByFour.pixelArray[1][0]);
        assertEquals(1, twoByFour.pixelArray[2][0]);
        assertEquals(1, twoByFour.pixelArray[3][0]);
        assertEquals(1, twoByFour.pixelArray[4][0]);
        assertEquals(1, twoByFour.pixelArray[5][0]);
        assertEquals(1, twoByFour.pixelArray[6][0]);
        assertEquals(1, twoByFour.pixelArray[7][0]);

    }

    @Test
    public void testPixelateFourByTwoDOne() {
        //test 4x2 (rowxcol) arrray with each sub array having elements corresponding to its index + 1
        Image twoByFour = new Image(2, 4);
        for (int r = 0; r < twoByFour.pixelArray.length; r++) {
            for (int c = 0; c < twoByFour.pixelArray[0].length; c++) {
                twoByFour.pixelArray[r][c] = r + 1;
            }
        }
        twoByFour.addFilter(pixelate);
        pixelate.pixelate(twoByFour, 1);
        assertEquals(1, twoByFour.pixelArray[0][0]);
        assertEquals(2, twoByFour.pixelArray[1][0]);
        assertEquals(1, twoByFour.pixelArray[2][0]);
        assertEquals(2, twoByFour.pixelArray[3][0]);
        assertEquals(5, twoByFour.pixelArray[4][0]);
        assertEquals(6, twoByFour.pixelArray[5][0]);
        assertEquals(5, twoByFour.pixelArray[6][0]);
        assertEquals(6, twoByFour.pixelArray[7][0]);

    }

    @Test
    public void testPixelateFourByFourDZero() {
        //test 4x4 (rowxcol) arraywith each sub array having elements corresponding to its index + 1
        Image fourByFour = new Image(4, 4);
        for (int r = 0; r < fourByFour.pixelArray.length; r++) {
            for (int c = 0; c < fourByFour.pixelArray[0].length; c++) {
                fourByFour.pixelArray[r][c] = r + 1;
            }
        }
        fourByFour.addFilter(pixelate);
        pixelate.pixelate(fourByFour, 0);
        assertEquals(1, fourByFour.pixelArray[0][0]);
        assertEquals(1, fourByFour.pixelArray[1][0]);
        assertEquals(1, fourByFour.pixelArray[2][0]);
        assertEquals(1, fourByFour.pixelArray[3][0]);
        assertEquals(1, fourByFour.pixelArray[4][0]);
        assertEquals(1, fourByFour.pixelArray[5][0]);
        assertEquals(1, fourByFour.pixelArray[6][0]);
        assertEquals(1, fourByFour.pixelArray[7][0]);
        assertEquals(1, fourByFour.pixelArray[8][0]);
        assertEquals(1, fourByFour.pixelArray[9][0]);
        assertEquals(1, fourByFour.pixelArray[10][0]);
        assertEquals(1, fourByFour.pixelArray[11][0]);
        assertEquals(1, fourByFour.pixelArray[12][0]);
        assertEquals(1, fourByFour.pixelArray[13][0]);
        assertEquals(1, fourByFour.pixelArray[14][0]);
        assertEquals(1, fourByFour.pixelArray[15][0]);
    }

    @Test
    public void testPixelateFourByFourDOne() {
        //test 4x4 (rowxcol) arraywith each sub array having elements corresponding to its index + 1
        Image fourByFour = new Image(4, 4);
        for (int r = 0; r < fourByFour.pixelArray.length; r++) {
            for (int c = 0; c < fourByFour.pixelArray[0].length; c++) {
                fourByFour.pixelArray[r][c] = r + 1;
            }
        }
        fourByFour.addFilter(pixelate);
        pixelate.pixelate(fourByFour, 1);
        assertEquals(1, fourByFour.pixelArray[0][0]);
        assertEquals(1, fourByFour.pixelArray[1][0]);
        assertEquals(3, fourByFour.pixelArray[2][0]);
        assertEquals(3, fourByFour.pixelArray[3][0]);
        assertEquals(1, fourByFour.pixelArray[4][0]);
        assertEquals(1, fourByFour.pixelArray[5][0]);
        assertEquals(3, fourByFour.pixelArray[6][0]);
        assertEquals(3, fourByFour.pixelArray[7][0]);
        assertEquals(9, fourByFour.pixelArray[8][0]);
        assertEquals(9, fourByFour.pixelArray[9][0]);
        assertEquals(11, fourByFour.pixelArray[10][0]);
        assertEquals(11, fourByFour.pixelArray[11][0]);
        assertEquals(9, fourByFour.pixelArray[12][0]);
        assertEquals(9, fourByFour.pixelArray[13][0]);
        assertEquals(11, fourByFour.pixelArray[14][0]);
        assertEquals(11, fourByFour.pixelArray[15][0]);
    }
}