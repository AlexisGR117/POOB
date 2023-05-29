import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FractionTest {

    // Test gcd (a, b)
    @Test
    public void gcdShouldBe1ifRelativePrimes() {
        assertEquals (1, Fraction.gcd(3, 4));
        assertEquals (1, Fraction.gcd(6, 35));
        assertEquals (1, Fraction.gcd(35, 24));
        assertEquals (1, Fraction.gcd(7, 5));
    }

    @Test
    public void gcdShouldBeTheSmallestIfMultiples() {
        assertEquals (3, Fraction.gcd(3, 12));
        assertEquals (4, Fraction.gcd(16, 4));
    }
    
    @Test
    public void gcdShouldBeEitherTwoIfEqual() {
        assertEquals (1204, Fraction.gcd(1204, 1204));
        assertEquals (452, Fraction.gcd(452, 452));
        assertEquals (1, Fraction.gcd(1, 1));
    }
    
    @Test
    public void gcdShouldCalculateWellInNormalCases() {
        assertEquals (30, Fraction.gcd(180, 210));
        assertEquals (415800, Fraction.gcd(4158000, 5405400));
    }
    
    @Test
    public void gcdShouldBeTheSameForNegatives() {
        assertEquals (Fraction.gcd(-210,180), Fraction.gcd(180, 210));
        assertEquals (Fraction.gcd(210,-180), Fraction.gcd(180, 210));
        assertEquals (Fraction.gcd(-210,-180), Fraction.gcd(180, 210));
    }
    
    @Test
    public void gcdShouldBeCommutative() {
        assertEquals (Fraction.gcd(210,180), Fraction.gcd(180, 210));
        assertEquals (Fraction.gcd(5405400,4158000), Fraction.gcd(4158000, 5405400));
        assertEquals (Fraction.gcd(12,3), Fraction.gcd(3, 12));
        assertEquals (Fraction.gcd(35, 24), Fraction.gcd(35, 24));
        assertEquals (Fraction.gcd(210,-180), Fraction.gcd(-180, 210));
    }
    
    // Pruebas de creacion de fraccionarios
    @Test
    public void shouldCanCreateIntegers() {
        Fraction ent1 = new Fraction (1234);
        assertEquals(1234,ent1.numerator());
        assertEquals(1,ent1.denominator());
        assertEquals(0,new Fraction (0).numerator());
    }

    @Test
    public void shouldCanCreateFractionsNumeratorZero() {
        assertEquals(0,new Fraction (0,10).numerator());
        assertEquals(0,new Fraction (0).numerator());
        assertEquals(0,new Fraction (0,-10).numerator());
    }
    
    @Test
    public void shouldnotSimplifyIfRelativePrimes () {
        Fraction pr= new Fraction (100,3);
        assertEquals(100, pr.numerator());
        assertEquals(3, pr.denominator());
        Fraction pr2= new Fraction (-100,1);
        assertEquals(-100, pr2.numerator());
        assertEquals(1, pr2.denominator());
    }

    @Test
    public void shouldSimplifyFractions() {
        Fraction fr1= new Fraction (5405400,4158000);
        assertEquals(13, fr1.numerator());
        assertEquals(10, fr1.denominator());
        Fraction fr2= new Fraction (-100,60);
        assertEquals(-5, fr2.numerator());
        assertEquals(3, fr2.denominator());
    }
    
    @Test
    public void ShouldNotBeNegativeSimplifiedDenominators () {
        Fraction fr1= new Fraction (100,-3);
        assertEquals(-100, fr1.numerator());
        assertEquals(3, fr1.denominator());
        Fraction fr2= new Fraction (-100,-3);
        assertEquals(100, fr2.numerator());
        assertEquals(3, fr2.denominator());
    }

    @Test
    public void shouldRealizeThatAFractionIsEqualToItself () {
        assertEquals(new Fraction (0),new Fraction (0));
        assertEquals(new Fraction (1234),new Fraction (1234));
        assertEquals (new Fraction(5405400,4158000), new Fraction(5405400,4158000));
    }
    
    @Test
    public void shouldRealizeIfTwoFractionsAreEqualWithNegatives () {
        assertEquals(new Fraction(3,100), new Fraction(-3,-100));
        assertEquals(new Fraction(-3,100), new Fraction(3,-100));
    }

    @Test
    public void shouldRealizeIfTwoFractionsCreatedDifferentAreEqual () {
        assertEquals (new Fraction(5405400,4158000), new Fraction(54054,41580));
        assertEquals (new Fraction(13,10), new Fraction(54054,41580));
    }

    @Test
    public void shouldRealizeThatAnyFractionWithNumeratorZeroIsEqual () {
        assertEquals (new Fraction(0,4158000), new Fraction(0,1));
        assertEquals (new Fraction(0,-10), new Fraction(0));
    }
    

    @Test
    public void shouldCanWriteFractionAsString () {
        assertEquals("1/2", new Fraction(1,2).toString());
        assertEquals("-1/2", new Fraction(-1,2).toString());
        assertEquals("1/2", new Fraction(4,8).toString());
    }   
}
