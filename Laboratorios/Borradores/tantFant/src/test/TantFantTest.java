package test;

import domain.*;
import static org.junit.Assert.*;
import org.junit.Test;
/**
 * The test class TantFantTest.
 *
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (09/11/2022)
 */
public class TantFantTest {
    @Test
    public void shouldCanCreateANewTantFantGame() {
        try {
            TantFant tantFant = new TantFant(3);
            assertEquals(1, tantFant.player());
            assertEquals(0, tantFant.plays());
            char[][] gameBoard = tantFant.board();
            assertEquals(3, gameBoard.length);
            for (int i = 0; i < 3; i++) {
                assertEquals(3, gameBoard[i].length);
                for (int j = 0; j < 3; j++) {
                    if (i == 0) assertEquals('b', gameBoard[i][j]);
                    else if (i == 2) assertEquals('n', gameBoard[i][j]);
                    else assertEquals('v', gameBoard[i][j]);
                }
            }
        } catch (TantFantException e){
            fail("Threw a exception");
        }
    }
    
    @Test
    public void shouldThrowExceptionIfSizeIsNotGreaterThan2() {
        try { 
            TantFant tantFant = new TantFant(2);
            fail("Did not throw exception");
        } catch (TantFantException e) {
            assertEquals(TantFantException.INVALID_SIZE, e.getMessage());
        }    
    }   
    
    @Test
    public void shouldCanplay() {
        try {
            char[][] gameBoard;
            TantFant tantFant = new TantFant(3);
            gameBoard = tantFant.board();       
            tantFant.play(1, 1, 2, 2);
            gameBoard = tantFant.board();   
            assertEquals('n', gameBoard[1][1]);
            assertEquals('v', gameBoard[2][0]);
            assertEquals(2, tantFant.player());
            assertEquals(0, tantFant.plays());
            tantFant.play(3, 1, 2, 1);
            gameBoard = tantFant.board();
            /*
            for (int i = 0; i < 3; i++) {
                System.out.println(Arrays.toString(gameBoard[i]));
            } 
            */  
            assertEquals('b', gameBoard[1][0]);
            assertEquals('v', gameBoard[0][0]);
            assertEquals(1, tantFant.player());
            assertEquals(1, tantFant.plays());
        } catch (TantFantException e){
            fail("Threw a exception"+e.getMessage());
        }
    }
    
    @Test
    public void shouldThrowExceptionIfInitialPositionIsNotInsideTheBoard() {
        try { 
            TantFant tantFant = new TantFant(3);
            tantFant.play(0, 0, 2, 3);
            fail("Did not throw exception");
        } catch (TantFantException e) {
            assertEquals(TantFantException.OFF_THE_BOARD, e.getMessage());
        }    
    }
    
    @Test
    public void shouldThrowExceptionIfFinalPositionIsNotInsideTheBoard() {
        try { 
            TantFant tantFant = new TantFant(3);
            tantFant.play(1, 1, 4, 4);
            fail("Did not throw exception");
        } catch (TantFantException e) {
            assertEquals(TantFantException.OFF_THE_BOARD, e.getMessage());
        }    
    }
    
    @Test
    public void shouldThrowExceptionIfInFinalPositionThereIsAPiece() {
        try { 
            TantFant tantFant = new TantFant(3);
            tantFant.play(1, 1, 1, 2);
            fail("Did not throw exception");
        } catch (TantFantException e) {
            assertEquals(TantFantException.INVALID_MOVEMENT, e.getMessage());
        }    
    }
    
    @Test
    public void shouldThrowExceptionIfTheMoveIsNotToAnAdjacentSquare() {
        try { 
            TantFant tantFant = new TantFant(3);
            tantFant.play(1, 3, 2, 1);
            fail("Did not throw exception");
        } catch (TantFantException e) {
            assertEquals(TantFantException.INVALID_MOVEMENT, e.getMessage());
        }    
    }
    
    @Test
    public void shouldThrowExceptionIfTheFinalPositionIsEqualToTheInitial() {
        try { 
            TantFant tantFant = new TantFant(3);
            tantFant.play(1, 3, 1, 3);
            fail("Did not throw exception");
        } catch (TantFantException e) {
            assertEquals(TantFantException.INVALID_MOVEMENT, e.getMessage());
        }    
    }
    

    @Test
    public void shoulWinHorizontally() {
        try {
            TantFant tantFant = new TantFant(3);  
            assertFalse(tantFant.play(1, 3, 2, 3));
            assertFalse(tantFant.play(3, 2, 2, 2));
            assertFalse(tantFant.play(1, 1, 2, 1));
            assertFalse(tantFant.play(2, 2, 1, 1));
            assertTrue(tantFant.play(1, 2, 2, 2));
        } catch (TantFantException e){
            fail("Threw a exception"+e.getMessage());
        }
    }
    
    @Test
    public void shoulCanWinVertically() {
        try {
            TantFant tantFant = new TantFant(3);  
            assertFalse(tantFant.play(1, 3, 2, 2));
            assertFalse(tantFant.play(3, 2, 2, 3));
            assertFalse(tantFant.play(1, 1, 2, 1));
            assertFalse(tantFant.play(2, 3, 1, 3));
            assertTrue(tantFant.play(2, 1, 3, 2));
        } catch (TantFantException e){
            fail("Threw a exception"+e.getMessage());
        }
    }
    
    @Test
    public void shoulCanWinOnTheMainDiagonal() {
        try {
            TantFant tantFant = new TantFant(3);  
            assertFalse(tantFant.play(1, 1, 2, 1));
            assertFalse(tantFant.play(3, 1, 2, 2));
            assertFalse(tantFant.play(2, 1, 3, 1));
            assertFalse(tantFant.play(2, 2, 2, 3));
            assertTrue(tantFant.play(1, 2, 2, 2));
        } catch (TantFantException e){
            fail("Threw a exception"+e.getMessage());
        }
    }
    
    @Test
    public void shoulCanWinOnTheSecondaryDiagonal() {
        try {
            TantFant tantFant = new TantFant(3);  
            assertFalse(tantFant.play(1, 3, 2, 3));
            assertFalse(tantFant.play(3, 3, 2, 2));
            assertFalse(tantFant.play(2, 3, 3, 3));
            assertFalse(tantFant.play(2, 2, 2, 1));
            assertTrue(tantFant.play(1, 2, 2, 2));
        } catch (TantFantException e){
            fail("Threw a exception");
        }
    }
    
    @Test
    public void shoulNotWinAtTheOriginLine() {
        try {
            TantFant tantFant = new TantFant(3);  
            assertFalse(tantFant.play(1, 3, 2, 2));
            assertFalse(tantFant.play(3, 2, 2, 3));
            assertFalse(tantFant.play(2, 2, 1, 3));
        } catch (TantFantException e){
            fail("Threw a exception"+e.getMessage());
        }
    }   
}
