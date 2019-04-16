package cs1302.arcade;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

public class ArcadeTetrisView {
    
    private ArcadeApp app;
    private GridPane mainPane;

    /**
     * Constructs a new Tetris game.
     *
     * @param app the application this game runs within
     */
    public ArcadeTetrisView(ArcadeApp app) {
        this.app = app;
        mainPane = new GridPane();
    } // ArcadeTetrisView(ArcadeApp)

    /**
     * Return the parent node of this view.
     *
     * @return parent node of this view.
     */
    public Parent asParent() {
        return mainPane;
    } // asParent()

} // ArcadeTetrisView
