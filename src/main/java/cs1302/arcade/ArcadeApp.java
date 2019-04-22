package cs1302.arcade;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.control.Label;
import javafx.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

/**
 * An arcade application with a selection of Tetris and 2048.
 */
public class ArcadeApp extends Application {

    Stage stage;
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
        Image imageTetris = new Image(FILE LOCATION, 100, 100, true, true); // TODO
        Image image2048 = new Image(FILE LOCATION, 100, 100, true, true); // TODO
        ImageView imageViewTetris = new ImageView(imageTetris);
        ImageView imageView2048 = new ImageView(image2048);
        // Buttons
        Button buttonTetris = new Button(imageTetris);
        Button button2048 = new Button(image2048);
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
            stage.setScene(new Scene(view.asParent()));
            stage.sizeToScene();
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
            stage.setScene(new Scene(view.asParent()));
            stage.sizeToScene();
        };
        button.setOnAction(handler);
    } // startTetrisEvent(Button)

} // ArcadeApp
