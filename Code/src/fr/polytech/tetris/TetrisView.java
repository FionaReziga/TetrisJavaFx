package fr.polytech.tetris;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

import static javafx.scene.paint.Color.BLACK;

/**
 * Created by REZIGA on 14/05/2017.
 */
public class TetrisView extends Application {
    protected final static int GRID_WIDTH = 10;
    protected final static int GRID_HEIGHT = 16;
    protected final static int SIZE_CASE = 30;
    protected final static Color COLOR = BLACK;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TetrisController controller = new TetrisController(GRID_WIDTH, GRID_HEIGHT, SIZE_CASE, COLOR);
        BorderPane border = new BorderPane();
        GridPane gridPane = controller.getBoard().getGrid().getGridPane();

        gridPane.setMaxSize(SIZE_CASE * GRID_WIDTH, SIZE_CASE * GRID_HEIGHT);

        primaryStage.setTitle("Tetris");

        border.setCenter(gridPane);

        Scene scene = new Scene(border, GRID_WIDTH * SIZE_CASE + 400, GRID_HEIGHT * SIZE_CASE);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    controller.rotatePiece();
                    break;
                case DOWN:
                    controller.movePiece( 1, 0);
                    break;
                case LEFT:
                    controller.movePiece( 0, -1);
                    break;
                case RIGHT:
                    controller.movePiece( 0, 1);
                    break;
            }
        });

        primaryStage.setScene(scene);

        primaryStage.show();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                controller.movePiece( 1, 0);
            }
        }, 0, 1000);
    }
}
