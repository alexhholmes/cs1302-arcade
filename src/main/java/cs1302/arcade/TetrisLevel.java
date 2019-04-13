import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * A model for the Tetris game level.
 */
public class TetrisLevel {

    /** Level Property */
    private IntegerProperty level;

    /**
     * Initializes level property
     */
    public TetrisLevel() {
        score = new SimpleIntegerProperty();
    } // TetrisLevel()

    /**
     * Returns this model's level property.
     *
     * @return this model's level property
     */
    public IntegerProperty levelProperty() {
        return this.level;
    } // levelProperty()

    /**
     * Sets the level.
     *
     * @param newLevel the new level value
     */
    public void setLevel(int newLevel) {
        this.level.setValue(newLevel);
    } // setLevel(int)

    /**
     * Returns the current level.
     *
     * @return the current level.
     */
    public int getLevel() {
        return this.level.getValue();
    } // getLevel()

} // TetrisLevel
