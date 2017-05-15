package fr.polytech.library.model;


import javafx.scene.paint.Color;

/**
 * Created by REZIGA on 14/05/2017.
 */
public class Button {
    int width;
    int height;
    Color color;
    String text;

    public Button(int width, int height, Color color, String text) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.text = text;
    }
}
