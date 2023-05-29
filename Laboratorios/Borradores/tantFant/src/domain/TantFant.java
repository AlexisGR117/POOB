package domain;

/**
 * Tant Fant game.
 *
 * @author Angel Cuervo y Jefer Gonzalez
 * @version 1.0 (11/04/2022)
 */
public class TantFant {
    private int playerOneMoves, playerTwoMoves;
    private byte gameTurn;
    private char[][] gameBoard;
    private int size;
    /**
     * Create a new Tant Fant game.
     * @param n Game board size (nxn).
     * @throws TantFantException INVALID_SIZE, If the size of the board is not greater than 2.
     */
    public TantFant(int n) throws TantFantException {
        if (n <= 2) throw new TantFantException(TantFantException.INVALID_SIZE);
        size = n;
        gameBoard = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0) gameBoard[i][j] = 'b';
                else if (i == size - 1) gameBoard[i][j] = 'n';
                else gameBoard[i][j] = 'v';
            }
        }
        playerOneMoves = 0;
        playerTwoMoves = 0;
        gameTurn = 1;
    }
    
    /**
     * Makes a move in the game.
     * @param initialRow Horizontal starting position.
     * @param initialColumn Vertical starting position.
     * @param finalRow Horizontal end position.
     * @param finalColumn Vertical end position.
     * @throws TantFantException INVALID_MOVEMENT, If the move cannot be made. OFF_THE_BOARD, If the given position is not inside the board. INVALID_PLAYER, If it is the other player's turn. 
     * @return If the player wins the game.
     */
    public boolean play(int initialRow, int initialColumn, int finalRow, int finalColumn) throws TantFantException {
        if (initialRow > size || initialRow < 1 || finalRow > size || finalRow < 1 ||
            initialColumn > size || finalColumn < 1 || finalColumn > size || initialColumn < 1) {
            throw new TantFantException(TantFantException.OFF_THE_BOARD);
        }
        initialRow = size-initialRow;
        initialColumn -= 1;
        finalRow = size-finalRow;
        finalColumn -= 1;
        if (gameBoard[initialRow][initialColumn] == 'v' || gameBoard[finalRow][finalColumn] == 'b' || gameBoard[finalRow][finalColumn] == 'n' ||
        	gameBoard[finalRow][finalColumn] == 'c' || gameBoard[finalRow][finalColumn] == 'o' || Math.abs(finalColumn - initialColumn) > 1 || Math.abs(finalRow - initialRow) > 1) {
            throw new TantFantException(TantFantException.INVALID_MOVEMENT);
        }
        if ((gameTurn == 1 && (gameBoard[initialRow][initialColumn] == 'b' || gameBoard[initialRow][initialColumn] == 'c')) ||
        	(gameTurn == 2 && (gameBoard[initialRow][initialColumn] == 'n' || gameBoard[initialRow][initialColumn] == 'o'))) {
            throw new TantFantException(TantFantException.INVALID_PLAYER);
        }
        gameBoard[finalRow][finalColumn] = gameBoard[initialRow][initialColumn];
        gameBoard[initialRow][initialColumn] = 'v';
        char c;
        if (gameTurn == 1) {
            c = 'n';
            playerOneMoves += 1;
            gameTurn = 2;
        } else {
            c = 'b';
            playerTwoMoves += 1;
            gameTurn = 1;
        }
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (gameBoard[i][j] == 'B' || gameBoard[i][j] == 'N') gameBoard[i][j] = 'v';
                if (gameBoard[i][j] == 'o') gameBoard[i][j] = 'n';
                if (gameBoard[i][j] == 'c') gameBoard[i][j] = 'b';
            }
        } 
        return win(finalRow, finalColumn, c);
    }
    
    /**
     * @return Game board.
     */
    public char[][] board() {
        return gameBoard;
    }
    
    /**
     * @return Player who has the turn.
     */
    public byte player() {
        return gameTurn;
    }
    
    /**
     * @return Number of moves of the player who has the turn.
     */
    public int plays(){
        int moves = playerOneMoves;
        if (gameTurn == 2) moves = playerTwoMoves;
        return moves;
    }    
    
    /*
     * It tells if the move made makes a player win.
     */
    private boolean win(int finalRow, int finalColumn, char c){
        boolean h = true, v = true, d1 = true, d2 = true;
        if (finalRow == finalColumn || finalRow + finalColumn == size - 1) {
            for (int i = 0; i < size && (d1 || d2); i++) {
                if (gameBoard[i][i] != c) d1 = false;
                if (gameBoard[i][size-1-i] != c) d2 = false;
            }    
        } else{
            d1 = false;
            d2 = false;
        }
        if (finalRow == 0 && (c == 'b')) h = false;
        if (finalRow == size - 1 && c == 'n') h = false;
        for (int i = 0; i < size && (h || v); i++) {
            if (gameBoard[finalRow][i] != c) h = false;
            if (gameBoard[i][finalColumn] != c) v = false;               
        }
        return h || v || d1 || d2;
    }
    
    /**
     * Gives the size of the board.
     * @return Board size.
     */
    public int getSize(){
        return size;
    }   
    
    /**
     * Change the board with the possible moves that the given piece can make.
     * @param r Row number.
     * @param c Column number.
     */
    public void possibles(int r, int c){
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (gameBoard[i][j] == 'B' || gameBoard[i][j] == 'N'){
                    gameBoard[i][j] = 'v';
                }
                if (gameBoard[i][j] == 'o') gameBoard[i][j] = 'n';
                if (gameBoard[i][j] == 'c') gameBoard[i][j] = 'b';
            }
        } 
        if(inLatice(r, c) && gameBoard[r][c] != 'v') {
            for(int dr=-1; dr<2;dr++){
                for (int dc=-1; dc<2;dc++){
                    if ((dr!=0 || dc!=0) && inLatice(r+dr,c+dc) && (gameBoard[r+dr][c+dc]=='v')){
                        if (gameBoard[r][c] == 'n') gameBoard[r+dr][c+dc] = 'N';
                        if (gameBoard[r][c] == 'b') gameBoard[r+dr][c+dc] = 'B';
                    }
                }
            }        
        	if (gameBoard[r][c] == 'n') gameBoard[r][c] = 'o';
            if (gameBoard[r][c] == 'b') gameBoard[r][c] = 'c';
        }
    } 
    
    /*
     * Indicates if the given position is inside the game board.
     */
    private boolean inLatice(int r, int c){
        return ((0<=r) && (r<size) && (0<=c) && (c<size));
    }
}