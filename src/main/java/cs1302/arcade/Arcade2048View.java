package cs1302.arcade;

import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.shape.Rectangle;

/**
 * The UI elements of a 2048 game.
 */
public class Arcade2048View {
    
    private ArcadeApp app;
    private VBox root;
    private GridPane gridPane;
    private BoardGroup board;
    private Model2048 model;
    private Controller2048 controller;
    
    /**
     * Starts a new game of 2048.
     */
    public Arcade2048View(ArcadeApp app) {
        // Instance variables
        this.app = app;
        model = new Model2048();
        controller = new Controller2048(model);
        
        // Build UI
        buildView();

        // Start game
        controller.startGame();
    } // Arcade2048View(ArcadeApp)

    /**
     * Builds the game view.
     */
    public void buildView() {
        // MenuBar
        MenuBar menuBar = new MenuBar();
        Menu gameMenu = new Menu("Game");
        Menu helpMenu = new Menu("Help");
        MenuItem resetItem = new MenuItem("Reset Game");
        MenuItem exitItem = new MenuItem("Exit Game");
        MenuItem helpItem = new MenuItem("How to Play");
        gameMenu.getItems()
            .addAll(resetItem, highScoresItem, exitItem);
        helpMenu.getItems()
            .addAll(helpItem);
        menuBar.getChildren()
            .addAll(gameMenu, helpMenu);
        // MenuItem event handlers
        EventHandler<ActionEvent> resetHandler = event -> {
            controller.reset();
        };
        resetItem.setOnAction(resetHandler);
        EventHandler<ActionEvent> exitHandler = event -> {
            app.setSelectGameScene();
        };
        exitItem.setOnAction(exitHandler);
        EventHandler<ActionEvent> helpHandler = event -> {
            openHelpWindow();
        };
        helpItem.setOnAction(helpHandler);
        // Game label
        Label gameLabel = new Label("2048");
        // Score
        VBox scoreVBox = new VBox(4);
        Label scoreTitle = new Label("Score:");
        Label scoreLabel = new Label();
        scoreLabel.textProperty().bind(model.scoreProperty());
        scoreVBox.getChildren()
            .addAll(scoreTitle, scoreLabel);
        scoreVbox.setAlignment(Pos.CENTER);
        // Board
        board = new BoardGroup(); // TODO
        // GridPane
        gridPane = new GridPane();
        gridPane.add(gameLabel, 0, 0, 1, 1);
        gridPane.add(scoreVBox, 1, 0, 1, 1);
        gridPane.add(board, 0, 1, 2, 1);
        gridPane.setHAlignment(gameLabel, HPos.Center);
        gridPane.setVAlignment(gameLabel, VPos.Center);
        gridPane.setHAlignment(scoreVBox, HPos.Center);
        gridPane.setVAlignment(scoreVBox, VPos.Center);
        GridPane.setHGrow(gameLabel, Priority.ALWAYS);
        GridPane.setHGrow(scoreVBox, Priority.ALWAYS);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        // Root
        root = new VBox();
        root.getChildren()
            .addAll(menuBar, gridPane);
    } // buildView()

    /**
     * Binds the board UI tiles to the board model tiles.
     */
    private void bindBoard() {
        Tile[][] board = model.getBoard();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                Tile tile = board[row][col];
                IntegerProperty value = tile.valueProperty();
                Color color = tile.getColor();
                bindTile(row, col, value, color);
            } // for
        } // for
    } // bindBoard()

    /**
     * Binds a tile to its corresponding board data model value and sets its
     * color.
     *
     * @param row the row of the tile
     * @param col the column of the tile
     * @param value the value of the tile piece
     * @param color the color of the tile piece
     */
    private void bindTile(int row, int col, IntegerProperty value, Color color) {
        StackPane tile = board.getTile(row, col);
        // Bind UI tile text to display the tile integer value
        tile.get(1).textProperty().bind(value);
        setTileColor(tile.getChildren(0), color);
    } // bindTile(int, int, IntegerProperty, Color)

    /**
     * Sets the CSS for the tile to the specified color. A color of black indicates
     * that the board space is empty.
     *
     * @param tile a rectangle representing a game tile
     * @param color the color of the tile piece
     */
    private void setTileColor(Rectangle tile, Color color) {
        // TODO
        switch (color) {
            case BLACK:
                tile.applyCSS(); // TODO
                break;
        }
    } // setTileColor(Rectangle, Color)

    private void tileColorListener() {
        
    } // tileColorListener

    /**
     * Returns the root node of this game as a {@code Parent}.
     *
     * @return the root node of this game
     */
    public Parent asParent() {
        return root;
    } // asParent()

} // Arcade2048View
