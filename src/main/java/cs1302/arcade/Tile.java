package cs1302.arcade;

import javafx.scene.paint.Color;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * A model representing a 2048 game tile.
 */
public class Tile {

    private IntegerProperty value = new SimpleIntegerProperty();
    
    /**
     * Constructs a new tile with the specified value.
     */
    public Tile(int value) {
        this.value.setValue(value);
    } // Tile(int)

    /**
     * Returns the value property of this tile.
     *
     * @return the value property
     */
    public IntegerProperty valueProperty() {
        return value;
    } // valueProperty()

    /**
     * Returns the int value of this tile.
     *
     * @return the int value
     */
    public int getValue() {
        return value.getValue();
    } // getValue()

    /**
     * Sums the value of this tile with a tile that it is merging with.
     *
     * @param tile the tile that this tile is merging with
     */
    public mergeTile(Tile tile) {
        value.setValue(getValue() + tile.getValue());
    } // mergeTile(Tile)

    /**
     * Resets the value of this tile to zero.
     */
    public void resetTile() {
        score.setValue(0);
    } // resetTile()

} // Tile
