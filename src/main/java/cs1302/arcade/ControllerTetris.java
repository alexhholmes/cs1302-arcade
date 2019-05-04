package cs1302.arcade;

import java.util.Random;

public class ControllerTetris {
    public void start(){
        TetrisBoard gameBoard = new TetrisBoard();
            TetrisPiece currentPiece = new TetrisPiece(Random.nextInt(7)+1);
                Timeline tLine = new Timeline();
                EventHandler<ActionEvent> handler = event ->{
                    if(gameBoard.canDrop(currentPiece){
                        gameBoard.dropPiece(currentPiece);
                    }// if
                };
                KeyFrame frame = new KeyFrame(Duration.seconds(2), handler);
                tLine.setCycleCount(Timeline.INDEFINITE);
                tLine.getKeyFrames().add(frame);
                tLine.play();
    }// 
}// ControllerTetris
