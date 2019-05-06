package cs1302.arcade;

/**
 * A controller for a 2048 game.
 */
public class Controller2048 {

    private Model2048 board;
    private Score score;
    private Arcade2048View view;
    private boolean gameOver;
    private boolean validMove;

    /**
     * Starts a new game of 2048.
     */
    public Controller2048(Model2048 board, Score score, Arcade2048View view) {
        this.board = board;
        this.score = score;
        this.view = view;
        gameOver = false;
        validMove = false;
    } // Controller2048(Model2048, Score)

    /**
     * Makes a player move in a given direction provided by a key event then
     * adds a sets a random tile value to the board. The view should check if
     * the game is over after a move is made.
     *
     * @param direction the direction of the player move
     */
    public void makeMove(Direction direction) {
        switch (direction) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
        } // switch
        // Set random tile
        if (validMove) {
            board.setRandomTile();
            validMove = false;
            // View must check if game is over after the move has been executed
            if (checkGameOver()) {
                gameOver = true;
            } // if
        } // if
    } // makeMove()

    /**
     * Shifts board tiles in the up direction.
     */
    private void moveUp() {
        for (int col = 0; col < board.getCols(); col++) {
            Tile[] tiles = new Tile[board.getRows()];
            for (int row = 0; row < board.getRows(); row++) {
                tiles[row] = board.getTile(row, col);
            } // for
            shiftTiles(tiles);
        } // for
    } // moveUp()

    /**
     * Shifts board tiles in the down direction.
     */
    private void moveDown() {
        for (int col = 0; col < board.getCols(); col++) {
            Tile[] tiles = new Tile[board.getRows()];
            for (int row = board.getRows() - 1; row >= 0; row--) {
                tiles[Math.abs(row - 3)] = board.getTile(row, col);
            } // for
            shiftTiles(tiles);
        } // for
    } // moveDown()

    /**
     * Shifts board tiles in the left direction.
     */
    private void moveLeft() {
        for (int row = 0; row < board.getRows(); row++) {
            Tile[] tiles = new Tile[board.getCols()];
            for (int col = 0; col < board.getCols(); col++) {
                tiles[col] = board.getTile(row, col);
            } // for
            shiftTiles(tiles);
        } // for
    } // moveLeft()

    /**
     * Shifts board tiles in the right direction.
     */
    private void moveRight() {
        for (int row = 0; row < board.getRows(); row++) {
            Tile[] tiles = new Tile[board.getCols()];
            for (int col = board.getCols() - 1; col >= 0; col--) {
                tiles[Math.abs(col - 3)] = board.getTile(row, col);
            } // for
            shiftTiles(tiles);
        } // for
    } // moveRight()

    /**
     * Shift tiles down the array by merging.
     * 
     * @param tiles a row or column of tiles being shifted down
     */
    private void shiftTiles(Tile[] tiles) {
        // Pointer to the previous tile that will be checked for merging
        int prev = 0;
        for (int i = 1; i < tiles.length; i++) {
            // If tile is not zero, then it can be checked for shifting down
            if (tiles[i].getValue() != 0) {
                // If the pointer and current tile are equal then merge and add score
                // Else if the pointer is zero then merge without adding score
                // Else move pointer up one and shift down if there is a blank space
                if (tiles[prev].getValue() == tiles[i].getValue()) {
                    int score = tiles[prev].mergeTile(tiles[i]);
                    prev++;
                    this.score.addScore(score);
                    validMove = true;
                } else if (tiles[prev].getValue() == 0) {
                    tiles[prev].mergeTile(tiles[i]);
                    validMove = true;
                } else {
                    if (prev + 1 != i) {
                        tiles[prev + 1].mergeTile(tiles[i]);
                        validMove = true;
                    }
                    prev++;
                } // if
            } // if
        } // for
    } // shiftArr(Tile[])

    /**
     * Returns true if the game is over. The view should call on this after a move
     * has been executed to check if the end game screen needs to be displayed.
     *
     * @return true if the game is over.
     */
    public boolean isGameOver() {
        return gameOver;
    } // isGameOver()

    /**
     * Returns true if conditions for the game to over are met. A game is over if
     * no possible move can be made in any direction because a board is full and
     * none of the tiles are able to merge with their neighbors.
     */
    private boolean checkGameOver() {
        if (board.isFull()) {
            for (int row = 0; row < board.getRows(); row++) {
                for (int col = 0; col < board.getCols(); col++) {
                    if (!board.checkNeighborsMatch(row, col)) {
                        return true;
                    } // if
                } // for
            } // for
        } // if
        return false;
    } // checkGameOver()

    /**
     * Resets the game to the starting state.
     */
    public void reset() {
        board.resetBoard();
        score.resetScore();
        gameOver = false;
        validMove = false;
    } // reset()

} // Controller2048
