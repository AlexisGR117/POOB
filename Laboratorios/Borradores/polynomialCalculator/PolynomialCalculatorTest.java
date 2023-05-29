

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class PolynomialCalculatorTest.
 *
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (06/09/2022)
 */
public class PolynomialCalculatorTest{
    int[][] f1 = {{5, 7}, {3, 4}};
    int[][] f2 = {{8, 3}, {9, 7}, {98, 12}};
    int[][] f6 = {{14, 3}, {-5, 3}, {13, 12},{1, 6}};
    int[][] f7 = {{4, 1}, {1, 2}};
    int[][] f8 = {{-8, 1}, {-1, 1}, {0, 1}, {2, 1}, {0, 1}, {1, 1}};
    int[][] f9 = {{1, 1}, {-2, 1}, {1, 1}};
    @Test
    public void shouldCanCreateVariables(){
        PolynomialCalculator pc = new PolynomialCalculator();
        pc.create("a");
        assertTrue(pc.getVariables().containsKey("a"));
    }    

    @Test
    public void shouldCanAssignPolynomialsWithArraysOfIntegers(){
        PolynomialCalculator pc = new PolynomialCalculator();
        pc.create("a");
        pc.assign("a", f1);
        Polynomial p = new Polynomial();
        p.addTerm(f1);
        assertEquals(pc.getVariables().get("a").toString(), p.toString());
    } 

    @Test
    public void shouldCanEvaluatePolynomials(){
        PolynomialCalculator pc = new PolynomialCalculator();
        pc.create("a");
        pc.assign("a", f2);
        int[] x = {3, 4, 5, 6};
        assertEquals(pc.evaluate("a", x), "3361/42, 2908/21, 8957/42, 6392/21");
    }     
    
    @Test
    public void shouldNotEvaluatePolynomialsToNonexistentVariables(){
        PolynomialCalculator pc = new PolynomialCalculator();
        int[] x = {3, 4, 5, 6};
        assertNull(pc.evaluate("a", x));
    } 
    
    @Test
    public void shouldCanWritePolynomialsAsString(){
        PolynomialCalculator pc = new PolynomialCalculator();
        pc.create("a");
        pc.assign("a", f2);
        assertEquals(pc.toString("a"), "8/3+9/7x+49/6x2");
        pc.create("b");
        pc.assign("b", f8);
        assertEquals(pc.toString("b"), "-8/1-1/1x+0/1x2+2/1x3+0/1x4+1/1x5");
    }      
    
    @Test
    public void shouldNotWritePolynomialsToNonexistentVariables(){
        PolynomialCalculator pc = new PolynomialCalculator();
        assertNull(pc.toString("a"));
    }   
    
    @Test
    public void shouldCanSayIfTheLatOperationWasSccesfull(){
        PolynomialCalculator pc = new PolynomialCalculator();
        // Create Variables
        pc.create("b");
        pc.create("b");
        assertFalse(pc.ok());
        pc.create("c");
        assertTrue(pc.ok());
        // Assign polynomials
        pc.assign("d", f1);
        assertFalse(pc.ok());
        pc.create("d");
        pc.assign("d", f1);
        assertTrue(pc.ok());
        // Evaluate Polynomials
        int[] x = {3, 4, 5, 6};
        pc.evaluate("a", x);
        assertFalse(pc.ok());
        pc.evaluate("b", x);
        assertTrue(pc.ok());
        // Write Polynomials
        pc.toString("a");
        assertFalse(pc.ok());
        pc.toString("b");
        assertTrue(pc.ok());
    }       
    
    @Test
    public void shouldCanGetAllVariables(){
        PolynomialCalculator pc = new PolynomialCalculator();
        pc.create("a");
        pc.assign("a", f2);
        pc.create("b");
        pc.assign("b", f8);
        assertEquals(pc.getVariables().toString(), "{a=8/3+9/7x+49/6x2, b=-8/1-1/1x+0/1x2+2/1x3+0/1x4+1/1x5}");
    }    
    
    @Test
    public void shouldCanAssignTheValueOfASum(){
        PolynomialCalculator pc = new PolynomialCalculator();
        pc.create("a");
        pc.create("b");
        pc.create("c");
        pc.create("d");
        pc.assign("a", f1);
        pc.assign("b", f2);
        int[][] f3 = {{71, 21}, {57, 28}, {98, 12}};
        pc.assign("c", f3);
        pc.assign("d", "a", '+', "b");
        assertEquals(pc.getVariables().get("d").toString(), pc.getVariables().get("c").toString());
    }
    
    @Test
    public void shouldCanAssignTheValueOfASubstraction(){
        PolynomialCalculator pc = new PolynomialCalculator();
        pc.create("a");
        pc.create("b");
        pc.create("c");
        pc.create("d");
        pc.create("e");
        pc.create("f");
        pc.assign("a", f1);
        pc.assign("b", f2);
        int[][] f4 = {{-41, 21}, {-15, 28}, {-98, 12}};
        int[][] f5 = {{41, 21}, {15, 28}, {98, 12}};
        pc.assign("c", f4);
        pc.assign("d", "a", '-', "b");
        pc.assign("e", f5);
        pc.assign("f", "b", '-', "a");
        assertEquals(pc.getVariables().get("c").toString(), pc.getVariables().get("d").toString());
        assertEquals(pc.getVariables().get("e").toString(), pc.getVariables().get("f").toString());
    }
    
    @Test
    public void shouldCanAssignTheValueOfAMultiplication(){
        PolynomialCalculator pc = new PolynomialCalculator();
        pc.create("a");
        pc.create("b");
        pc.create("c");
        pc.create("d");
        pc.create("e");
        pc.assign("a", f1);
        pc.assign("b", f2);
        int[][] f4 = {{40, 21}, {143, 49}, {571, 84}, {49, 8}};
        pc.assign("c", f4);
        pc.assign("d", "a", '*', "b");
        pc.assign("e", "b", '*', "a");
        assertEquals(pc.getVariables().get("c").toString(), pc.getVariables().get("d").toString());
        assertEquals(pc.getVariables().get("c").toString(), pc.getVariables().get("e").toString());
    }
    
    @Test
    public void shouldCanAssignTheValueOfADivision(){
        PolynomialCalculator pc = new PolynomialCalculator();
        pc.create("a");
        pc.create("b");
        pc.create("c");
        pc.create("d");
        pc.create("e");
        pc.create("f");
        pc.create("g");
        pc.create("h");
        pc.assign("a", f6);
        pc.assign("b", f7);
        pc.assign("c", f8);
        pc.assign("d", f9);
        int[][] f10 = {{2, 3}, {-1, 2}, {1, 3}};
        pc.assign("e", f10);
        int[][] f11 = {{8, 1}, {5, 1}, {2, 1}, {1, 1}};
        pc.assign("f", f11);
        pc.assign("h", "c", '/', "d");
        pc.assign("g", "a", '/', "b");
        assertEquals(pc.getVariables().get("e").toString(), pc.getVariables().get("g").toString());
        assertEquals(pc.getVariables().get("f").toString(), pc.getVariables().get("h").toString());
    }    

    @Test
    public void shouldCanAssignTheValueOfADerivation(){
        PolynomialCalculator pc = new PolynomialCalculator();
        pc.create("a");
        pc.create("b");
        pc.create("c");
        pc.create("e");
        pc.create("f");
        pc.create("g");
        pc.create("h");
        pc.create("i");
        pc.create("j");
        pc.assign("a", f1);
        pc.assign("b", f2);
        pc.assign("c", f6);
        int[][] f12 = {{3, 4}};
        pc.assign("e", f12);
        int[][] f13 = {{9, 7}, {49, 3}};
        pc.assign("f", f13);
        int[][] f14 = {{-5, 3}, {13, 6}, {1, 2}};
        pc.assign("g", f14);
        pc.assign("h", 'd', "a");
        pc.assign("i", 'd', "b");
        pc.assign("j", 'd', "c");
        assertEquals(pc.getVariables().get("e").toString(), pc.getVariables().get("h").toString());
        assertEquals(pc.getVariables().get("f").toString(), pc.getVariables().get("i").toString());
        assertEquals(pc.getVariables().get("g").toString(), pc.getVariables().get("j").toString());
    }
    
    @Test
    public void shouldCanAssignTheValueOfAIntegration(){
        PolynomialCalculator pc = new PolynomialCalculator();
        pc.create("a");
        pc.create("b");
        pc.create("c");
        pc.create("e");
        pc.create("f");
        pc.create("g");
        pc.create("h");
        pc.create("i");
        pc.create("j");
        pc.assign("a", f1);
        pc.assign("b", f2);
        pc.assign("c", f6);
        int[][] f12 = {{0, 1},{5, 7}, {3, 8}};
        pc.assign("e", f12);
        int[][] f13 = {{0,1},{8, 3}, {9, 14}, {49,18}};
        pc.assign("f", f13);
        int[][] f14 = {{0, 1}, {14, 3}, {-5, 6}, {13, 36}, {1, 24}};
        pc.assign("g", f14);
        pc.assign("h", 'i', "a");
        pc.assign("i", 'i', "b");
        pc.assign("j", 'i', "c");
        assertEquals(pc.getVariables().get("e").toString(), pc.getVariables().get("h").toString());
        assertEquals(pc.getVariables().get("f").toString(), pc.getVariables().get("i").toString());
        assertEquals(pc.getVariables().get("g").toString(), pc.getVariables().get("j").toString());
    }
    
    @Test
    public void shouldCanAssignTheValueOfAMultipleDerivation(){
        PolynomialCalculator pc = new PolynomialCalculator();
        pc.create("a");
        pc.create("b");
        pc.create("c");
        pc.create("e");
        pc.create("f");
        pc.create("g");
        pc.create("h");
        pc.create("i");
        pc.create("j");
        pc.assign("a", f1);
        pc.assign("b", f2);
        pc.assign("c", f6);
        int[][] f12 = {{3, 4}};
        pc.assign("e", f12);
        int[][] f13 = {{49,3}};
        pc.assign("f", f13);
        int[][] f14 = {{13, 6}, {1, 1}};
        pc.assign("g", f14);
        pc.assign("h", 'd', "a", 1);
        pc.assign("i", 'd', "b", 2);
        pc.assign("j", 'd', "c", 2);
        assertEquals(pc.getVariables().get("e").toString(), pc.getVariables().get("h").toString());
        assertEquals(pc.getVariables().get("f").toString(), pc.getVariables().get("i").toString());
        assertEquals(pc.getVariables().get("g").toString(), pc.getVariables().get("j").toString());
    }
    
    @Test
    public void shouldCanAssignTheValueOfAMultipleIntegration(){
        PolynomialCalculator pc = new PolynomialCalculator();
        pc.create("a");
        pc.create("b");
        pc.create("c");
        pc.create("e");
        pc.create("f");
        pc.create("g");
        pc.create("h");
        pc.create("i");
        pc.create("j");
        pc.assign("a", f1);
        pc.assign("b", f2);
        pc.assign("c", f6);
        int[][] f12 = {{0, 1}, {5, 7}, {3, 8}};
        pc.assign("e", f12);
        int[][] f13 = {{0, 1}, {0, 1}, {4,3}, {3, 14}, {49, 72}};
        pc.assign("f", f13);
        int[][] f14 = {{0, 1}, {0, 1}, {7, 3}, {-5, 18}, {13, 144}, {1, 120}};
        pc.assign("g", f14);
        pc.assign("h", 'i', "a", 1);
        pc.assign("i", 'i', "b", 2);
        pc.assign("j", 'i', "c", 2);
        assertEquals(pc.getVariables().get("e").toString(), pc.getVariables().get("h").toString());
        assertEquals(pc.getVariables().get("f").toString(), pc.getVariables().get("i").toString());
        assertEquals(pc.getVariables().get("g").toString(), pc.getVariables().get("j").toString());
    }
}
