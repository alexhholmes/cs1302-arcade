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

    private static final double TILE_HEIGHT = 100;
    private static final double TILE_WIDTH = 100;
    
    private ArcadeApp app;
    private VBox root;
    private GridPane gridPane;
    private MenuBar menuBar;
    private VBox scoreVBox;
    private Group board;

    private Score score;
    private Model2048 boardModel;
    private Controller2048 controller;

    private boolean keyDisable = false;
    
    /**
     * Starts a new game of 2048.
     */
    public Arcade2048View(ArcadeApp app) {
        // Instance variables
        this.app = app;
        score = new Score();
        boardModel = new Model2048();
        controller = new Controller2048(boardModel, score);
        
        // Build UI
        buildView();
        // Game controls
        board.setKeyPressed(createKeyHandler());
        board.requestFocus();
    } // Arcade2048View(ArcadeApp)

    /**
     * Builds the game view.
     */
    public void buildView() {
        // Components
        buildMenuBar();
        buildGameLabel();
        buildScore();
        buildBoard();
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
     * Builds the menu bar.
     */
    private void buildMenuBar() {
        menuBar = new MenuBar();
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
        EventHandler<ActionEvent> exitHandler = event -> {
            app.setSelectGameScene();
        };
        EventHandler<ActionEvent> helpHandler = event -> {
            displayHelpWindow();
        };
        resetItem.setOnAction(resetHandler);
        exitItem.setOnAction(exitHandler);
        helpItem.setOnAction(helpHandler);
    } // buildMenuBar()

    /**
     * Builds the 2048 game label.
     */
    private void buildGameLabel() {
        Label gameLabel = new Label("2048");
    } // buildGameLabel()

    /**
     * Builds the score display.
     */
    private void buildScore() {
        scoreVBox = new VBox(4);
        Label scoreTitle = new Label("Score:");
        Label scoreLabel = new Label();
        scoreLabel.textProperty().bind(model.scoreProperty());
        scoreVBox.getChildren()
            .addAll(scoreTitle, scoreLabel);
        scoreVbox.setAlignment(Pos.CENTER);
    } // buildScore()

    /**
     * Builds the game board.
     */
    private void buildBoard() {
        board = new Group();
        board.minHeight(450);
        board.minWidth(450);
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                Tile tile = board.getTile(row, col);
                TileView tileView = new TileView();
                tileView.xProperty().bind(tile.xProperty);
                tileView.yProperty().bind(tile.yProperty);
                tileView.textProperty().bind(tile.valueProperty().asString());
                tile.valueProperty().addListener((obs, oldValue, newValue) ->
                    (tileView.setFill(tile.getColor()));
                board.getChildren.add(tile);
            } // for
        } // for
    } // buildBoard()

    private EventHandler<? super KeyEvent> createKeyHandler() {
        return event -> {
            System.out.println(event.getCode());
            if (!keyDisable) {
                keyDisable = true;
                } // switch
                Thread t = new Thread(() -> {
                    switch (event.getCode()) {
                        case KeyCode.UP:
                            controller.makeMove(Direction.UP);
                            break;
                        case KeyCode.DOWN:
                            controller.makeMove(Direction.DOWN);
                            break;
                        case KeyCode.LEFT:
                            controller.makeMove(Direction.LEFT);
                            break;
                        case KeyCode.RIGHT:
                            controller.makeMove(Direction.RIGHT);
                            break;
                    makeMove(direction);
                });
                thread.setDaemon(true);
                thread.start();
                if (controller.isGameOver()) {
                    displayGameOver();
                } else {
                    keyDisable = false;
                } // if
            } // if
        };
    } // createKeyHandler()

    /**
     * Displays a game over screen when the game is complete.
     */
    private void displayGameOver() {
        
    } // displayGameOver()

    /**
     * Displays the rules of the game and its controls.
     */
    private void displayHelpWindow() {

    } // displayHelpWindow()

    /**
     * Returns the root node of this game as a {@code Parent}.
     *
     * @return the root node of this game
     */
    public Parent asParent() {
        return root;
    } // asParent()

} // Arcade2048View
