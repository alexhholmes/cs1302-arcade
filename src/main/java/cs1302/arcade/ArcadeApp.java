package cs1302.arcade;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

/**
 * An arcade application with a selection of Tetris and 2048.
 */
public class ArcadeApp extends Application {

    /** The Stage */
    Stage stage;
    /** Game Select Scene */
    Scene gameSelectScene;

    /**
     * {@inheritdoc}
     */
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        buildSelectGameScene();
        // Configure stage
        stage.setTitle("cs1302-arcade!");
        stage.setScene(gameSelectScene);
        stage.setResizable(false);
        stage.show();
    } // start

    /**
     * Sets the stage to display the game selection scene.
     */
    public void setSelectGameScene() {
        stage.setScene(gameSelectScene);
        stage.sizeToScene();
    } // setSelectGameScene()

    /**
     * Returns the {@code Scene} that displays the game selection view.
     */
    private void buildSelectGameScene() {
        // Image views
        Image imageTetris = new Image("Tetris.png", 100, 100, true, true);
        Image image2048 = new Image("2048.png", 100, 100, true, true);
        ImageView imageViewTetris = new ImageView(imageTetris);
        ImageView imageView2048 = new ImageView(image2048);
        // Buttons
        Button buttonTetris = new Button();
        Button button2048 = new Button();
        buttonTetris.setGraphic(imageViewTetris);
        button2048.setGraphic(imageView2048);
        startTetrisEvent(buttonTetris);
        start2048Event(button2048);
        // Labels
        Label labelTetris = new Label("Tetris");
        Label label2048 = new Label("2048");

        // GridPane children and configuration
        GridPane gridPane = new GridPane();
        gridPane.add(buttonTetris, 0, 0, 1, 1);
        gridPane.add(button2048, 1, 0, 1, 1);
        gridPane.add(labelTetris, 0, 1, 1, 1);
        gridPane.add(label2048, 1, 1, 1, 1);
        GridPane.setHalignment(labelTetris, HPos.CENTER);
        GridPane.setHalignment(label2048, HPos.CENTER);
        // Center grid pane on the stage
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        // Set scene
        gameSelectScene = new Scene(gridPane, 300, 200, Color.BLUE);
    } // setSelectGameScene()

    /**
     * Sets {@code Button} event handler to set the scene to 2048 when pressed.
     *
     * @param button the button to set the event handler to
     */
    private void start2048Event(Button button) {
        EventHandler<ActionEvent> handler = event -> {
            Arcade2048View view = new Arcade2048View(this);
            Scene scene = new Scene(view.asParent());
            scene.getStylesheets().add("style2048.css");
            stage.setScene(scene);
            stage.sizeToScene();
            view.setControls();
        };
        button.setOnAction(handler);
    } // start2048Event(Button)

    /**
     * Sets {@code Button} event handler to set the scene to Tetris when pressed.
     *
     * @param button the button to set the event handler to
     */
    private void startTetrisEvent(Button button) {
        EventHandler<ActionEvent> handler = event -> {
            ArcadeTetrisView view = new ArcadeTetrisView(this);
            Scene scene = new Scene(view.asParent());
            stage.setScene(scene);
            stage.sizeToScene();
            view.setControls();
        };
        button.setOnAction(handler);
    } // startTetrisEvent(Button)

} // ArcadeApp
