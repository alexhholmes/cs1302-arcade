package cs1302.arcade;

import java.util.Random;

public class Controller2048 {

    private Model2048 board;
    private Score score;

    public Controller2048(Model2048 boardModel, Score score) {
        this.boardModel = boardModel;
        this.score = score;
        reset();
    } // Controller2048(Model2048, Score)

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
        } // switch
    } // makeMove()

    private void moveUp() {
        if (checkMove(1, 0)) { 
            
        } // if
    } // moveUp()
    
    private void moveDown() {

    } // moveDown()
    

    private void moveLeft() {

    } // moveLeft()
    
    private void moveRight() {

    } // moveRight()

    private void checkMoveRows(int row, int col) {
        for (int i = row 
    } // checkMoveRows(int)

    private void checkMoveColumns(int direction) {

    } // checkMovesColumns(int)

    /**
     * Makes a new tile at a random position that has an empty tile.
     */
    private void makeNewTile() {
        while (!board.isFull) {
            // Generate random row and col values
            Random random = new Random();
            int randRow = random.nextInt(3);
            int randCol = random.nextInt(3);
            if (board.
        } // while

    } // makeNewTile()

    /**
     * Makes a new {@code Tile} object and randomly sets its value to 2 or 4.
     *
     * @param row the row of the tile
     * @param col the column of the tile
     */
    private void setNewTile(int row, int col) {
        // Randomly pick 2 or 4
        Random random = new Random();
        int num = (rand.nextInt(1) + 1) * 2;
        // Set new tile on board
        Tile tile = new Tile(num);
        model.setTile(tile, row, col);
    } // setNewTile(int, int)
    
    /**
     * Resets the game to the starting state.
     */
    public void reset() {
        boardModel.resetBoard();
        
        score.resetScore();
        // TODO
    } // reset()

} // Controller2048
