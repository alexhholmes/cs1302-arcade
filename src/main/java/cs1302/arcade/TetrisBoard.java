package cs1302.arcade;

public class TetrisBoard {
    Color[][] board = new Color[10][20];
    
    /**
    * resets the board to be completely blank
    */
    public void resetBoard(){
        for(int x = 0; x < 10; x ++){
            for(int y = 0; y < 20; y++){
                board[x][y] = Color.BLACK;
            }// for
        }// for
    }// resetBoard
    
    /**
    * Sets a specific block to a desired color
    * @param color desired color
    * @param x column of desired color
    * @param y row of desired color
    */
    public void setBlock(Color color, int x, int y){
        board[x][y] = color;
    }// setblock
    
    /**
    * clears a desired row of blocks
    * @param y desired row
    */
    public void rowClear(int rowCleared, int rowsCleared){
        for(int y = 0; y < rowsCleared; y++){
            for(int x = 0; x < 10; x++){
                board[x][rowCleared + y] = Color.BLACK;
            }// for
        }// for
    }// rowclear
    
    public void shiftDown(int rowCleared, int rowsCleared){
        for(int y = rowCleared; y < 19; y+=rowsCleared){
            for(int x = 0; x < 10; x++){
                board[x][y] = board[x][y+rowsCleared];
            }// for
            board.rowClear(y+rowsCleared, rowsCleared);
        }// for
    }// shiftDown
}// TetrisBoard
