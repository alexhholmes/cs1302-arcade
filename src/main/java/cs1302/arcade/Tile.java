package cs1302.arcade;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Random;

/**
 * A model representing a 2048 game tile.
 */
public class Tile extends StackPane {

    /** Tile Height */
    private static final double TILE_HEIGHT = 100;
    /** Tile Width */
    private static final double TILE_WIDTH = 100;

    /** Tile Body */
    private Rectangle agent = new Rectangle();
    /** Tile Text */
    private Text text = new Text();

    /** The value of this tile */
    private int value;
    /** The future value of this tile */
    private int futureValue;

    /**
     * Constructs a new tile with a value of zero.
     */
    public Tile() {
        agent.setHeight(TILE_HEIGHT);
        agent.setWidth(TILE_WIDTH);
        agent.setArcHeight(8);
        agent.setArcWidth(8);
        text.getStyleClass().add("tile-text");
        this.getChildren().addAll(agent, text);
        resetTile();
    } // Tile()

    /**
     * Constructs a new tile with the specified value.
     */
    public Tile(int value) {
        this();
        setValue(value);
    } // Tile(int)

    /**
     * Returns the int value of this tile.
     *
     * @return the int value
     */
    public int getValue() {
        return value;
    } // getValue()

    /**
     * Sets the value to the specified value and updates the color
     * of this tile.
     */
    private void setValue(int value) {
        this.value = value;
        futureValue = value;
        text.setText(Integer.toString(value));
        updateColor();
    } // setValue(int)

    /**
     * Returns the future value of this tile.
     *
     * @return the future value of this tile
     */
    public int getFutureValue() {
        return futureValue;
    } // getFutureValue()

    /**
     * Sets the future value of this tile to the specified value.
     *
     * @param value the future value of this tile
     */
    public void setFutureValue(int value) {
        futureValue = value;
    } // setFutureValue(int)

    /**
     * Sums the value of this tile with a tile that it is merging with.
     *
     * @param tile the tile that this tile is merging with
     * @return the new value of this tile
     */
    public int mergeTile(Tile tile) {
        setValue(getValue() + tile.getValue());
        tile.resetTile();
        return getValue();
    } // mergeTile(Tile)

    /**
     * Randomly sets this tile to either 2 or 4 with a 90% chance of
     * it being a value of 2.
     */
    public void setRandomValue() {
        Random random = new Random();
        double num = random.nextDouble();
        if (num < 0.9) {
            setValue(2);
        } else {
            setValue(4);
        } // if
    } // setRandomValue()

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
        if (getValue() == tile.getValue()) {
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
     * Updates the color of this tile based on this tile's value.
     */
    private void updateColor() {
        if (getValue() != 0) {
            this.setVisible(true);
        } // if
        switch (getValue()) {
            case 0:
                this.setVisible(false);
                break;
            case 2:
                agent.setFill(Color.web("#EEE4DA"));
                break;
            case 4:
                agent.setFill(Color.web("#EDE0C8"));
                break;
            case 8:
                agent.setFill(Color.web("#F2b179"));
                break;
            case 16:
                agent.setFill(Color.web("#F59563"));
                break;
            case 32:
                agent.setFill(Color.web("#F67C5F"));
                break;
            case 64:
                agent.setFill(Color.web("#F65E3B"));
                break;
            case 128:
                agent.setFill(Color.web("#EDCF72"));
                break;
            case 256:
                agent.setFill(Color.web("#EDCC61"));
                break;
            case 512:
                agent.setFill(Color.web("#EDC850"));
                break;
            case 1024:
                agent.setFill(Color.web("#EDC53F"));
                break;
            case 2048:
                agent.setFill(Color.web("#EDC22E"));
                break;
            default:
                agent.setFill(Color.web("#3C3A32"));
                break;
        } // switch
    } // setColor(int)

} // Tile
