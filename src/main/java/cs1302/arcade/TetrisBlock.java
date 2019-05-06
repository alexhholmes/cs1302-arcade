package cs1302.arcade;

import javafx.scene.paint.Color;

/** 
* class for the individual tetris blocks
*/
public class TetrisBlock {

    public int xValue;
    public int yValue;
    public Color color;
    
    /**
    * creates a tetris block
    * @param x column the block is in
    * @param y row the block is in
    */
    public TetrisBlock(int x, int y, Color c){
        xValue = x;
        yValue = y;
        color = c;
    }// TetrisBlock
    
    /** 
    * fetches the color of the given block
    * @return color of the given block
    */
    public Color getColor(){
        return this.color;
    }// getColor
    
    /** returns the current column of the block
    * @return the x coordinates of the block
    */
    public int getColumn(){
        return this.xValue;
    }// getColumn
    
    /** returns the current row of the block
    * @return the y coordinates of the block
    */
    public int getRow(){
        return this.yValue;
    }// getrow
    
    /** sets the row of a block
    * @param row the new row for the block
    */
    public void setRow(int row){
        this.yValue = row;
    }// setRow
    
    /** sets the colum of a block
    * @param column the new column for the block
    */
    public void setColumn(int column){
        this.xValue = column;
    }// setRow
}// TetrisBlock
