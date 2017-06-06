package fr.polytech.morpion;

import fr.polytech.library.view.GPane;
import fr.polytech.morpion.controller.MorpionController;
import fr.polytech.morpion.model.BoardMorpion;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

import static javafx.scene.paint.Color.*;
import static javax.swing.JOptionPane.YES_NO_OPTION;

/**
 * Created by REZIGA on 14/05/2017.
 * Classe qui génère la vue du jeu Puissance 4
 */
public class MorpionView extends Application implements Observer {
    public final static int GRID_WIDTH = 3;
    public final static int GRID_HEIGHT = 3;
    private final static int SIZE_CASE = 100;
    private final static Color GRID_COLOR = TRANSPARENT;

    private MorpionController controller;

    public static void main(String[] args) {
        launch(args);
    }


    /**
     * Démarrage
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new MorpionController(GRID_WIDTH, GRID_HEIGHT, SIZE_CASE, GRID_COLOR);
        controller.getBoard().addObserver(this);

        GPane morpionPane = new GPane(controller.getBoard());
        morpionPane.setGridLinesVisible(true);
        initializeGrid(morpionPane, GRID_WIDTH, GRID_HEIGHT, GRID_COLOR, SIZE_CASE);
        morpionPane.setGridLinesVisible(false);

        BorderPane border = new BorderPane();
        border.setCenter(morpionPane);

        final Scene scene = new Scene(border, GRID_WIDTH * SIZE_CASE, GRID_HEIGHT * SIZE_CASE);
        scene.setOnMouseClicked(controller);

        primaryStage.setTitle("Morpion");
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
                rectangle.setStroke(BLACK);
                rectangle.setStrokeWidth(2);
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
     * Mise à jour de la vue Puissance 4
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        BoardMorpion board = (BoardMorpion) o;
        if (board.gameOver()) {
            String message = "Match nul !";
            if (board.getGrid().getWinnerColor() == RED) message = "Joueur rouge gagne !";
            if (board.getGrid().getWinnerColor() == BLUE) message = "Joueur bleu gagne !";
            int dialogResult = JOptionPane.showConfirmDialog(null, message + " Voulez-vous rejouer ?", "Fin de la partie", YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                controller.newGame();
            } else {
                Platform.exit();
                System.exit(0);
            }
        }
    }
}
