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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;

/**
 * Created by REZIGA on 14/05/2017.
 * Classe qui génère la vue du jeu TETRIS
 */
public class TetrisView extends Application implements Observer {
    public final static int GRID_WIDTH = 10;
    public final static int GRID_HEIGHT = 16;
    private final static int PREVIOUS_GRID_WIDTH = 5;
    private final static int PREVIOUS_GRID_HEIGHT = 5;
    private final static int SIZE_CASE = 30;
    private final static Color PREVIOUS_GRID_COLOR = BLACK;
    private final static Color GRID_COLOR = BLACK;

    private GridPane previousPane;
    private Text scoreValue;
    private TetrisController controller;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Démarrage du jeu
     * Démarrage du jeu
     *
     * @throws Exception
     */
    @Override
    public void stop() {
        controller.stopGame();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new TetrisController(GRID_WIDTH, GRID_HEIGHT, SIZE_CASE, GRID_COLOR);
        controller.getBoard().addObserver(this);

        BorderPane border = new BorderPane();

        GPane tetrisPane = new GPane(controller.getBoard());
        previousPane = new GridPane();

        initializeGrid(tetrisPane, GRID_WIDTH, GRID_HEIGHT, GRID_COLOR, SIZE_CASE);
        initializeGrid(previousPane, PREVIOUS_GRID_WIDTH, PREVIOUS_GRID_HEIGHT, PREVIOUS_GRID_COLOR, SIZE_CASE);

        border.setCenter(tetrisPane);

        // Boutons
        AnchorPane.setBottomAnchor(previousPane, 40.);
        AnchorPane.setRightAnchor(previousPane, 20.);

        final Button buttonNewGame = new Button("Nouvelle Partie");
        AnchorPane.setTopAnchor(buttonNewGame, 300.);
        AnchorPane.setLeftAnchor(buttonNewGame, 30.);
        buttonNewGame.setFont(Font.font("Helvetica"));
        buttonNewGame.setStyle("-fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%), linear-gradient(#020b02, #3a3a3a), linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%), linear-gradient(#8a8a8a 0%, #6b6a6b 20%, #343534 80%, #262626 100%), linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%); " + " -fx-background-insets: 0,1,4,5,6;  -fx-background-radius: 9,8,5,4,3;  -fx-padding: 10 20 10 20;  -fx-font-size: 15px;  -fx-font-weight: bold;  -fx-text-fill: whitesmoke;  -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1)");

        buttonNewGame.setOnAction(e -> {
            controller.newGame();
            JOptionPane.showMessageDialog(null, "Nouvelle Partie");

        });

        Label scoreLabel = new Label("Score");
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
        AnchorPane.setTopAnchor(border, 0.);

        // Images

        Image logo = new Image("/fr/polytech/tetris/ressources/logo.png");
        ImageView iv3 = new ImageView();
        iv3.setImage(logo);
        iv3.setFitHeight(100);
        iv3.setFitWidth(150);

        AnchorPane.setLeftAnchor(iv3, 25.);
        AnchorPane.setTopAnchor(iv3, 60.);


        Image im = new Image("/fr/polytech/tetris/ressources/bg.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(GRID_WIDTH * SIZE_CASE + 400, GRID_HEIGHT * SIZE_CASE, false, false, false, false);

        final AnchorPane root = new AnchorPane();
        root.getChildren().setAll(scoreLabel, scoreValue, buttonNewGame, border, iv3, previousPane);

        root.setBackground(new Background(new BackgroundImage(im, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize)));

        final Scene scene = new Scene(root, GRID_WIDTH * SIZE_CASE + 400, GRID_HEIGHT * SIZE_CASE);
        scene.setOnKeyPressed(controller);

        // Sons
        String sound = "src/fr/polytech/tetris/ressources/song.mp3";
        Media media = new Media(new File(sound).toURI().toString());

        MediaPlayer mp = new MediaPlayer(media);
        mp.play();


        // Primary Stage
        primaryStage.getIcons().add(logo);
        primaryStage.setTitle("Tetris");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * Initialisation de la Grid
     */
    private void initializeGrid(GridPane gridPane, int width, int height, Color gridColor, int sizeCase) {
        gridPane.setGridLinesVisible(true);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Rectangle rectangle = new Rectangle(sizeCase, sizeCase);
                rectangle.setFill(gridColor);
                rectangle.setStroke(gridColor);
                rectangle.setStrokeWidth(1);
                gridPane.add(rectangle, i, j);
            }
        }

        // Ajout des contraintes
        for (int i = 0; i < width; i++) {
            ColumnConstraints column = new ColumnConstraints(sizeCase);
            gridPane.getColumnConstraints().add(column);
        }
        for (int j = 0; j < height; j++) {
            RowConstraints row = new RowConstraints(sizeCase);
            gridPane.getRowConstraints().add(row);
        }
        gridPane.setMaxSize(sizeCase * width, sizeCase * height);
    }

    /**
     * Mise à jour de la grille
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        BoardTetris board = (BoardTetris) o;
        if (scoreValue != null) scoreValue.setText(board.getScore() + "");
        GridTetris grid = (GridTetris) board.getGrid();

        if (((GridTetris) board.getGrid()).isGameOver()) {
            controller.getBoard().newGame();
        }

        // On nettoie la grille
        for (int i = 0; i < PREVIOUS_GRID_HEIGHT; i++) {
            for (int j = 0; j < PREVIOUS_GRID_WIDTH; j++) {
                Node object = previousPane.getChildren().get(i + (j * PREVIOUS_GRID_HEIGHT) + 1);
                if (object instanceof Rectangle) {
                    Rectangle current = (Rectangle) previousPane.getChildren().get(i + (j * PREVIOUS_GRID_HEIGHT) + 1);
                    current.setFill(BLACK);
                }
            }
        }

        int[][] matrixPiece = grid.getNextPiece().getMatrix();
        for (int i = 0; i < matrixPiece.length; i++) {
            for (int j = 0; j < matrixPiece[0].length; j++) {
                Node object = previousPane.getChildren().get((i + 1) * 5 + j + 2);
                if (object instanceof Rectangle && matrixPiece[i][j] != 0) {
                    Rectangle current = (Rectangle) object;
                    current.setFill(grid.getNextPiece().getColor());
                }
            }
        }
    }
}
