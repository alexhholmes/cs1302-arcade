package cs1302.arcade;

import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.control.MenuBar;

public class ArcadeTetrisView {
    
    private ArcadeApp app;
    private VBox mainPane;
    private MenuBar menuBar;
    private GridPane gamePane;

    /**
     * Constructs a new Tetris game.
     *
     * @param app the application this game runs within
     */
    public ArcadeTetrisView(ArcadeApp app) {
        this.app = app;
        mainPane = new VBox();

        buildMainPane();
    } // ArcadeTetrisView(ArcadeApp)

    /**
     * Builds the attributes and components of the main pane for this Tetris
     * game view.
     */
    private void buildMainPane() {
        menuBar = new TetrisMenuBar();
        gamePane = new TetrisGamePane();
        
        
        mainPane.getChildren()
            .addAll(menuBar, gamePane);
    } // buildMainPane()

    /**
     * Return the parent node of this view.
     *
     * @return parent node of this view.
     */
    public Parent asParent() {
        return mainPane;
    } // asParent()

} // ArcadeTetrisView
