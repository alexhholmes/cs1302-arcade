package cs1302.arcade;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;

public class Model2048 {

    private IntegerProperty score = new SimpleIntegerProperty();
    
    public Model2048() {
        resetScore();
    } // Model2048()

    public ReadOnlyIntegerProperty scoreProperty() {
        return score;
    } // scoreProperty()

    public addScore(int num) {
        score.setValue(score.getValue() + num);
    } // addScore(int)

    public void resetScore() {
        score.setValue(0);
    } // resetScore()

} // Model2048
