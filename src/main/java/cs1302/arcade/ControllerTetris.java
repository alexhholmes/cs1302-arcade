package cs1302.arcade;

import java.util.Random;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;


/** 
* handles all of the surface-level tasks for tetris
*/
public class ControllerTetris{
    
    ArcadeTetrisView gameView;
    TetrisLevel level;
    Score score;
    TetrisBoard gameBoard;
    TetrisPiece currentPiece, nextPiece;
    boolean falling;
    int rowsCleared = 0;
    Random random = new Random();
    
    /** 
    * contains the basic setup and gameflow of tetris
    * @param view the object used to alter's the user's view of the game
    */
    public ControllerTetris(ArcadeTetrisView view){
        gameView = view;
        this.setUp();
        
        this.makeTimeLine(level.getLevel());
        
        while(true){
            if(gameBoard.canDrop(currentPiece) == false){
                score.addScore(gameBoard.calcScore());
                gameView.updateScore(score.getScore());
                rowsCleared += this.calcCleared(gameBoard.calcScore());
                currentPiece = nextPiece;
                nextPiece = new TetrisPiece(random.nextInt(7)+1);
                this.levelCheck();
                if(gameBoard.canAddToTop(currentPiece)){
                    gameBoard.addPiece(currentPiece);
                    gameView.buildBoard(gameBoard);
                }// if
                else{
                    falling = false;
                    gameBoard.addPiece(currentPiece);
                    gameView.buildBoard(gameBoard);
                    gameView.lose(score);
                }// else
            }// if
        }// while
    }// ControllerTetris
    
    /** 
    * checks to see if the level needs to increase
    */
    private void levelCheck(){
        if(level.getLevel() < 10){
            if(rowsCleared/10 > level.getLevel()){
                level.setLevel(level.getLevel()+1);
                gameView.updateLevel(level.getLevel());
            }// if
        }// if
    }// levelCheck
    
    /** 
    * updates the rate in which the tetris pieces fall
    * @param newTime the new variable used to determine the rate
    */
    private void updateTimeLine(double newTime){
        this.makeTimeLine(newTime);
    }//updateTimeLine
    
    /**
    * sets up tetris block falling periodically
    * @param time used to calculate how much time there is between drops
    */
    private void makeTimeLine(double time){
        Timeline tLine = new Timeline();
        EventHandler<ActionEvent> handler = event ->{
            if(falling){
                gameBoard.dropPiece(currentPiece);
                gameView.buildBoard(gameBoard);
            }// if
        };
        KeyFrame frame = new KeyFrame(Duration.seconds(1.7-(time*.2)), handler);
        tLine.setCycleCount(Timeline.INDEFINITE);
        tLine.getKeyFrames().add(frame);
        tLine.play();
    }// makeTimeLine
    
    /** 
    * calculates the number of rows cleared based off 
    * the score earned 
    * @param points the score in question
    * @return the number of rows cleared based on the points
    */
    private int calcCleared(int points){
        switch(points){
            case 1200: {
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
    * gets game objects set up 
    */
    private void setUp(){
        score = new Score();
        level = new TetrisLevel();
        gameBoard = new TetrisBoard();
        gameView.buildBoard(gameBoard);
        currentPiece = new TetrisPiece(random.nextInt(7)+1);
        nextPiece = new TetrisPiece(random.nextInt(7)+1);
        gameBoard.addPiece(currentPiece);
        gameView.buildBoard(gameBoard);
        falling = true;
    }// setUp
}// ControllerTetris
