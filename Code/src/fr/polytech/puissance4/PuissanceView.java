package fr.polytech.puissance4;

import fr.polytech.library.view.GPane;
import fr.polytech.puissance4.controller.PuissanceController;
import fr.polytech.puissance4.model.BoardPuissance;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
public class PuissanceView extends Application implements Observer {
    private final static int GRID_WIDTH = 7;
    private final static int GRID_HEIGHT = 7;
    private final static int SIZE_CASE = 100;
    private final static Color GRID_COLOR = BLACK;

    private PuissanceController controller;

    public static void main(String[] args) {
        launch(args);
    }


    /**
     * Démarrage
     *
     * @param primaryStage : conteneur de l'application
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new PuissanceController(GRID_WIDTH, GRID_HEIGHT, SIZE_CASE, GRID_COLOR);
        controller.getBoard().addObserver(this);

        GPane puissancePane = new GPane(controller.getBoard());
        puissancePane.setBackground(new Background(new BackgroundFill(BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        initializeGrid(puissancePane, GRID_WIDTH, GRID_HEIGHT, GRID_COLOR, SIZE_CASE);
        puissancePane.setGridLinesVisible(false);

        BorderPane border = new BorderPane();
        border.setCenter(puissancePane);

        final Scene scene = new Scene(border, GRID_WIDTH * SIZE_CASE, GRID_HEIGHT * SIZE_CASE);
        scene.setOnKeyPressed(controller);

        primaryStage.setTitle("Puissance 4");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        controller.initiliazeFirstPiece();
    }

    /**
     * Initialisation de la Grid
     */
    private void initializeGrid(GridPane gridPane, int width, int height, Color gridColor, int sizeCase) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Circle circle = new Circle(sizeCase / 2 - 1, gridColor);
                circle.setFill(j == 0 ? BLUE : BLACK);
                gridPane.add(circle, i, j);
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
     * @param o : observable
     * @param arg : argument
     */
    @Override
    public void update(Observable o, Object arg) {
        BoardPuissance board = (BoardPuissance) o;
        if (board.gameOver()) {
            String message = "Match nul !";
            if (board.getGrid().getWinnerColor() == RED) message = "Joueur rouge gagne !";
            if (board.getGrid().getWinnerColor() == ORANGE) message = "Joueur orange gagne !";
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
