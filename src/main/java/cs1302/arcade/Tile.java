package cs1302.arcade;

import javafx.scene.paint.Color;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.util.Random;

/**
 * A model representing a 2048 game tile.
 */
public class Tile {

    /** Value of this tile */
    private IntegerProperty value = new SimpleIntegerProperty();
    /** X Coordinate */
    private DoubleProperty x = new SimpleDoubleProperty();
    /** Y Coordinate */
    private DoubleProperty y = new SimpleDoubleProperty();
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
     * Sets the value to the specified value and updates the color
     * of this tile.
     */
    private void setValue(int value) {
        value.setValue(value);
        setColor(value);
    } // setValue(int)

    /**
     * Returns the x property of this tile.
     *
     * @return the x property
     */
    public DoubleProperty xProperty() {
        return x;
    } // xProperty()

    /**
     * Returns the x value of this tile.
     *
     * @return the x value
     */
    public double getX() {
        return x.getValue()
    } // getX()

    /**
     * Sets the x to the specified value.
     *
     * @param x the x coordinate of this tile
     */
    public void setX(double x) {
        this.x.setValue(x);
    } // setX(double)

    /**
     * Returns the y property of this tile.
     *
     * @return the y property
     */
    public DoubleProperty yProperty() {
        return y;
    } // yProperty()

    /**
     * Returns the y value of this tile.
     *
     * @return the y value
     */
    public double getY() {
        return x.getValue()
    } // getY()

    /**
     * Sets the y to the specified value.
     *
     * @param y the y coordinate of this tile
     */
    public void setY(double y) {
        this.y.setValue(y);
    } // setY(double)

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
     * Randomly sets this tile to either 2 or 4.
     */
    public void setRandomValue() {
        Random random = new Random();
        int num = (rand.nextInt(1) + 1) * 2;
        setValue(num);
    } // setRandomValue()

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
        setValue(0);
    } // resetTile()

    /**
     * Returns true if the value of this tile matches the given tile.
     *
     * @param tile the tile to check a match with
     * @return true if this tile matches the given tile
     */
    public boolean matches(Tile tile) {
        if (getValue() == tile.getValue) {
            return true;
        } // if
        return false;
    } // matches(Tile)

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
