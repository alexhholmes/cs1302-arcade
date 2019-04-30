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
                board[x][rowCleared - y] = BlockColor.BLACK;
            }// for
        }// for
    }// rowclear
    
    /** shifts the blocks down based on the rows that
    * have been cleared
    * @param rowCleared the lowest row that was just cleared
    * @param rowsCleared the number of rows cleared
    */
    public void shiftDown(int rowCleared, int rowsCleared){
        this.rowClear(rowCleared, rowsCleared);
        for(int i = 0; i < 10; i++){
            board[i][rowCleared] = board[x][rowCleared - rowsCleared];
        }// for
    }// shiftDown
    
    /** adds a piece to the top of the board
    * @param piece the TetrisPiece to be added
    */
    public void addPiece(TetrisPiece piece){
        for(int i = 0; i < 4; i++){
            int tempX = piece.getBlock(i).getColumn();
            int tempY = piece.getBlock(i).getRow();
            BlockColor tempColor = piece.getBlock(i).getColor();
            if(board[tempX][tempY] == BlockColor.BLACK){
                board[tempX][tempY] = tempColor;
            }// if
        }// for
    }// addPiece
    
    /** removes a desired piece from the board
    * @param piece to be removed
    */
    public void removePiece(tetrisPiece piece){
        for(int i = 0; i < 4; i++){
            int tempX = piece.getBlock(i).getColumn();
            int tempY = piece.getBlock(i).getRow();
            board[tempX][tempY] = BlockColor.BLACK;
        }// for
    }// removePiece
    
    /** sees if a piece can fall 1 row
    * @return true if there are no blocks directly below the piece
    * and the piece is not at the bottum of the board
    */
    public boolean canDrop(TetrisPiece piece){
        for(int i = 0; i<4; i++){
            int tempX = piece.getBlock(i).getColumn();
            int tempY = piece.getBlock(i).getRow();
            boolean samePiece = false;
            for(int z = 0; z < 4; z++){
                int tempX2 = piece.getBlock(z).getColumn();
                int tempY2 = piece.getBlock(z).getRow();
                    if(tempX-1 == tempX2 && tempY-1 == tempY2){
                        samePiece == true;
                    }// if
            }// for
            if(!samePiece){
                if(board[tempX-1][tempY-1] != BlockColor.BLACK){
                    return false;
                }// if
            }// if
            if(tempY >= 19){
                return false;
            }// if
        }// for
        return true;
    }// canDrop
    
    /** lowers a piece by 1 row
    */
    public void dropPiece(TetrisPiece piece){
        if(this.canDrop(piece)){
            piece.drop();
            this.addPiece(piece);
        }// if
    }// dropPiece
}// TetrisBoard
