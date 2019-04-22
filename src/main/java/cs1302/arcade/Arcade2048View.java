package cs1302.arcade;

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
        MenuItem exitItem = new MenuItem("Exit Game");
        MenuItem helpItem = new MenuItem("How to Play");
        gameMenu.getItems()
            .addAll(resetItem, exitItem);
        helpMenu.getItems()
            .addAll(helpItem);
        menuBar.getChildren()
            .addAll(gameMenu, helpMenu);
        // Game label
        Label gameLabel = new Label("2048");
        // Score
        VBox scoreVBox = new VBox();
        Label scoreTitle = new Label("Score:");
        Label scoreLabel = new Label();
        scoreVBox
        // Board
        board = new BoardGroup();
        // GridPane
        gridPane = new GridPane();
        gridPane.getChildren()
            .addAll(gameLabel, scoreVBox); // TODO
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        // Root
        root.getChildren()
            .addAll(menuBar, gridPane);
    } // buildView()

    public Parent asParent() {
        return root;
    } // asParent()

} // Arcade2048View
