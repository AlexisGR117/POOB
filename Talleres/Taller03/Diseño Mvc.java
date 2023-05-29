package domain;
/**
 * Tant Fant game.
 *
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (11/04/2022)
 */
public class TantFant{
    private int playerOneMoves;
    private int playerTwoMoves;
    private byte gameTurn;
    private char[][] gameBoard;
    /**
     * Create a new Tant Fant game.
     * @param n Game board size (nxn).
     * @throws TantFantException INVALID_SIZE, If the size of the board is not greater than 2.
     */
    public TantFant(int n) throws TantFantException{
    }
    
    /**
     * Makes a move in the game.
     * @param initialRow Horizontal starting position.
     * @param initiaColumn Vertical starting position.
     * @param finalRow Horizontal end position.
     * @param finalColumn Vertical end position.
     * @throws TantFantException INVALID_MOVEMENT, If the move cannot be made. OFF_THE_BOARD, If the given position is not inside the board. INVALID_PLAYER, If it is the other player's turn. 
     * @return If the player wins the game.
     */
    public boolean play(int initialRow, int initiaColumn, int finalRow, int finalColumn) throws TantFantException{
        return false;
    }
    
    /**
     * @return Game board.
     */
    public char[][] board(){
        return null;
    }
    
    /**
     * @return Player who has the turn.
     */
    public byte player(){
        return 0;
    }
    
    /**
     * @return Number of moves of the player who has the turn.
     */
    public int plays(){
        return 0;
    }    
}