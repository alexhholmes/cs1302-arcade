package cs1302.arcade;

import javafx.scene.paint.Color;

/** 
* class for the tetris pieces
*/
public class TetrisPiece{
    public TetrisBlock block1, block2, block3, block4;

    /** 
    * creates a tetris piece 
    * @param num chooses which piece to make
    */
    public TetrisPiece(int num){
        switch(num){
            case 1: {
                this.makeLong();
                break;
            }// case 1
            case 2: {
                this.makeSquare();
                break;
            }// case 2
            case 3: {
                this.makeRightL();
                break;
            }// case 3
            case 4: {
                this.makeLeftL();
                break;
            }// case 4
            case 5: {
                this.makeRightZ();
                break;
            }// case 5
            case 6: {
                this.makeLeftZ();
                break;
            }// case 6
            case 7: {
                this.makeT();
                break;
            }// case 7
            default: {
                break;
            }// default 
        }// switch
    }// TetrisPiece
    
    /** 
    * creates the long piece
    */
    public void makeLong(){
        block1 = new TetrisBlock(6, 0, Color.LIGHTSKYBLUE);
        block2 = new TetrisBlock(6, 1, Color.LIGHTSKYBLUE);
        block3 = new TetrisBlock(6, 2, Color.LIGHTSKYBLUE);
        block4 = new TetrisBlock(6, 3, Color.LIGHTSKYBLUE);
    }// makeLong
    
    /** 
    * creates the square piece
    */
    public void makeSquare(){
        block1 = new TetrisBlock(5, 0, Color.YELLOW);
        block2 = new TetrisBlock(6, 0, Color.YELLOW);
        block3 = new TetrisBlock(5, 1, Color.YELLOW);
        block4 = new TetrisBlock(6, 1, Color.YELLOW);
    }// makeSquare
    
    /** 
    * creates the right L piece
    */
    public void makeRightL(){
        block1 = new TetrisBlock(5, 0, Color.ORANGE);
        block2 = new TetrisBlock(5, 1, Color.ORANGE);
        block3 = new TetrisBlock(5, 2, Color.ORANGE);
        block4 = new TetrisBlock(6, 2, Color.ORANGE);
    }// makeRightL
    
    /** 
    * creates the left L piece
    */
    public void makeLeftL(){
        block1 = new TetrisBlock(6, 0, Color.DARKBLUE);
        block2 = new TetrisBlock(6, 1, Color.DARKBLUE);
        block3 = new TetrisBlock(6, 2, Color.DARKBLUE);
        block4 = new TetrisBlock(5, 2, Color.DARKBLUE);
    }// makeLeftL
    
    /** 
    * creates the right Z piece
    */
    public void makeRightZ(){
        block1 = new TetrisBlock(7, 0, Color.GREEN);
        block2 = new TetrisBlock(6, 0, Color.GREEN);
        block3 = new TetrisBlock(6, 1, Color.GREEN);
        block4 = new TetrisBlock(5, 1, Color.GREEN);
    }// makeRightZ
    
    /** 
    * creates the left Z piece
    */
    public void makeLeftZ(){
        block1 = new TetrisBlock(5, 0, Color.RED);
        block2 = new TetrisBlock(6, 0, Color.RED);
        block3 = new TetrisBlock(6, 1, Color.RED);
        block4 = new TetrisBlock(7, 1, Color.RED);
    }// makeLeftZ
    
    /** 
    * creates the T piece
    */
    public void makeT(){
        block1 = new TetrisBlock(6, 0, Color.PURPLE);
        block2 = new TetrisBlock(5, 1, Color.PURPLE);
        block3 = new TetrisBlock(6, 1, Color.PURPLE);
        block4 = new TetrisBlock(7, 1, Color.PURPLE);
    }// makeTPiece
    
    /** 
    * returns a desired block
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
            default: {
                return block1;
            }// default
        }// switch
    }// getBlock
   
   /** 
   * lowers a block's row by 1
   */ 
    public void drop(){
        for(int i = 0; i<4; i++){
            this.getBlock(i).setRow(this.getBlock(i).getRow()-1);
        }// for
    }// drop
    
    /** 
    * moves piece one column to the right
    */
    public void shiftRight(){
        for(int i = 0; i<4; i++){
            if(this.getBlock(i).getColumn() < 19){
                this.getBlock(i).setColumn(this.getBlock(i).getColumn()+1);
            }// if
        }// for
    }// shiftRight
    
    /** 
    * moves piece one column to the left
    */
    public void shiftLeft(){
        for(int i = 0; i<4; i++){
            if(this.getBlock(i).getColumn() > 0){
                this.getBlock(i).setColumn(this.getBlock(i).getColumn()-1);
            }// if
        }// for
    }// shiftLeft
    
    /** 
    * rotates a given piece
    */
    public void rotate(){
        TetrisBlock pivot = this.getBlock(3);
        for(int i = 1; i <= 4; i++){
            if(i!=3){
                TetrisBlock tempB = this.getBlock(i);
                int newX = tempB.getColumn() - pivot.getColumn();
                int newY = tempB.getRow()- pivot.getRow();
                this.getBlock(i).setColumn((0*newX) + (-1*newY) + pivot.getColumn());
                this.getBlock(i).setRow((1*newX) + (0*newY) + pivot.getRow());
            }// if
        }// for
    }// rotate
}// class
