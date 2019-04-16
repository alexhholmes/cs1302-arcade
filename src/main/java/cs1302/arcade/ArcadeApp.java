package cs1302.arcade;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

/**
 * An arcade application with a selection of Tetris and 2048.
 */
public class ArcadeApp extends Application {

    Stage stage;
    Scene scene;

    /**
     * {@inheritdoc}
     */
    @Override
    public void start(Stage stage) {
        this.stage = stage;

        setSelectGameScene();

        // Configure stage
        stage.setTitle("cs1302-arcade!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();
    } // start

    /**
     * Sets the {@code Scene} to the game selection view.
     */
    protected void setSelectGameScene() {
        // Instantiate components
        GridPane mainPane = new GridPane();
        // 2048 Button & Label
        Image image2048 = new Image("file:resources/2048Logo.png"
                , 100, 100, true, true);
        Button button2048 = new Button(image2048);
        start2048Event(button2048);
        Label label2048 = new Label("2048");
        // Tetris Button & Label
        Image imageTetris = new Image("file:resources/tetrisLogo.png"
                , 100, 100, true, true);
        Button buttonTetris = new Button(imageTetris);
        startTetrisEvent(buttonTetris);
        Label labelTetris = new Label("Tetris");
        // Configure main pane components
        mainPane.add(button2048, 0, 0);
        mainPane.add(buttonTetris, 1, 0);
        mainPane.add(label2048, 1, 0);
        mainPane.add(labelTetris, 1, 1);
        // Set scene
        scene = new Scene(mainPane, 400, 200, Color.BLUE);
    } // setSelectGameScene()

    /**
     * Sets {@code Button} event handler to set the scene to 2048 when pressed.
     *
     * @param button the button to set the event handler to
     */
    private void start2048Event(Button button) {
        EventHandler<ActionEvent> handler = event -> {
            Arcade2048View view = new Arcade2048View(this);
            scene = new Scene(view);
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
            scene = new Scene(view);
            stage.sizeToScene();
        };
        button.setOnAction(handler);
    } // startTetrisEvent(Button)

} // ArcadeApp
