package cs1302.arcade;

import javafx.scene.paint.Color;

/** 
 * The internal storage for the tetris board and all of 
 * its methods.
 */
public class TetrisBoard {

    /** 2D array the holds the game's storage */
    Color[][] board = new Color[10][20];

    /** 
     * Creates a blank tetris board.
     */
    public TetrisBoard(){
        for(int x = 0; x < 10; x ++){
            for(int y = 0; y < 20; y++){
                board[x][y] = Color.BLACK;
            }// for
        }// for
    }//TetrisBoard

    /**
     * Resets the board to be completely blank.
     */
    public void resetBoard(){
        for(int x = 0; x < 10; x ++){
            for(int y = 0; y < 20; y++){
                board[x][y] = Color.BLACK;
            }// for
        }// for
    }// resetBoard

    /** 
     * Returns the color in the given coordinance.
     *
     * @param x the desired column
     * @param y the desired row
     * @return the color in the given spot
     */
    public Color getValue(int x, int y){
        return board[x][y];
    }// getValue

    /**
     * Sets a specific block to a desired color.
     *
     * @param color desired color
     * @param x column of desired color
     * @param y row of desired color
     */
    public void setBlock(Color color, int x, int y){
        board[x][y] = color;
    }// setblock

    /**
     * Checks to see if a piece can move to the left.
     * @param piece the piece in question
     * @return true if the piece has nothing in its way
     */
    public boolean canShiftLeft(TetrisPiece piece){ 
        this.removePiece(piece);
        piece.shiftLeft();
        for(int i = 1; i<=4; i++){
            int tempX = piece.getBlock(i).getColumn();
            int tempY = piece.getBlock(i).getRow();
            if(this.isInBounds(tempX, tempY)){
                if(board[tempX][tempY] != Color.BLACK){
                    piece.shiftRight();
                    this.addPiece(piece);
                    return false;
                }// if
            }// if
            else{
                piece.shiftRight();
                this.addPiece(piece);
                return false;
            }//else
        }// for
        piece.shiftRight();
        this.addPiece(piece);
        return true; 
    }// canShiftLeft

    /** 
     * Checks to see if a given piece can be added to the board.
     *
     * @param piece the piece in question
     * @return boolean true if the piece can be added
     */
    public boolean canAddToTop(TetrisPiece piece){
        for(int i=1; i<=4; i++){
            int tempX = piece.getBlock(i).getColumn();
            int tempY = piece.getBlock(i).getRow();
            if(board[tempX][tempY] != Color.BLACK){
                return false;
            }// if
        }// for
        return true;
    }//canAddToTop

    /** 
     * Moves a piece one space to the left on the board.
     *
     * @param piece the piece in question
     */
    public void shiftPieceLeft(TetrisPiece piece){
        if(this.canShiftLeft(piece)){
            this.removePiece(piece);
            piece.shiftLeft();
            this.addPiece(piece);
        }// if
    }//shiftPieceLeft

    /**
     * Checks to see if a piece can move to the right.
     *
     * @param piece the piece in question
     * @return true if the piece has nothing in its way
     */
    public boolean canShiftRight(TetrisPiece piece){
        this.removePiece(piece);
        piece.shiftRight();
        for(int i = 1; i<=4; i++){
            int tempX = piece.getBlock(i).getColumn();
            int tempY = piece.getBlock(i).getRow();
            if(this.isInBounds(tempX, tempY)){
                if(board[tempX][tempY] != Color.BLACK){
                    piece.shiftLeft();
                    this.addPiece(piece);
                    return false;
                }// if
            }// if
            else{
                piece.shiftLeft();
                this.addPiece(piece);
                return false;
            }//else
        }// for
        piece.shiftLeft();
        this.addPiece(piece);
        return true; 
    }// canShiftRight

    /**
     * Moves a piece one space to the right on the board.
     *
     * @param piece the piece in question
     */
    public void shiftPieceRight(TetrisPiece piece){
        if(this.canShiftRight(piece)){
            this.removePiece(piece);
            piece.shiftRight();
            this.addPiece(piece);
        }// if
    }//shiftPieceRight

    /**
     * Clears a desired row of blocks.
     *
     * @param rowCleared desired row
     */
    public void rowClear(int rowCleared){
        for(int x = 0; x < 10; x++){
            board[x][rowCleared] = Color.BLACK;
        }// for
    }// rowclear

    /** 
     * Shifts the blocks down based on the rows that
     * have been cleared.
     *
     * @param rowCleared the lowest row that was just cleared
     */
    public void shiftDown(int rowCleared){
        this.rowClear(rowCleared);
        for(int y = rowCleared; y >0; y--){
            for(int i = 0; i < 10; i++){
                board[i][y] = board[i][y-1];
            }// for
        }// for
    }// shiftDown

    /** 
     * Adds a piece to the top of the board.
     *
     * @param piece the TetrisPiece to be added
     */
    public void addPiece(TetrisPiece piece){
        for(int i = 1; i <= 4; i++){
            int tempX = piece.getBlock(i).getColumn();
            int tempY = piece.getBlock(i).getRow();
            Color tempColor = piece.getBlock(i).getColor();
            if(board[tempX][tempY] == Color.BLACK){
                board[tempX][tempY] = tempColor;
            }// if
        }// for
    }// addPiece

    /** 
     * Removes a desired piece from the board.
     * 
     * @param piece to be removed
     */
    public void removePiece(TetrisPiece piece){
        for(int i = 1; i <=4; i++){
            int tempX = piece.getBlock(i).getColumn();
            int tempY = piece.getBlock(i).getRow();
            board[tempX][tempY] = Color.BLACK;
        }// for
    }// removePiece

    /** 
     * Sees if a piece can fall 1 row.
     *
     * @param piece the piece in question
     * @return true if there are no blocks directly below the piece
     * and the piece is not at the bottum of the board
     */
    public boolean canDrop(TetrisPiece piece){
        this.removePiece(piece);
        piece.drop();
        for(int i = 1; i<=4; i++){
            int tempX = piece.getBlock(i).getColumn();
            int tempY = piece.getBlock(i).getRow();
            if(this.isInBounds(tempX, tempY)){
                if(board[tempX][tempY] != Color.BLACK){
                    piece.unDrop();
                    this.addPiece(piece);
                    return false;
                }// if
            }// if
            else{
                piece.unDrop();
                this.addPiece(piece);
                return false;
            }//else
        }// for
        piece.unDrop();
        this.addPiece(piece);
        return true; 
    }// canDrop

    /**
     * Lowers a piece by 1 row.
     *
     * @param piece the piece in question
     */
    public void dropPiece(TetrisPiece piece){
        if(this.canDrop(piece)){
            this.removePiece(piece);
            piece.drop();
            this.addPiece(piece);
        }// if
    }// dropPiece

    /**
     * Determines if a given row should be cleared
     *
     * @param row the row on the board to be checked
     * @return true if the board has no black blocks
     */
    public boolean isRowClear(int row){
        boolean cleared = true;
        for(int i = 0; i < 10; i++){
            if(board[i][row] == Color.BLACK){
                cleared = false;
            }// if
        }// for
        return cleared;
    }// isClear

    /**
     * Clears the rows that need to be cleared and 
     * returns an array that is used to calculate the score
     * earned from the rows cleared
     *
     * @return boolean array of the 4 rows that were or were not cleared
     */
    public boolean[] rowsCleared(){
        boolean[] cleared = new boolean[4];
        boolean hasFound = false;
        for(int i = 0; i < 4; i++){
            cleared[i] = false;
        }// for
        int clearCount = 0;
        for(int i = 0; i<20; i++){
            if(hasFound){
                if(isRowClear(i)){
                    cleared[clearCount] = true;
                    clearCount++;
                    this.shiftDown(i);
                }// if
                else{
                    clearCount++;
                }// else
            }// if
            else{
                if(isRowClear(i)){
                    cleared[clearCount] = true;
                    clearCount++;
                    this.shiftDown(i);
                    hasFound = true;
                }// if
            }// else
        }// for
        return cleared;
    }// rowsCleared

    /** 
     * Determines the score earned.
     *
     * @return the amount of points earned
     */ 
    public int calcScore(){
        boolean[] cleared = this.rowsCleared();
        int numCleared = 0;
        for(int i = 0; i<4; i++){
            if(cleared[i]){
                numCleared++;
            }// if
        }// for
        switch(numCleared){
            case 4:
                return 1200;
            case 1:
                return 40;
            case 3:
                if(cleared[1] == false || cleared[2] == false){
                    return 140;
                }// if
                else{
                    return 300;
                }// else
            case 2:
                if((cleared[0]==false&&cleared[1]==false)||
                        (cleared[2]==false&&cleared[3]==false)||
                        (cleared[0]==false&&cleared[3]==false)){
                    return 100;
                } else {
                    return 80;
                }// else
            default:
                return 0;
        }// switch
    }// calcScore

    /**
     * Determines if given coordinance are out of bounds.
     *
     * @param x the column of the given coordinance
     * @param y the row of the given coordinance
     * @return true if they are within the tetrisboard
     */
    public boolean isInBounds(int x, int y){
        if(x < 0 || x > 9 || y < 0 || y > 19){
            return false;
        }// if
        return true;
    }// isInBounds

    /** 
     * Determines if a piece can rotate.
     *
     * @param piece the piece in question
     * @return true if the piece has room to rotate
     */
    public boolean canRotate(TetrisPiece piece){
        this.removePiece(piece);
        piece.rotate();
        for(int i = 1; i<=4; i++){
            int tempX = piece.getBlock(i).getColumn();
            int tempY = piece.getBlock(i).getRow();
            if(this.isInBounds(tempX, tempY)){
                if(board[tempX][tempY] != Color.BLACK){
                    piece.rotate();
                    piece.rotate();
                    piece.rotate();
                    this.addPiece(piece);
                    return false;
                }// if
            }// if
            else{
                piece.rotate();
                piece.rotate();
                piece.rotate();
                this.addPiece(piece);
                return false;
            }//else
        }// for
        piece.rotate();
        piece.rotate();
        piece.rotate();
        this.addPiece(piece);
        return true; 
    }// canRotate

    /**
     * Rotates a given piece on the tetris board.
     *
     * @param piece the piece to be rotated
     */
    public void rotatePiece(TetrisPiece piece){
        if(this.canRotate(piece)){
            this.removePiece(piece);
            piece.rotate();
            this.addPiece(piece);
        }// if
    }// rotate

}// TetrisBoard
