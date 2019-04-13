import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * A model for the Tetris game score.
 */
public class TetrisScore {

    /** Score Property */
    private IntegerProperty score;

    /**
     * Initializes score property.
     */
    public TetrisScore() {
        score = new SimpleIntegerProperty();
    } // TetrisScore()

    /**
     * Returns this model's score property.
     *
     * @return this model's score property.
     */
    public IntegerProperty scoreProperty() {
        return this.score;
    } // scoreProperty()

    /**
     * Adds the specified parameter to the current score.
     *
     * @param num the amount added to the score
     */
    public void addScore(int num) {
        this.score.setValue(score.getValue() + num);
    } // addScore(int)

    /**
     * Sets the score to zero.
     */
    public void resetScore() {
        this.score.setValue(0);
    } // resetScore()

} // TetrisScore
