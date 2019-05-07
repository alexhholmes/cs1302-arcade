package cs1302.arcade;

import java.util.Random;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;


/** 
 * Handles all of the surface-level tasks for tetris.
 */
public class ControllerTetris{

    /** The game display controller */ 
    ArcadeTetrisView gameView;
    /** The timeline to run the periodic dropping */
    Timeline tLine;
    /** The current level */
    TetrisLevel level;
    /** The player's score */
    Score score;
    /** The internal storage for the board */
    TetrisBoard gameBoard;
    /** The piece in play and the next piece in play */
    TetrisPiece currentPiece, nextPiece;
    /** Controls when the piece should be falling */
    boolean falling;
    /** Total number of rows cleared */
    int rowsCleared = 0;
    /** Used to generate a new piece */
    Random random = new Random();

    /** 
     * Contains the basic setup and gameflow of tetris.
     *
     * @param view the object used to alter's the user's view of the game
     */
    public ControllerTetris(ArcadeTetrisView view){
        gameView = view;
        this.setUp();

        this.makeTimeLine(level.getLevel());

    }// ControllerTetris


    /**
     * Sets up tetris block falling periodically.
     *
     * @param time used to calculate how much time there is between drops
     */
    private void makeTimeLine(double time){
        tLine = new Timeline();
        EventHandler<ActionEvent> handler = event ->{
            if(falling){
                gameBoard.dropPiece(currentPiece);
                gameView.buildBoard(gameBoard);

                if(gameBoard.canDrop(currentPiece) == false){
                    int pointsEarned = gameBoard.calcScore();
                    score.addScore(pointsEarned);
                    gameView.updateScore(score.getScore());
                    rowsCleared += this.calcCleared(pointsEarned);
                    currentPiece = nextPiece;
                    nextPiece = new TetrisPiece(random.nextInt(7)+1);
                    this.levelCheck();
                    if(gameBoard.canAddToTop(currentPiece)){
                        gameBoard.addPiece(currentPiece);
                        gameView.buildBoard(gameBoard);
                        gameView.buildNext(nextPiece);
                    }// if
                    else{
                        falling = false;
                        gameBoard.addPiece(currentPiece);
                        gameView.buildBoard(gameBoard);
                        gameView.lose(score);
                    }// else
                }// if
            }// if
        };
        KeyFrame frame = new KeyFrame(Duration.seconds(1.7-(time*.2)), handler);
        tLine.setCycleCount(Timeline.INDEFINITE);
        tLine.getKeyFrames().add(frame);
        tLine.play();
    }// makeTimeLine

    /** 
     * Checks to see if the level needs to increase.
     */
    private void levelCheck(){
        if(level.getLevel() < 10){
            if(rowsCleared/10 >= level.getLevel()){
                level.setLevel(level.getLevel()+1);
                gameView.updateLevel(level.getLevel());
                tLine.pause();
                this.makeTimeLine(level.getLevel());
            }// if
        }// if
    }// levelCheck

    /** 
     * Resets the game.
     */
    public void reset(){
        score = new Score();
        level = new TetrisLevel();
        level.setLevel(1);
        gameBoard = new TetrisBoard();
        currentPiece = new TetrisPiece(random.nextInt(7)+1);
        nextPiece = new TetrisPiece(random.nextInt(7)+1);
        gameView.buildBoard(gameBoard);
        gameView.buildNext(nextPiece);
        gameView.updateScore(score.getScore());
        gameView.updateLevel(level.getLevel());
        falling = true;
    }// reset

    /** 
     * Calculates the number of rows cleared based off 
     * the score earned.
     *
     * @param points the score in question
     * @return the number of rows cleared based on the points
     */
    private int calcCleared(int points){
        switch(points){
            case 1200:
                return 4;
            }// case 1200
            case 40: {
                return 1;
            }// case 40
            case 140: {}
            case 300: {
                return 3;
            }// case 300/140
            case 100: {}
            case 80: {
                return 2;
            }// case 80/100
            default:{
                return 0;
            }//default
        }// switch
    }//calcCleared

    /** 
     * Gets game objects set up.
     */
    private void setUp(){
        rowsCleared = 0;
        score = new Score();
        level = new TetrisLevel();
        gameBoard = new TetrisBoard();
        currentPiece = new TetrisPiece(random.nextInt(7)+1);
        nextPiece = new TetrisPiece(random.nextInt(7)+1);
        gameView.buildBoard(gameBoard);
        gameView.buildNext(nextPiece);
        falling = true;
    }// setUp

    /**
     * Moves current piece to the left.
     */
    public void moveLeft(){
        gameBoard.shiftPieceLeft(currentPiece);
        gameView.buildBoard(gameBoard);
    }// moveLeft

    /**
     * Moves current piece to the right.
     */
    public void moveRight(){
        gameBoard.shiftPieceRight(currentPiece);
        gameView.buildBoard(gameBoard);
    }// moveLeft

    /** 
     * Moves the current piece down.
     */
    public void moveDown(){
        gameBoard.dropPiece(currentPiece);
        gameView.buildBoard(gameBoard);
    }// moveDown

    /**
     * Rotates the current piece
     */
    public void rotate(){
        gameBoard.rotatePiece(currentPiece);
        gameView.buildBoard(gameBoard);
    }// rotate

}// ControllerTetris
