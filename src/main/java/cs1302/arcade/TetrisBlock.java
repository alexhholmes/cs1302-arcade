package cs1302.arcade;

public Interface TetrisBlock{
    public enum Color {
        /** SquareBlock */
        YELLOW,
        /** RightLBlock */
        ORANGE,
        /** LeftLBlock */
        DARK_BLUE,
        /** LineBlock */
        LIGHT_BLUE,
        /** RightZBlock */
        GREEN,
        /** LEFTZBlock */ 
        RED,
        /** TBlock */
        PURPLE,
        /** Background */
        BLACK
    }// Color
    /** 
    * fetches the color of the given block
    * @return color of the given block
    */
    public Color getColor();
}// TetrisBlock
