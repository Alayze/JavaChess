package Terrain;

import Components.Graphics.Drawable;
import Core.GameObject;

import java.awt.*;
import java.util.ArrayList;

/**
 * Classe che descrive il tavolo da gioco
 * Created by dimaer on 27/03/17.
 */
public class Board extends GameObject implements Drawable {
    ArrayList<Cell> cells;
    public Board(Point position) {
        super(position);
        cells = new ArrayList<>();
        composeBoard();
    }

    /**
     * Metodo che costruisce il tavolo di gioco
     */
    private void composeBoard() {

    }
    @Override
    public void draw(Graphics graphics) {
        for (Cell c : cells){
            c.draw(graphics);
        }

    }
}
