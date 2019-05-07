package cs1302.arcade;

import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.MenuBar;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.application.Platform;
import javafx.scene.input.KeyEvent;
import javafx.scene.Group;
import javafx.geometry.Insets;

/** 
 * Controls what the user sees while playing tetris.
 */
public class ArcadeTetrisView {

    /** game app */
    private ArcadeApp app;
    /** score, level, and help display */
    private Label score, level, help;
    /** the primary display unit */
    private HBox mainPane;
    /** secondary displays */
    private VBox gameSide, statSide;
    /** the board for the game and next piece display */
    private GridPane gamePane, nextPane;
    /** quit and restart buttons */
    private Button quit, restart; 
    /** pop-up for losing */
    private Alert warning = new Alert(AlertType.ERROR);
    /** execute the game flow */
    ControllerTetris game;

    /**
     * Constructs a new Tetris game.
     *
     * @param app the application this game runs within
     */
    public ArcadeTetrisView(ArcadeApp app) {
        this.app = app;
        mainPane = new HBox(); 
        buildMainPane();
        mainPane.setPadding(new Insets(10));
        mainPane.setSpacing(10);
        mainPane.setStyle("-fx-background-color: blueviolet");
        game = new ControllerTetris(this);
    } // ArcadeTetrisView(ArcadeApp)

    /**
     * Builds the attributes and components of the main pane for this Tetris
     * game view.
     */
    private void buildMainPane() {
        score = new Label("Score: 0");
        level = new Label("Level: 1");
        String tempHelp = "Use the left, right,\nand down keys to\nmove the tetriminos.\n";
        String tempHelp2 = "Use the up key to\nrotate the tetriminos.";
        String tempHelp3 = "Every ten rows you clear,\n the level and\nfallspeed increase!";
        help = new Label("How to play: \n" + tempHelp + tempHelp2);
        nextPane = new GridPane();
        nextPane.setStyle("-fx-grid-lines-visible: true; -fx-grid-lines-color: white");
        gamePane = new GridPane();
        gamePane.setStyle("-fx-grid-lines-visible: true; -fx-grid-lines-color: white");
        restart = new Button("Restart"){
            public void requestFocus() {}
        };
        quit = new Button("Quit"){
            public void requestFocus() {}
        };
        quitGame(quit);
        resetGame(restart);
        gameSide = new VBox(gamePane);
        gameSide.setStyle("-fx-background-color: purple");
        statSide = new VBox(score, level, nextPane, restart, quit, help);
        statSide.setStyle("-fx-background-color: purple");

        mainPane.getChildren()
            .addAll(gameSide, statSide);
    } // buildMainPane

    /** 
     * Displays a pop-up when the player loses
     * score the score the user earned.
     *
     * @param score the game score
     */
    public void lose(Score score){
        warning.setTitle("FOOL!");
        warning.setHeaderText(null);
        warning.setContentText("Game Over! Your score was: " + score.getScore() + 
                ". To play again, close this and hit reset!");
        Platform.runLater(() -> {warning.showAndWait();});
    }// lose

    /** 
     * Builds the tetris board.
     *
     * @param board the board object to be displayed
     */
    public void buildBoard(TetrisBoard board){
        gamePane.getChildren().clear(); 
        for(int x = 0; x<10; x++){
            for(int y = 0; y< 20; y++){
                Rectangle tempRec = new Rectangle(25.0, 25.0, board.getValue(x,y));
                int tempX = this.getVal(x);
                int tempY = this.getVal(y);
                gamePane.add(tempRec, tempX, tempY);
            }// for
        }// for
    }// buildBoard

    /** 
     * Boots user to game select when clicking quit button.
     * 
     * @param button the user has to click to get booted to the game select
     */
    private void quitGame(Button button){
        EventHandler<ActionEvent> handler = event -> {
            app.setSelectGameScene();
        };
        button.setOnAction(handler);
    }// quitGame

    /** 
     * Restarts the tetris game
     *
     * @param button the button that needs to be pressed to restart the game
     */
    private void resetGame(Button button){
        EventHandler<ActionEvent> handler = event -> {
            game.reset();
        };
        button.setOnAction(handler);
    }// resetGame

    /** 
     * Builds the grid pane to display the next block
     *
     * @param piece the piece being displayed
     */
    public void buildNext(TetrisPiece piece){
        nextPane.getChildren().clear(); 
        for(int x = 0; x < 4; x++){
            for(int y = 0; y < 4; y++){
                Rectangle tempRec = new Rectangle(25.0, 25.0, Color.BLACK);
                int tempX = this.getVal(x);
                int tempY = this.getVal(y);
                nextPane.add(tempRec, tempX, tempY);
            }// for
        }// for
        for(int i = 1; i<=4; i++){
            int tempX = piece.getBlock(i).getColumn()-4;
            int tempY = piece.getBlock(i).getRow();
            Rectangle tempRec = new Rectangle(25.0, 25.0, piece.getBlock(i).getColor());
            Platform.runLater(() -> {
                nextPane.add(tempRec, tempX, tempY);
            });// runLater
        }// for
    }// buildNext

    /**
     * Updates the score display.
     *
     * @param points the new score to display
     */
    public void updateScore(int points){
        String scoreText = "Score: " + Integer.toString(points);
        score.setText(scoreText);
    }// updateScore

    /**
     * Updates the level display.
     *
     * @param num the new level to display
     */
    public void updateLevel(int num){
        String levelText = "Level: " + Integer.toString(num);
        level.setText(levelText);
    }// updateLevel

    /** 
     * Helper method for build board that simply returns 
     * a given value to make java think the int is final.
     *
     * @param i the value to be returned
     * @return the given value
     */
    private int getVal(int i){
        int temp = i;
        return temp;
    }// getVal

    /**
     * Return the parent node of this view.
     *
     * @return parent node of this view.
     */
    public Parent asParent() {
        return mainPane;
    } // asParent()

    /**
     * Return a key event handler that makes a player move that
     * will move the tetrimino.
     *
     * @return the key event handler
     */
    private EventHandler<? super KeyEvent> createKeyHandler() {
        return event -> {
            switch (event.getCode()) {
                case UP:
                    game.rotate();
                    break;
                case DOWN:
                    game.moveDown();
                    break;
                case LEFT:
                    game.moveLeft();
                    break;
                case RIGHT:
                    game.moveRight();
                    break;
            } // switch
            gamePane.requestFocus();
        };
    } // createKeyHandler()

    /**
     * Sets the game's controls and focuses after 
     * the game has been initialized
     */
    public void setControls(){
        gamePane.setOnKeyPressed(createKeyHandler());
        gamePane.requestFocus();
    }// setControls

} // ArcadeTetrisView
