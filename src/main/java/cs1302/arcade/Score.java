package cs1302.arcade;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;

/**
 * A model for a game's score.
 */
public class Score {

    /** Score Property */
    private IntegerProperty score = new SimpleIntegerProperty();

    /**
     * Initializes score property.
     */
    public Score() {
        resetScore();
    } // Score

    /**
     * Returns this model's score property.
     *
     * @return this model's score property.
     */
    public ReadOnlyIntegerProperty scoreProperty() {
        return this.score;
    } // scoreProperty()

    /**
     * Adds the given value to the current score.
     *
     * @param num the amount added to the score
     */
    public void addScore(int num) {
        this.score.setValue(score.getValue() + num);
    } // addScore(int)

    /**
     * Resets the score value to zero.
     */
    public void resetScore() {
        this.score.setValue(0);
    } // resetScore()

} // Score
