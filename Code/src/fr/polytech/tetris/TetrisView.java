package fr.polytech.tetris;

import fr.polytech.library.view.GPane;
import fr.polytech.tetris.controller.TetrisController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.BLACK;

/**
 * Created by REZIGA on 14/05/2017.
 */
public class TetrisView extends Application {
    public final static int GRID_WIDTH = 10;
    public final static int GRID_HEIGHT = 16;
    protected final static int PREVIOUS_GRID_WIDTH = 4;
    protected final static int PREVIOUS_GRID_HEIGHT = 4;
    protected final static int SIZE_CASE = 30;
    protected final static int SIZE_PIECE_CASE = 30;
    protected final static Color PREVIOUS_GRID_COLOR = BLACK;
    protected final static Color GRID_COLOR = BLACK;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TetrisController controller = new TetrisController(GRID_WIDTH, GRID_HEIGHT, SIZE_CASE, GRID_COLOR);
        BorderPane border = new BorderPane();

        //BorderPane previousBorder = new BorderPane();
        GPane gridPane = new GPane(controller.getBoard());

        gridPane.setGridLinesVisible(true);
        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j++) {
                Rectangle rectangle = new Rectangle(SIZE_CASE, SIZE_CASE);
                rectangle.setFill(GRID_COLOR);
                rectangle.setStroke(BLACK);
                rectangle.setStrokeWidth(1);
                gridPane.add(rectangle, i, j);
            }
        }

        // Add constraints
        for (int i = 0; i < GRID_WIDTH; i++) {
            ColumnConstraints column = new ColumnConstraints(SIZE_CASE);
            gridPane.getColumnConstraints().add(column);
        }
        for (int j = 0; j < GRID_HEIGHT; j++) {
            RowConstraints row = new RowConstraints(SIZE_CASE);
            gridPane.getRowConstraints().add(row);
        }
        //GridPane gridPreviousPane = controller.getBoardTetris().getGrid().getGridPane();

        gridPane.setMaxSize(SIZE_CASE * GRID_WIDTH, SIZE_CASE * GRID_HEIGHT);
        //gridPreviousPane.setMaxSize(SIZE_CASE * PREVIOUS_GRID_WIDTH, SIZE_CASE * PREVIOUS_GRID_HEIGHT);

        primaryStage.setTitle("Tetris");
        primaryStage.setResizable(false);

        border.setCenter(gridPane);

        final Button buttonPause = new Button("Pause");
        AnchorPane.setBottomAnchor(buttonPause, 150.);
        AnchorPane.setRightAnchor(buttonPause, 80.);

        final Button buttonNewGame = new Button("Nouvelle Partie");
        AnchorPane.setBottomAnchor(buttonNewGame, 100.);
        AnchorPane.setRightAnchor(buttonNewGame, 50.);

        AnchorPane.setLeftAnchor(border, 200.);
        AnchorPane.setBottomAnchor(border, 0.);
        final AnchorPane root = new AnchorPane();
        root.getChildren().setAll(buttonNewGame, buttonPause, border);
        final Scene scene = new Scene(root, GRID_WIDTH * SIZE_CASE + 400, GRID_HEIGHT * SIZE_CASE);
        primaryStage.setTitle("Tetris");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Scene scene = new Scene(border, GRID_WIDTH * SIZE_CASE + 400, GRID_HEIGHT * SIZE_CASE);

        scene.setOnKeyPressed(controller);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
