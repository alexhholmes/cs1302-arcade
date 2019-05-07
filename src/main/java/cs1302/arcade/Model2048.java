package cs1302.arcade;

import java.util.Random;

/**
 * A model for a 2048 game board.
 */
public class Model2048 {

    /** Number of board rows */
    private static final int ROWS = 4;
    /** Number of board columns */
    private static final int COLS = 4;

    /** The game board */
    private Tile[][] board = new Tile[ROWS][COLS];

    /**
     * Sets the initial board model for the 2048 game.
     */
    public Model2048() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = new Tile();
            } // for
        } // for
        setRandomTile();
        setRandomTile();
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
     * Return the number of rows of this board.
     *
     * @return number of rows of this board.
     */
    public int getRows() {
        return ROWS;
    } // getRows()

    /**
     * Return the number of columns of this board.
     *
     * @return number of columns of this board.
     */
    public int getCols() {
        return COLS;
    } // getCols()

    /**
     * Resets the board and sets two random tiles.
     */
    public void resetBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col].resetTile();
            } // for
        } // for
        setRandomTile();
        setRandomTile();
    } // resetBoard()

    /**
     * Returns true if all Tiles on the board are not empty.
     *
     * @return true if all Tiles on the board are not empty.
     */
    public boolean isFull() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (getTile(row, col).isEmpty()) {
                    return false;
                } // if
            } // for
        } // for
        return true;
    } // isFull()

    /**
     * Returns true if the tile at the specified location has a value equal to
     * any of its neighbors.
     *
     * @param row the row of the tile
     * @param col the column of the tile
     * @return true if the tile is equal to any its neighbors
     */
    public boolean checkNeighborsMatch(int row, int col) {
        if (row - 1 >= 0) {
            if (getTile(row, col).matches(getTile(row - 1, col))) {
                return true;
            } // if
        } // if
        if (row + 1 < ROWS) {
            if (getTile(row, col).matches(getTile(row + 1, col))) {
                return true;
            } // if
        } // if
        if (col - 1 >= 0) {
            if (getTile(row, col).matches(getTile(row, col - 1))) {
                return true;
            } // if
        } // if
        if (col + 1 < COLS) {
            if (getTile(row, col).matches(getTile(row, col + 1))) {
                return true;
            } // if
        } // if
        return false;
    } // checkNeighborsMatch(int, int)

    /**
     * Sets a random tile on the board to a new random value.
     */
    public void setRandomTile() {
        Random random = new Random();
        while (true) {
            int randRow = random.nextInt(ROWS);
            int randCol = random.nextInt(COLS);
            if (getTile(randRow, randCol).isEmpty()) {
                getTile(randRow, randCol).setRandomValue();
                break;
            } // if
        } // while
    } // setRandomTile

} // Model2048
