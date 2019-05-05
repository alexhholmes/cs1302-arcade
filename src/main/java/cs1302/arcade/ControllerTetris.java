package cs1302.arcade;

import java.util.Random;

public class ControllerTetris {
    public void start(){
        TetrisBoard gameBoard = new TetrisBoard();
        TetrisPiece currentPiece = new TetrisPiece(Random.nextInt(7)+1);
        TetrisPiece nextPiece = new TetrisPiece(Random.nextInt(7)+1);
        int score = 0;
        gameBoard.addPiece(currentPiece);
        boolean falling = true;
        
        Timeline tLine = new Timeline();
        EventHandler<ActionEvent> handler = event ->{
            if(falling){
                gameBoard.dropPiece(currentPiece);
            }// if
        };
        KeyFrame frame = new KeyFrame(Duration.seconds(2), handler);
        tLine.setCycleCount(Timeline.INDEFINITE);
        tLine.getKeyFrames().add(frame);
        tLine.play();
        
        while(1 == 1){
            if(gameBoard.canDrop(currentPiece) == false){
                score += gameBoard.calcScore();
                currentPiece = nextPiece;
                nextPiece = new TetrisPiece(Random.nextInt(7)+1);
                if(gameBoard.canAddToTop(currentPiece)){
                    gameBoard.addPiece(currentPiece);
                }// if
                else{
                    falling = false;
                    gameBoard.addPiece(currentPiece);
                    
                }// else
            }// if
        }// while
    }// 
}// ControllerTetris
