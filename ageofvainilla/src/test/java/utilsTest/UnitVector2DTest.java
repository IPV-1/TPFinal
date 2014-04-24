package utilsTest;


import org.junit.Before;
import org.junit.Test;

import com.uqbar.vainilla.UnitVector2D;

import static org.junit.Assert.assertTrue;

public class UnitVector2DTest {

    UnitVector2D unitVector;
    double x;
    double y;

    @Before
    public void setUp() {
        x = 1;
        y = 1;
        unitVector = new UnitVector2D(x, y);
    }

    @Test
    public void testXMustBeEqualOrLessToOriginalNumber() {
        assertTrue(unitVector.getX() <= x);
    }

    @Test
    public void testYMustBeEqualOrLessToOriginalNumber() {
        assertTrue(unitVector.getY() <= y);
    }

    @Test
    public void testInvertXMustMakePositiveToNegative() {
        unitVector.invertX();
        assertTrue(unitVector.getX() < 0);
    }

    @Test
    public void testInvertYMustMakePositiveToNegative() {
        unitVector.invertY();
        assertTrue(unitVector.getY() < 0);
    }

    @Test
    public void testInvertXModuleMustBeEqualsOrLessThanOriginalNumber() {
        unitVector.invertX();
        double newX = Math.abs(unitVector.getX());
        assertTrue(newX <= x);
    }

    @Test
    public void testInvertYModuleMustBeEqualsOrLessThanOriginalNumber() {
        unitVector.invertY();
        double newY = Math.abs(unitVector.getY());
        assertTrue(newY <= y);
    }


    @Test
    public void testInvertXModuleMustBeMoreThan0() {
        unitVector.invertX();
        double newX = Math.abs(unitVector.getX());
        assertTrue(newX > 0);
    }

    @Test
    public void testInvertYModuleMustBeMoreThan0() {
        unitVector.invertY();
        double newY = Math.abs(unitVector.getY());
        assertTrue(newY > 0);
    }

}
