package cs1302.arcade;

/**
 * A model for a 2048 game board.
 */
public class Board2048 {
    
    private static final int ROWS = 4;
    private static final int COLS = 4;

    private Tile[][] board = new Tile[ROWS][COLS];
    
    /**
     * Sets the initial model for the 2048 game.
     */
    public Board2048() {
        resetScore();
    } // Model2048()

    /**
     * Returns the game board.
     *
     * @return a 2d array of tiles representing the game board
     */
    public Tile[][] getBoard() {
        return board;
    } // getBoard()

    /**
     * Returns a tile from a specified position on the board.
     *
     * @param row the row to retrieve the tile from
     * @param col the column to retrieve the tile from
     * @return a tile from the specified position on the board
     */
    public Tile getTile(int row, int col) {
        return board[row][col];
    } // getTile(int, int)

    /**
     * Sets the tile at the specified position on the board.
     * 
     * @param tile the tile to put on the board
     * @param row the row to put the tile in
     * @param col the column to put the tile in
     */
    public void setTile(Tile tile, int row, int col) {
        board[row][col] = tile;
    } // setTile(Tile, int, int)

    /**
     * Resets the board.
     */
    public void resetBoard() {
        board = new Tile[ROWS][COLS];
    } // resetBoard()

} // Model2048
