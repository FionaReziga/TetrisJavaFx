package fr.polytech.puzzle;

import fr.polytech.library.view.GPane;
import fr.polytech.puzzle.controller.PuzzleController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.BLACK;

/**
 * Created by REZIGA on 14/05/2017.
 * Classe qui définit la vue du Puzzle
 */
public class PuzzleView extends Application {
    public final static int PUZZLE_GRID_WIDTH = 6;
    public final static int PUZZLE_GRID_HEIGHT = 6;
    protected final static int SIZE_CASE = 60;
    protected final static Color GRID_COLOR = BLACK;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Démarrage
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        PuzzleController controller = new PuzzleController(PUZZLE_GRID_WIDTH, PUZZLE_GRID_HEIGHT, SIZE_CASE, GRID_COLOR);
        BorderPane border = new BorderPane();

        //BorderPane previousBorder = new BorderPane();
        GPane gridPane = new GPane(controller.getBoard());

        gridPane.setGridLinesVisible(true);
        for (int i = 0; i < PUZZLE_GRID_WIDTH; i++) {
            for (int j = 0; j < PUZZLE_GRID_HEIGHT; j++) {
                Rectangle rectangle = new Rectangle(SIZE_CASE, SIZE_CASE);;
                rectangle.setFill(GRID_COLOR);
                rectangle.setStroke(BLACK);
                rectangle.setStrokeWidth(1);
                gridPane.add(rectangle, i, j);
            }
        }

        // Add constraints
        for (int i = 0; i < PUZZLE_GRID_WIDTH; i++) {
            ColumnConstraints column = new ColumnConstraints(SIZE_CASE);
            gridPane.getColumnConstraints().add(column);
        }
        for (int j = 0; j < PUZZLE_GRID_HEIGHT; j++) {
            RowConstraints row = new RowConstraints(SIZE_CASE);
            gridPane.getRowConstraints().add(row);
        }

        gridPane.setMaxSize(SIZE_CASE * PUZZLE_GRID_WIDTH, SIZE_CASE * PUZZLE_GRID_HEIGHT);

        primaryStage.setTitle("Puzzle");
        primaryStage.setResizable(false);

        border.setCenter(gridPane);

        Scene scene = new Scene(border, PUZZLE_GRID_WIDTH * SIZE_CASE, PUZZLE_GRID_HEIGHT * SIZE_CASE);
        primaryStage.setTitle("Puzzle");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Scene scene = new Scene(border, PUZZLE_GRID_WIDTH * SIZE_CASE + 400, PUZZLE_GRID_HEIGHT * SIZE_CASE);

        scene.setOnKeyPressed(controller);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
