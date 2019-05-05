package cs1302.arcade;

public class TileView extends StackPane {

    private static final double TILE_HEIGHT = 100;
    private static final double TILE_WIDTH = 100;

    private Rectangle agent = new Rectangle();
    private Text text = new Text();

    public TilePane() {
        agent.setHeight(TILE_HEIGHT);
        agent.setWidth(TILE_WIDTH);
        agent.setArcHeight(8);
        agent.setArcWidth(8);
    } // TilePane()

    public StringProperty textProperty() {
        return text.textProperty();
    } // textProperty()

    public DoubleProperty xProperty() {
        return agent.xProperty();
    } // xProperty()

    public DoubleProperty yProperty() {
        return agent.yProperty();
    } // xProperty()

    public void setFill(Color color) {
        agent.setFill(color);
    } // setFill(Color)

} // TilePane
