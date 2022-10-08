package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FilterTest {
    private Filter pixelate = new Filter("pixelate");
    private Filter mirror = new Filter("mirror");
    private Filter negative = new Filter("negative");
    private Image imgTestOne;
    private Image imgTestTwo;

    @BeforeEach
    public void setup() {
        imgTestOne = new Image(1, 1);
        imgTestTwo = new Image(1, 2);
    }

    @Test
    public void constructorTestForName() {
        assertEquals("pixelate", pixelate.getFilterName());
    }

    @Test
    public void constructorExceptionTest() {
        assertThrows(IllegalStateException.class, () -> {
            Filter pixelate2 = new Filter("pixelate");
        });
    }

    @Test
    public void negativeTestOneElement(){
        String[] testArrayBefore = {"ffffff"};
        String[] testArrayAfter = {"0"};
        assertEquals(testArrayBefore, imgTestOne.getHexCodeArray());
        negative.negative(imgTestOne);
        assertEquals(testArrayAfter, imgTestOne.getHexCodeArray());
    }

    @Test
    public void negativeTestTwoElement(){
        String[] testArrayBefore = {"ffffff", "ffffff"};
        String[] testArrayAfter = {"0", "0"};
        assertEquals(testArrayBefore, imgTestTwo.getHexCodeArray());
        negative.negative(imgTestTwo);
        assertEquals(testArrayAfter, imgTestTwo.getHexCodeArray());
    }


}