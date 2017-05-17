package fr.polytech.tetris;

import fr.polytech.library.view.GPane;
import fr.polytech.tetris.controller.TetrisController;
import fr.polytech.tetris.model.BoardTetris;
import fr.polytech.tetris.model.GridTetris;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;

/**
 * Created by REZIGA on 14/05/2017.
 */
public class TetrisView extends Application implements Observer {
    public final static int GRID_WIDTH = 10;
    public final static int GRID_HEIGHT = 16;
    protected final static int PREVIOUS_GRID_WIDTH = 5;
    protected final static int PREVIOUS_GRID_HEIGHT = 5;
    protected final static int SIZE_CASE = 30;
    protected final static int SIZE_PIECE_CASE = 30;
    protected final static Color PREVIOUS_GRID_COLOR = BLACK;
    protected final static Color GRID_COLOR = BLACK;

    private GPane gPane;
    private GridPane gridPane;
    private Label scoreLabel;
    private Text scoreValue;
    private TetrisController controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        controller.getBoard().setStop(true);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new TetrisController(GRID_WIDTH, GRID_HEIGHT, SIZE_CASE, GRID_COLOR);
        BorderPane border = new BorderPane();

        BorderPane previousBorder = new BorderPane();
        gPane = new GPane(controller.getBoard());
        gridPane = new GridPane();
        controller.getBoard().addObserver(this);

        initializeGridPane();
        initializePreviousGridPane();

        border.setCenter(gPane);

        // Boutons
        AnchorPane.setBottomAnchor(gridPane, 40.);
        AnchorPane.setRightAnchor(gridPane, 20.);

        final Button buttonPause = new Button("Pause");
        AnchorPane.setTopAnchor(buttonPause, 250.);
        AnchorPane.setLeftAnchor(buttonPause, 30.);
        buttonPause.setStyle("-fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%), linear-gradient(#020b02, #3a3a3a), linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%), linear-gradient(#8a8a8a 0%, #6b6a6b 20%, #343534 80%, #262626 100%), linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%); " + " -fx-background-insets: 0,1,4,5,6;  -fx-background-radius: 9,8,5,4,3;  -fx-padding: 10 52 10 52;  -fx-font-size: 15px;  -fx-font-weight: bold;  -fx-text-fill: whitesmoke;  -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1)");

        buttonPause.setOnAction(e -> {
            if (controller.getBoard().isStop()) {
                buttonPause.setText("  Go  !");
                controller.getBoard().setStop(false);
            } else {
                buttonPause.setText(" Pause ");
                controller.getBoard().setStop(true);
            }
        });

        final Button buttonNewGame = new Button("Nouvelle Partie");
        AnchorPane.setTopAnchor(buttonNewGame, 300.);
        AnchorPane.setLeftAnchor(buttonNewGame, 30.);
        buttonNewGame.setFont(Font.font("Helvetica"));
        buttonNewGame.setStyle("-fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%), linear-gradient(#020b02, #3a3a3a), linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%), linear-gradient(#8a8a8a 0%, #6b6a6b 20%, #343534 80%, #262626 100%), linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%); " + " -fx-background-insets: 0,1,4,5,6;  -fx-background-radius: 9,8,5,4,3;  -fx-padding: 10 20 10 20;  -fx-font-size: 15px;  -fx-font-weight: bold;  -fx-text-fill: whitesmoke;  -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1)");

        buttonNewGame.setOnAction(e -> {
            controller.newGame();
            JOptionPane.showMessageDialog(null, "Nouvelle Partie");

        });

        scoreLabel = new Label("Score");
        scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        scoreLabel.setStyle("-fx-text-fill: linear-gradient(to right, red,orange,yellow,green,blue,indigo,violet);");
        AnchorPane.setTopAnchor(scoreLabel, 50.);
        AnchorPane.setRightAnchor(scoreLabel, 40.);

        scoreValue = new Text("" + controller.getBoard().getScore());
        scoreValue.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        scoreValue.setFill(WHITE);
        AnchorPane.setTopAnchor(scoreValue, 100.);
        AnchorPane.setRightAnchor(scoreValue, 40.);

        AnchorPane.setLeftAnchor(border, 200.);
        AnchorPane.setBottomAnchor(border, 0.);

        Image logo = new Image("/fr/polytech/tetris/logo.png");
        ImageView iv3 = new ImageView();
        iv3.setImage(logo);
        iv3.setFitHeight(100);
        iv3.setFitWidth(150);

        AnchorPane.setLeftAnchor(iv3, 25.);
        AnchorPane.setTopAnchor(iv3, 60.);

        final AnchorPane root = new AnchorPane();
        root.getChildren().setAll(scoreLabel, scoreValue, buttonNewGame, buttonPause, border, iv3, gridPane);

        Image im = new Image("/fr/polytech/tetris/bg.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(GRID_WIDTH * SIZE_CASE + 400, GRID_HEIGHT * SIZE_CASE, false, false, false, false);
        root.setBackground(new Background(new BackgroundImage(im, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize)));

        final Scene scene = new Scene(root, GRID_WIDTH * SIZE_CASE + 400, GRID_HEIGHT * SIZE_CASE);
        primaryStage.getIcons().add(logo);
        scene.setOnKeyPressed(controller);

        primaryStage.setTitle("Tetris");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void initializeGridPane() {
        gPane.setGridLinesVisible(true);
        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j++) {
                Rectangle rectangle = new Rectangle(SIZE_CASE, SIZE_CASE);
                rectangle.setFill(GRID_COLOR);
                rectangle.setStroke(BLACK);
                rectangle.setStrokeWidth(1);
                gPane.add(rectangle, i, j);
            }
        }

        // Add constraints
        for (int i = 0; i < GRID_WIDTH; i++) {
            ColumnConstraints column = new ColumnConstraints(SIZE_CASE);
            gPane.getColumnConstraints().add(column);
        }
        for (int j = 0; j < GRID_HEIGHT; j++) {
            RowConstraints row = new RowConstraints(SIZE_CASE);
            gPane.getRowConstraints().add(row);
        }
        //GridPane gridPreviousPane = controller.getBoard();

        gPane.setMaxSize(SIZE_CASE * GRID_WIDTH, SIZE_CASE * GRID_HEIGHT);
        //gridPreviousPane.setMaxSize(SIZE_CASE * PREVIOUS_GRID_WIDTH, SIZE_CASE * PREVIOUS_GRID_HEIGHT);

    }

    private void initializePreviousGridPane() {
        gridPane.setGridLinesVisible(true);
        for (int i = 0; i < PREVIOUS_GRID_WIDTH; i++) {
            for (int j = 0; j < PREVIOUS_GRID_HEIGHT; j++) {
                Rectangle rectangle = new Rectangle(SIZE_CASE, SIZE_CASE);
                rectangle.setFill(PREVIOUS_GRID_COLOR);
                rectangle.setStroke(BLACK);
                rectangle.setStrokeWidth(1);
                gridPane.add(rectangle, i, j);
            }
        }

        // Add constraints
        for (int i = 0; i < PREVIOUS_GRID_WIDTH; i++) {
            ColumnConstraints column = new ColumnConstraints(SIZE_CASE);
            gridPane.getColumnConstraints().add(column);
        }
        for (int j = 0; j < PREVIOUS_GRID_HEIGHT; j++) {
            RowConstraints row = new RowConstraints(SIZE_CASE);
            gridPane.getRowConstraints().add(row);
        }
        //GridPane gridPreviousPane = controller.getBoard();

        gridPane.setMaxSize(SIZE_CASE * PREVIOUS_GRID_WIDTH, SIZE_CASE * PREVIOUS_GRID_HEIGHT);
        //gridPreviousPane.setMaxSize(SIZE_CASE * PREVIOUS_GRID_WIDTH, SIZE_CASE * PREVIOUS_GRID_HEIGHT);

    }

    @Override
    public void update(Observable o, Object arg) {
        BoardTetris board = (BoardTetris) o;
        scoreValue.setText(board.getScore() + "");
        GridTetris grid = (GridTetris)board.getGrid();

        if(((GridTetris) board.getGrid()).isGameOver()){
            controller.getBoard().newGame();
        }

        // On nettoie la grille
        for (int i = 0; i < PREVIOUS_GRID_HEIGHT; i++) {
            for (int j = 0; j < PREVIOUS_GRID_WIDTH; j++) {
                Node object = gridPane.getChildren().get(i + (j * PREVIOUS_GRID_HEIGHT) + 1);
                if (object instanceof Rectangle) {
                    Rectangle current = (Rectangle) gridPane.getChildren().get(i + (j * PREVIOUS_GRID_HEIGHT) + 1);
                    current.setFill(BLACK);
                }
            }
        }

        int[][] matrixPiece = grid.getNextPiece().getMatrix();
        for (int i = 0; i < matrixPiece.length; i++) {
            for (int j = 0; j < matrixPiece[0].length; j++) {
                Node object = gridPane.getChildren().get((i+1) * 5 + j + 2 );
                if (object instanceof Rectangle && matrixPiece[i][j] != 0) {
                    Rectangle current = (Rectangle) object;
                    current.setFill(grid.getNextPiece().getColor());
                }
            }
        }

    }
}
