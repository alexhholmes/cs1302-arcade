package cs1302.arcade;

import javafx.scene.paint.Color;

/** 
 * Class for the individual tetris blocks.
 */
public class TetrisBlock {

    /** The column of the block */
    public int xValue;
    /** The row of the block */
    public int yValue;
    /** The color of the block */
    public Color color;

    /**
     * Creates a tetris block.
     *
     * @param x column the block is in
     * @param y row the block is in
     * @param c the block color
     */
    public TetrisBlock(int x, int y, Color c){
        xValue = x;
        yValue = y;
        color = c;
    }// TetrisBlock

    /** 
     * Fetches the color of the given block.
     *
     * @return color of the given block
     */
    public Color getColor(){
        return this.color;
    }// getColor

    /** 
     * Returns the current column of the block.
     *
     * @return the x coordinates of the block
     */
    public int getColumn(){
        return this.xValue;
    }// getColumn

    /** 
     * Returns the current row of the block.
     *
     * @return the y coordinates of the block
     */
    public int getRow(){
        return this.yValue;
    }// getrow

    /**
     * Sets the row of a block.
     *
     * @param row the new row for the block
     */
    public void setRow(int row){
        this.yValue = row;
    }// setRow

    /**
     * Sets the colum of a block.
     * @param column the new column for the block
     */
    public void setColumn(int column){
        this.xValue = column;
    }// setRow

}// TetrisBlock
