package cs1302.arcade;

/** class for the tetris pieces
*/
public class TetrisPiece {
    public TetrisBlock block1, block2, block3, block4;

    /** creates a tetris piece 
    * @param num chooses which piece to make
    */
    public TetrisPiece(int num){
        switch(num){
            // creates long piece
            case 1: {
                block1 = new TetrisBlock(6, 0, BLOCKCOLOR.LIGHT_BLUE);
                block2 = new TetrisBlock(6, 1, BLOCKCOLOR.LIGHT_BLUE);
                block3 = new TetrisBlock(6, 2, BLOCKCOLOR.LIGHT_BLUE);
                block4 = new TetrisBlock(6, 3, BLOCKCOLOR.LIGHT_BLUE);
                break;
            }// case 1
            // creates square piece
            case 2: {
                block1 = new TetrisBlock(5, 0, BLOCKCOLOR.YELLOW);
                block2 = new TetrisBlock(6, 0, BLOCKCOLOR.YELLOW);
                block3 = new TetrisBlock(5, 1, BLOCKCOLOR.YELLOW);
                block4 = new TetrisBlock(6, 1, BLOCKCOLOR.YELLOW);
                break;
            }// case 2
            // creates Right L piece
            case 3: {
                block1 = new TetrisBlock(5, 0, BLOCKCOLOR.ORANGE);
                block2 = new TetrisBlock(5, 1, BLOCKCOLOR.ORANGE);
                block3 = new TetrisBlock(5, 2, BLOCKCOLOR.ORANGE);
                block4 = new TetrisBlock(6, 2, BLOCKCOLOR.ORANGE);
                break;
            }// case 3
            // creates Left L piece
            case 4: {
                block1 = new TetrisBlock(6, 0, BLOCKCOLOR.DARK_BLUE);
                block2 = new TetrisBlock(6, 1, BLOCKCOLOR.DARK_BLUE);
                block3 = new TetrisBlock(6, 2, BLOCKCOLOR.DARK_BLUE);
                block4 = new TetrisBlock(5, 2, BLOCKCOLOR.DARK_BLUE);
                break;
            }// case 4
            // creates right Z piece
            case 5: {
                block1 = new TetrisBlock(7, 0, BLOCKCOLOR.GREEN);
                block2 = new TetrisBlock(6, 0, BLOCKCOLOR.GREEN);
                block3 = new TetrisBlock(6, 1, BLOCKCOLOR.GREEN);
                block4 = new TetrisBlock(5, 1, BLOCKCOLOR.GREEN);
                break;
            }// case 5
            // creates left Z piece
            case 6: {
                block1 = new TetrisBlock(5, 0, BLOCKCOLOR.RED);
                block2 = new TetrisBlock(6, 0, BLOCKCOLOR.RED);
                block3 = new TetrisBlock(6, 1, BLOCKCOLOR.RED);
                block4 = new TetrisBlock(7, 1, BLOCKCOLOR.RED);
                break;
            }// case 6
            // creates T piece
            case 7: {
                block1 = new TetrisBlock(6, 0, BLOCKCOLOR.PURPLE);
                block2 = new TetrisBlock(5, 1, BLOCKCOLOR.PURPLE);
                block3 = new TetrisBlock(6, 1, BLOCKCOLOR.PURPLE);
                block4 = new TetrisBlock(7, 1, BLOCKCOLOR.PURPLE);
                break;
            }// case 7
            default: {
                break;
            }// default 
        }// switch
    }// TetrisPiece
    
    /** returns a desired block
    * @param the number of the desired block
    * @return the desired TetrisBlock object
    */
    public TetrisBlock getBlock(int num){
        switch(num){
            case 1: {
                return block1;
            }// case 1
            case 2: {
                return block2;
            }// case 2
            case 3: {
                return block3;
            }// case 3
            case 4: {
                return block4;
            }// case 4
        }// switch
    }// getBlock
   
   /** lowers a block's row by 1
   */ 
    public void drop(){
        for(int i = 0; i<4; i++)
            this.getBlock(i).setRow(this.getBlock(i).getRow()-1);
        }// for
    }// drop
    
    /** moves piece one column to the right
    */
    public void shiftRight(){
        for(int i = 0; i<4; i++)
            if(this.getBlock(i).getColumn() < 19){
                this.getBlock(i).setColumn(this.getBlock(i).getColumn()+1);
            }// if
        }// for
    }// shiftRight
    
    /** moves piece one column to the left
    */
    public void shiftLeft(){
        for(int i = 0; i<4; i++)
            if(this.getBlock(i).getColumn() > 0){
                this.getBlock(i).setColumn(this.getBlock(i).getColumn()-1);
            }// if
        }// for
    }// shiftLeft
}// class
