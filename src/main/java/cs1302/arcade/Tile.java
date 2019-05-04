package cs1302.arcade;

import javafx.scene.paint.Color;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * A model representing a 2048 game tile.
 */
public class Tile {

    /** Value of this tile */
    private IntegerProperty value = new SimpleIntegerProperty();
    /** Color of this tile */
    private Color color;

    /**
     * Constructs a new tile with a value of zero.
     */
    public Tile() {
        resetTile();
    } // Tile()
    
    /**
     * Constructs a new tile with the specified value.
     */
    public Tile(int value) {
        this.value.setValue(value);
        setColor(value);
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
     * @return the new value of this tile
     */
    public int mergeTile(Tile tile) {
        value.setValue(getValue() + tile.getValue());
        return getValue();
    } // mergeTile(Tile)

    /**
     * Returns the color of this tile.
     *
     * @return the color of this tile
     */
    public Color getColor() {
        return color;
    } // getColor()

    /**
     * Resets the value of this tile to zero.
     */
    public void resetTile() {
        score.setValue(0);
        setColor(0);
    } // resetTile()

    /**
     * Return true if this tile's value is zero.
     *
     * @return true of this tile's value is zero.
     */
    public boolean isEmpty() {
        return getValue() == 0 ? true : false;
    } // isEmpty()

    /**
     * Sets the color of this tile based on the paramater value.
     *
     * @param value the value of this tile
     */
    private void setColor(int value) {
        // TODO fix colors
        switch (value) {
            case 0:
                color = Color.web("#CCC0B3");
            case 2:
                color = Color.web("#EEE4DA");
                break;
            case 4:
                color = Color.web("#EEE4DA");
                break;
            case 8:
                color = Color.web("#F2B179");
                break;
            case 16:
                color = Color.web("#F2B179");
                break;
            case 32:
                color = Color.web("#F2B179");
                break;
            case 64:
                color = Color.web("#F2B179");
                break;
            case 128:
                color = Color.web("#EDCF72");
                break;
            case 256:
                color = Color.web("#EDCF72");
                break;
            case 512:
                color = Color.web("#EDCF72");
                break;
            case 1024:
                color = Color.web("#EDCF72");
                break;
            case 2048:
                color = Color.web("#EDCF72");
                break;
            case 4096:
                color = Color.web("#3E3933");
                break;
        } // switch
    } // setColor(int)

} // Tile
