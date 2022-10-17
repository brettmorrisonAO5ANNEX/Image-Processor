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
    public void testPixelateFourByTwoDZero() {
        //test 4x2 (rowxcol) array with each sub array having elements corresponding to its index + 1
        Image fourByTwo = new Image(2, 4);
        fourByTwo.setDegreeOfPixelation(0);
        for (int r = 0; r < fourByTwo.getPixelArray().length; r++) {
            for (int c = 0; c < fourByTwo.getPixelArray()[0].length; c++) {
                fourByTwo.getPixelArray()[r][c] = r + 1;
            }
        }
        fourByTwo.addFilter(pixelate);
        pixelate.pixelate(fourByTwo);
        assertEquals(1, fourByTwo.getPixelArray()[0][0]);
        assertEquals(2, fourByTwo.getPixelArray()[1][0]);
        assertEquals(1, fourByTwo.getPixelArray()[2][0]);
        assertEquals(2, fourByTwo.getPixelArray()[3][0]);
        assertEquals(5, fourByTwo.getPixelArray()[4][0]);
        assertEquals(6, fourByTwo.getPixelArray()[5][0]);
        assertEquals(5, fourByTwo.getPixelArray()[6][0]);
        assertEquals(6, fourByTwo.getPixelArray()[7][0]);

    }

    @Test
    public void testPixelateFourByTwoDOne() {
        //test 4x2 (rowxcol) arrray with each sub array having elements corresponding to its index + 1
        Image fourByTwo = new Image(2, 4);
        fourByTwo.setDegreeOfPixelation(1);
        for (int r = 0; r < fourByTwo.getPixelArray().length; r++) {
            for (int c = 0; c < fourByTwo.getPixelArray()[0].length; c++) {
                fourByTwo.getPixelArray()[r][c] = r + 1;
            }
        }
        fourByTwo.addFilter(pixelate);
        pixelate.pixelate(fourByTwo);
        assertEquals(1, fourByTwo.getPixelArray()[0][0]);
        assertEquals(1, fourByTwo.getPixelArray()[1][0]);
        assertEquals(1, fourByTwo.getPixelArray()[2][0]);
        assertEquals(1, fourByTwo.getPixelArray()[3][0]);
        assertEquals(1, fourByTwo.getPixelArray()[4][0]);
        assertEquals(1, fourByTwo.getPixelArray()[5][0]);
        assertEquals(1, fourByTwo.getPixelArray()[6][0]);
        assertEquals(1, fourByTwo.getPixelArray()[7][0]);

    }

    @Test
    public void testPixelateFourByFourDZero() {
        //test 4x4 (rowxcol) array with each sub array having elements corresponding to its index + 1
        Image fourByFour = new Image(4, 4);
        fourByFour.setDegreeOfPixelation(0);
        for (int r = 0; r < fourByFour.getPixelArray().length; r++) {
            for (int c = 0; c < fourByFour.getPixelArray()[0].length; c++) {
                fourByFour.getPixelArray()[r][c] = r + 1;
            }
        }
        fourByFour.addFilter(pixelate);
        pixelate.pixelate(fourByFour);
        assertEquals(1, fourByFour.getPixelArray()[0][0]);
        assertEquals(2, fourByFour.getPixelArray()[1][0]);
        assertEquals(3, fourByFour.getPixelArray()[2][0]);
        assertEquals(4, fourByFour.getPixelArray()[3][0]);
        assertEquals(5, fourByFour.getPixelArray()[4][0]);
        assertEquals(6, fourByFour.getPixelArray()[5][0]);
        assertEquals(7, fourByFour.getPixelArray()[6][0]);
        assertEquals(8, fourByFour.getPixelArray()[7][0]);
        assertEquals(9, fourByFour.getPixelArray()[8][0]);
        assertEquals(10, fourByFour.getPixelArray()[9][0]);
        assertEquals(11, fourByFour.getPixelArray()[10][0]);
        assertEquals(12, fourByFour.getPixelArray()[11][0]);
        assertEquals(13, fourByFour.getPixelArray()[12][0]);
        assertEquals(14, fourByFour.getPixelArray()[13][0]);
        assertEquals(15, fourByFour.getPixelArray()[14][0]);
        assertEquals(16, fourByFour.getPixelArray()[15][0]);
    }

    @Test
    public void testPixelateFourByFourDOne() {
        //test 4x4 (rowxcol) arraywith each sub array having elements corresponding to its index + 1
        Image fourByFour = new Image(4, 4);
        fourByFour.setDegreeOfPixelation(1);
        for (int r = 0; r < fourByFour.getPixelArray().length; r++) {
            for (int c = 0; c < fourByFour.getPixelArray()[0].length; c++) {
                fourByFour.getPixelArray()[r][c] = r + 1;
            }
        }
        fourByFour.addFilter(pixelate);
        pixelate.pixelate(fourByFour);
        assertEquals(1, fourByFour.getPixelArray()[0][0]);
        assertEquals(1, fourByFour.getPixelArray()[1][0]);
        assertEquals(3, fourByFour.getPixelArray()[2][0]);
        assertEquals(3, fourByFour.getPixelArray()[3][0]);
        assertEquals(1, fourByFour.getPixelArray()[4][0]);
        assertEquals(1, fourByFour.getPixelArray()[5][0]);
        assertEquals(3, fourByFour.getPixelArray()[6][0]);
        assertEquals(3, fourByFour.getPixelArray()[7][0]);
        assertEquals(9, fourByFour.getPixelArray()[8][0]);
        assertEquals(9, fourByFour.getPixelArray()[9][0]);
        assertEquals(11, fourByFour.getPixelArray()[10][0]);
        assertEquals(11, fourByFour.getPixelArray()[11][0]);
        assertEquals(9, fourByFour.getPixelArray()[12][0]);
        assertEquals(9, fourByFour.getPixelArray()[13][0]);
        assertEquals(11, fourByFour.getPixelArray()[14][0]);
        assertEquals(11, fourByFour.getPixelArray()[15][0]);
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