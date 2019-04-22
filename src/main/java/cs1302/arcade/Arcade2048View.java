package cs1302.arcade;

import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;
import javafx.geometry.Pos;

public class Arcade2048View {
    
    private ArcadeApp app;
    private VBox root;
    private GridPane gridPane;
    private BoardGroup board;
    private Model2048 model;
    private Controller2048 controller;
    
    public Arcade2048View(ArcadeApp app) {
        // Instance variables
        this.app = app;
        model = new Model2048();
        controller = new Controller2048(model);
        
        // Build UI
        buildView();

        // Start game
        controller.startGame();
    } // Arcade2048View(ArcadeApp)

    public void buildView() {
        // MenuBar
        MenuBar menuBar = new MenuBar();
        Menu gameMenu = new Menu("Game");
        Menu helpMenu = new Menu("Help");
        MenuItem resetItem = new MenuItem("Reset Game");
        MenuItem highScoresItem = new MenuItem("High Scores");
        MenuItem exitItem = new MenuItem("Exit Game");
        MenuItem helpItem = new MenuItem("How to Play");
        gameMenu.getItems()
            .addAll(resetItem, highScoresItem, exitItem);
        helpMenu.getItems()
            .addAll(helpItem);
        menuBar.getChildren()
            .addAll(gameMenu, helpMenu);
        EventHandler<ActionEvent> resetHandler = event -> {
            controller.reset();
        };
        resetItem.setOnAction(resetHandler);
        EventHandler<ActionEvent> highScoreHandler = event -> {
            openHighScoresWindow(); // TODO
        };
        EventHandler<ActionEvent> exitHandler = event -> {
            app.setSelectGameScene();
        };
        exitItem.setOnAction(exitHandler);
        EventHandler<ActionEvent> helpHandler = event -> {
            openHelpWindow();
        };
        // Game label
        Label gameLabel = new Label("2048");
        // Score
        VBox scoreVBox = new VBox(4);
        Label scoreTitle = new Label("Score:");
        Label scoreLabel = new Label();
        scoreLabel.textProperty().bind(model.scoreProperty());
        scoreVBox.getChildren()
            .addAll(scoreTitle, scoreLabel);
        scoreVbox.setAlignment(Pos.CENTER);
        // Board
        board = new BoardGroup(); // TODO
        // GridPane
        gridPane = new GridPane();
        gridPane.add(gameLabel, 0, 0, 1, 1);
        gridPane.add(scoreVBox, 1, 0, 1, 1);
        gridPane.add(board, 0, 1, 2, 1);
        gridPane.setHAlignment(gameLabel, HPos.Center);
        gridPane.setVAlignment(gameLabel, VPos.Center);
        gridPane.setHAlignment(scoreVBox, HPos.Center);
        gridPane.setVAlignment(scoreVBox, VPos.Center);
        GridPane.setHGrow(gameLabel, Priority.ALWAYS);
        GridPane.setHGrow(scoreVBox, Priority.ALWAYS);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        // Root
        root = new VBox();
        root.getChildren()
            .addAll(menuBar, gridPane);
    } // buildView()

    public Parent asParent() {
        return root;
    } // asParent()

} // Arcade2048View
