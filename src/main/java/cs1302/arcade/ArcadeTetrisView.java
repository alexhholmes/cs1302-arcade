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

/** 
* controls what the user sees while playing tetris
*/
public class ArcadeTetrisView {
    
    private ArcadeApp app;
    private Label score, level, help;
    private HBox mainPane;
    private VBox gameSide, statSide;
    private GridPane gamePane, nextPane;
    private Button quit, restart; 
    private Alert warning = new Alert(AlertType.ERROR);
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
        game = new ControllerTetris(this);
        
    } // ArcadeTetrisView(ArcadeApp)

    /**
     * Builds the attributes and components of the main pane for this Tetris
     * game view.
     */
    private void buildMainPane() {
        score = new Label("Score: 0");
        level = new Label("Level: ");
        String tempHelp = "Use the left, right, and down keys to move the tetriminos.";
        String tempHelp2 = "Use the up key to rotate the tetriminos.";
        help = new Label("How to play: \n" + tempHelp + tempHelp2);
        nextPane = new GridPane();
        nextPane.setStyle("-fx-grid-lines-visible: true; -fx-grid-lines-color: white");
        gamePane = new GridPane();
        gamePane.setStyle("-fx-grid-lines-visible: true; -fx-grid-lines-color: white");
        restart = new Button("Restart");
        quit = new Button("Quit");
        quitGame(quit);
        gameSide = new VBox(gamePane);
        statSide = new VBox(score, level, nextPane, restart, quit);
        
        mainPane.getChildren()
            .addAll(gameSide, statSide);
    } // buildMainPane
    
    /** 
    * displays a pop-up when the player loses
    * score the score the user earned
    */
    public void lose(Score score){
        warning.setTitle("FOOL!");
        warning.setHeaderText(null);
        warning.setContentText("Game Over! Your score was: " + score.getScore() + 
        ". To play again, close this and hit reset!");
         Platform.runLater(() -> {warning.showAndWait();});
    }// lose
    
    /** 
    * builds the tetris board
    * @param board the board object to be displayed
    */
    public void buildBoard(TetrisBoard board){
        for(int x = 0; x<10; x++){
            for(int y = 0; y< 20; y++){
                Rectangle tempRec = new Rectangle(25.0, 25.0, board.getValue(x,y));
                int tempX = this.getVal(x);
                int tempY = this.getVal(y);
                Platform.runLater(() -> {
                    gamePane.add(tempRec, tempX, tempY);
                });// runLater
            }// for
        }// for
    }// buildBoard
    
    /** 
    * boots user to game select when clicking quit button
    * @param button the user has to click to get booted to the game select
    */
    private void quitGame(Button button){
        EventHandler<ActionEvent> handler = event -> {
            app.setSelectGameScene();
        };
        button.setOnAction(handler);
    }// quitGame
    
    /** 
    * restarts the tetris game
    * @param button the button that needs to be pressed to restart the game
    */
    private void resetGame(Button button){
        EventHandler<ActionEvent> handler = event -> {
            game = new ControllerTetris(this);
        };
        button.setOnAction(handler);
    }// resetGame
    
    /** 
    * builds the grid pane to display the next block
    * @param piece the piece being displayed
    */
    public void buildNext(TetrisPiece piece){
        for(int x = 0; x < 4; x++){
            for(int y = 0; y < 4; y++){
                Rectangle tempRec = new Rectangle(25.0, 25.0, Color.BLACK);
                int tempX = this.getVal(x);
                int tempY = this.getVal(y);
                Platform.runLater(() -> {
                    nextPane.add(tempRec, tempX, tempY);
                });// runLater
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
    * updates the score display
    * @param points the new score to display
    */
    public void updateScore(int points){
        String scoreText = "Score: " + Integer.toString(points);
        score.setText(scoreText);
    }// updateScore
    
    /**
    * updates the level display
    * @param num the new level to display
    */
    public void updateLevel(int num){
        String levelText = "Level: " + Integer.toString(num);
        level.setText(levelText);
    }// updateLevel
    
    /** 
    * helper method for build board that simply returns 
    * a given value to make java think the int is final
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

} // ArcadeTetrisView
