package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Filter.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterTest {
    private Filter pixelate = new Filter("pixelate");
    private Filter mirror = new Filter("mirror");
    private Filter negative = new Filter("negative");
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

    @Test
    public void testPixelateFourByTwoDZero() {
        //test 4x2 (rowxcol) array with each sub array having elements corresponding to its index + 1
        Image fourByTwo = new Image(2, 4);
        fourByTwo.degreeOfPixelation = 0;
        for (int r = 0; r < fourByTwo.pixelArray.length; r++) {
            for (int c = 0; c < fourByTwo.pixelArray[0].length; c++) {
                fourByTwo.pixelArray[r][c] = r + 1;
            }
        }
        fourByTwo.addFilter(pixelate);
        pixelate.pixelate(fourByTwo);
        assertEquals(1, fourByTwo.pixelArray[0][0]);
        assertEquals(2, fourByTwo.pixelArray[1][0]);
        assertEquals(1, fourByTwo.pixelArray[2][0]);
        assertEquals(2, fourByTwo.pixelArray[3][0]);
        assertEquals(5, fourByTwo.pixelArray[4][0]);
        assertEquals(6, fourByTwo.pixelArray[5][0]);
        assertEquals(5, fourByTwo.pixelArray[6][0]);
        assertEquals(6, fourByTwo.pixelArray[7][0]);

    }

    @Test
    public void testPixelateFourByTwoDOne() {
        //test 4x2 (rowxcol) arrray with each sub array having elements corresponding to its index + 1
        Image fourByTwo = new Image(2, 4);
        fourByTwo.degreeOfPixelation = 1;
        for (int r = 0; r < fourByTwo.pixelArray.length; r++) {
            for (int c = 0; c < fourByTwo.pixelArray[0].length; c++) {
                fourByTwo.pixelArray[r][c] = r + 1;
            }
        }
        fourByTwo.addFilter(pixelate);
        pixelate.pixelate(fourByTwo);
        assertEquals(1, fourByTwo.pixelArray[0][0]);
        assertEquals(1, fourByTwo.pixelArray[1][0]);
        assertEquals(1, fourByTwo.pixelArray[2][0]);
        assertEquals(1, fourByTwo.pixelArray[3][0]);
        assertEquals(1, fourByTwo.pixelArray[4][0]);
        assertEquals(1, fourByTwo.pixelArray[5][0]);
        assertEquals(1, fourByTwo.pixelArray[6][0]);
        assertEquals(1, fourByTwo.pixelArray[7][0]);

    }

    @Test
    public void testPixelateFourByFourDZero() {
        //test 4x4 (rowxcol) array with each sub array having elements corresponding to its index + 1
        Image fourByFour = new Image(4, 4);
        fourByFour.degreeOfPixelation = 0;
        for (int r = 0; r < fourByFour.pixelArray.length; r++) {
            for (int c = 0; c < fourByFour.pixelArray[0].length; c++) {
                fourByFour.pixelArray[r][c] = r + 1;
            }
        }
        fourByFour.addFilter(pixelate);
        pixelate.pixelate(fourByFour);
        assertEquals(1, fourByFour.pixelArray[0][0]);
        assertEquals(2, fourByFour.pixelArray[1][0]);
        assertEquals(3, fourByFour.pixelArray[2][0]);
        assertEquals(4, fourByFour.pixelArray[3][0]);
        assertEquals(5, fourByFour.pixelArray[4][0]);
        assertEquals(6, fourByFour.pixelArray[5][0]);
        assertEquals(7, fourByFour.pixelArray[6][0]);
        assertEquals(8, fourByFour.pixelArray[7][0]);
        assertEquals(9, fourByFour.pixelArray[8][0]);
        assertEquals(10, fourByFour.pixelArray[9][0]);
        assertEquals(11, fourByFour.pixelArray[10][0]);
        assertEquals(12, fourByFour.pixelArray[11][0]);
        assertEquals(13, fourByFour.pixelArray[12][0]);
        assertEquals(14, fourByFour.pixelArray[13][0]);
        assertEquals(15, fourByFour.pixelArray[14][0]);
        assertEquals(16, fourByFour.pixelArray[15][0]);
    }

    @Test
    public void testPixelateFourByFourDOne() {
        //test 4x4 (rowxcol) arraywith each sub array having elements corresponding to its index + 1
        Image fourByFour = new Image(4, 4);
        fourByFour.degreeOfPixelation = 1;
        for (int r = 0; r < fourByFour.pixelArray.length; r++) {
            for (int c = 0; c < fourByFour.pixelArray[0].length; c++) {
                fourByFour.pixelArray[r][c] = r + 1;
            }
        }
        fourByFour.addFilter(pixelate);
        pixelate.pixelate(fourByFour);
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

    @Test
    public void testCreateApparentDegPixZeroFourByFour() {
        int appDegPixTest = createApparentDegPix(imgTestSixteenElement, 0);
        assertEquals(2, appDegPixTest);
    }

    @Test
    public void testCreateApparentDegPixOneFourByFour()  {
        int appDegPixTest = createApparentDegPix(imgTestSixteenElement, 1);
        assertEquals(1, appDegPixTest);
    }

    @Test
    public void testCreateApparentDegPixTwoFourByFour()  {
        int appDegPixTest = createApparentDegPix(imgTestSixteenElement, 2);
        assertEquals(0, appDegPixTest);
    }

    @Test
    public void testCreateApparentDegPixZeroFourByTwo() {
        int appDegPixTest = createApparentDegPix(imgTestEightElement, 0);
        assertEquals(1, appDegPixTest);
    }

    @Test
    public void testCreateApparentDegPixOneFourByTwo() {
        int appDegPixTest = createApparentDegPix(imgTestEightElement, 1);
        assertEquals(0, appDegPixTest);
    }

    @Test
    public void testCreateSubsectionHeightTwoFourByFour() {
        int testSubSecHeight = createSubsectionHeight(imgTestSixteenElement, 2);
        assertEquals(1, testSubSecHeight);
    }

    @Test
    public void testCreateSubsectionHeightOneFourByTwo() {
        int testSubSecHeight = createSubsectionHeight(imgTestEightElement, 1);
        assertEquals(2, testSubSecHeight);
    }

    @Test
    public void testCreateSubsectionWidthZeroFourByFour() {
        int testSubSecWidth = createSubsectionWidth(imgTestSixteenElement, 0);
        assertEquals(4, testSubSecWidth);
    }

    @Test
    public void testCreateSubsectionWidthOneFourByFour() {
        int testSubSecWidth = createSubsectionWidth(imgTestSixteenElement, 1);
        assertEquals(2, testSubSecWidth);
    }
}