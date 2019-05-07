package cs1302.arcade;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The UI elements of a 2048 game.
 */
public class Arcade2048View {

    private ArcadeApp app;
    private GridPane root;
    private Label gameLabel;
    private VBox scoreVBox;
    private HBox buttonBox;
    private StackPane boardBackground;
    private Group board;
    private Text text;
    private StackPane gameOverPane;
    private Text gameOverText;

    private Score score;
    private Model2048 boardModel;
    private Controller2048 controller;

    private AtomicBoolean keyDisable = new AtomicBoolean(false);
    
    /**
     * Starts a new game of 2048.
     */
    public Arcade2048View(ArcadeApp app) {
        // Instance variables
        this.app = app;
        score = new Score();
        boardModel = new Model2048();
        controller = new Controller2048(boardModel, score, this);
        
        // Build UI
        buildView();
    } // Arcade2048View(ArcadeApp)

    /**
     * For the main application to set this game's controls and focus
     * after the scene has been initialized.
     */
    public void setControls() {
        board.setOnKeyPressed(createKeyHandler());
        board.requestFocus();
    } // setControls()

    /**
     * Builds the game view.
     */
    public void buildView() {
        buildGameLabel();
        buildScore();
        buildButtons();
        buildBoard();
        buildHelpText();
        buildGameOver();
        // GridPane
        root = new GridPane();
        root.add(gameLabel, 0, 0, 1, 1);
        root.add(scoreVBox, 1, 0, 1, 1);
        root.add(buttonBox, 1, 1, 1, 1);
        root.add(boardBackground, 0, 2, 2, 1);
        root.add(text, 0, 3, 1, 1);
        GridPane.setValignment(gameLabel, VPos.TOP);
        GridPane.setHalignment(gameLabel, HPos.LEFT);
        GridPane.setValignment(scoreVBox, VPos.TOP);
        GridPane.setHalignment(scoreVBox, HPos.RIGHT);
        GridPane.setValignment(buttonBox, VPos.CENTER);
        GridPane.setHalignment(buttonBox, HPos.RIGHT);
        GridPane.setHgrow(scoreVBox, Priority.NEVER);
        GridPane.setVgrow(scoreVBox, Priority.NEVER);
        root.setPadding(new Insets(8));
        root.setHgap(8);
        root.setVgap(8);
    } // buildView()

    /**
     * If set to true, keys are disabled.
     *
     * @param keyDisable if true, disables keys
     */
    public void setKeyDisable(boolean keyDisable) {
        this.keyDisable.set(keyDisable);
    } // setKeyDisable(boolean)

    /**
     * Builds the 2048 game label.
     */
    private void buildGameLabel() {
        gameLabel = new Label("2048");
        gameLabel.getStyleClass().add("game-label");
    } // buildGameLabel()

    /**
     * Builds the score display.
     */
    private void buildScore() {
        scoreVBox = new VBox(4);
        Label scoreTitle = new Label("Score:");
        Label scoreLabel = new Label();
        scoreTitle.getStyleClass().add("score-label");
        scoreLabel.getStyleClass().add("score-label");
        scoreLabel.textProperty().bind(score.scoreProperty().asString());
        scoreVBox.getChildren()
            .addAll(scoreTitle, scoreLabel);
        scoreVBox.setAlignment(Pos.CENTER);
        scoreVBox.setMinWidth(200);
        scoreVBox.getStyleClass().add("score-box");
    } // buildScore()

    /**
     * Builds the buttons.
     */
    private void buildButtons() {
        Button restartButton = new Button("Restart") {
            public void requestFocus() {}
        };
        Button quitButton = new Button("Quit") {
            public void requestFocus() {}
        };
        restartButton.getStyleClass().add("button");
        quitButton.getStyleClass().add("button");
        restartButton.setOnAction(restartHandler());
        quitButton.setOnAction(quitHandler());
        buttonBox = new HBox();
        buttonBox.getChildren().addAll(restartButton, quitButton);
        buttonBox.setSpacing(8);
    } // buildButtons()

    /**
     * Builds the game board.
     */
    private void buildBoard() {
        boardBackground = new StackPane();
        boardBackground.getStyleClass().add("board");
        boardBackground.setPadding(new Insets(20));
        board = new Group();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                // Blank tiles
                Rectangle blankTile = new Rectangle(100, 100);
                blankTile.setX(col * 100 + 20 * col);
                blankTile.setY(row * 100 + 20 * row);
                blankTile.getStyleClass().add("empty-tile");
                // Game tiles
                Tile tile = boardModel.getTile(row, col);
                tile.setTranslateX(col * 100 + 20 * col);
                tile.setTranslateY(row * 100 + 20 * row);
                board.getChildren().addAll(blankTile, tile);
            }
        }
        boardBackground.getChildren().add(board);
        boardBackground.setAlignment(Pos.CENTER);
    } // buildBoard()

    /**
     * Builds the help text.
     */
    private void buildHelpText() {
        String helpText = "How to Play:\n" +
                "Use the arrow keys to move the tiles.\n" +
                "Tiles will merge together if they are equal\nnumbers. " +
                "Try to get the highest score\nyou can!";
        text = new Text(helpText);
    } // buildHelpText()

    /**
     * Displays a game over screen when the game is complete.
     */
    private void buildGameOver() {
        Rectangle rectangle = new Rectangle(460, 460);
        rectangle.setArcHeight(8);
        rectangle.setArcWidth(8);
        rectangle.setFill(Color.GRAY);
        rectangle.setOpacity(.2);
        gameOverText = new Text();
        gameOverText.setTextAlignment(TextAlignment.CENTER);
        gameOverText.setStyle("-fx-font-family: Helvetica");
        gameOverText.setStyle("-fx-font-weight: Bold");
        gameOverText.setStyle("-fx-font-size: 36");
        gameOverPane = new StackPane();
        gameOverPane.getChildren().addAll(rectangle, gameOverText);
        gameOverPane.setVisible(false);
        board.getChildren().add(gameOverPane);
    } // buildGameOver()

    /**
     * Returns an event handler to restart the game.
     *
     * @return an event handler to restart the game
     */
    private EventHandler<ActionEvent> quitHandler() {
        EventHandler<ActionEvent> handler = event -> {
            app.setSelectGameScene();
        };
        return handler;
    } // quitHandler()

    /**
     * Returns an event handler to restart the game.
     *
     * @return an event handler to restart the game
     */
    private EventHandler<ActionEvent> restartHandler() {
        EventHandler<ActionEvent> handler = event -> {
            controller.reset();
            keyDisable.set(false);
            gameOverPane.setVisible(false);
        };
        return handler;
    } // quitHandler()

    /**
     * Return a key event handler that makes a player move that
     * will shift the tiles on the board.
     *
     * @return the key event handler
     */
    private EventHandler<? super KeyEvent> createKeyHandler() {
        return event -> {
            System.out.println(event);
            // Check if game is over
            if (controller.isGameOver()) {
                gameOverText.setText("GAME OVER!\nYour Score: " + score.getScore());
                gameOverPane.toFront();
                gameOverPane.setVisible(true);
            } // if
            if (!keyDisable.get()) {
                keyDisable.set(true);
                switch (event.getCode()) {
                    case UP:
                        controller.makeMove(Direction.UP);
                        break;
                    case DOWN:
                        controller.makeMove(Direction.DOWN);
                        break;
                    case LEFT:
                        controller.makeMove(Direction.LEFT);
                        break;
                    case RIGHT:
                        controller.makeMove(Direction.RIGHT);
                        break;
                } // switch
            } // if
            board.requestFocus();
        };
    } // createKeyHandler()

    /**
     * Returns the root node of this game as a {@code Parent}.
     *
     * @return the root node of this game
     */
    public Parent asParent() {
        return root;
    } // asParent()

} // Arcade2048View
