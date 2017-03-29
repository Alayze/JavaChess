package Terrain;

import Components.Graphics.Drawable;
import Core.GameObject;
import Core.Screen;


import java.awt.*;
import java.util.ArrayList;

/**
 * Classe che descrive il tavolo da gioco
 * Created by dimaer on 27/03/17.
 */
public class Board extends GameObject implements Drawable {
    ArrayList<Cell> cells;
    Screen screen;
    public Board(Point position,Screen screen) {
        super(position);
        cells = new ArrayList<>();
        this.screen = screen;
        generateBoard(position);
    }

    /**
     * Metodo che costruisce il tavolo di gioco
     */
    private void generateBoard(Point origin) {
        int offsetX = 0;
        int offsetY = 0;

        String template = "X0X0X0X0" +
                          "0X0X0X0X" +
                          "X0X0X0X0" +
                          "0X0X0X0X" +
                          "X0X0X0X0" +
                          "0X0X0X0X" +
                          "X0X0X0X0" +
                          "0X0X0X0X";
        String spriteType;
        for(int i = 0;i<64;i++)
        {
            if(template.charAt(i)=='X'){
                spriteType = "Earth_Rock_1";
            }else{
                spriteType = "Earth_Grass_1";
            }
            if(i % 8 == 0)
            {
                offsetY+=43;
                offsetX=0;
            }

            Point position = new Point(origin.x + offsetX,origin.y + offsetY);

            cells.add(new Cell(cartToIso(position),spriteType));
            cells.get(i).getSprite().setDepth(i);

            offsetX+=43;
        }
           // cells = inverseMatrix();
    }

    private ArrayList<Cell> inverseMatrix()
    {
        ArrayList<Cell> inverseCells = new ArrayList<>();
        for(int i = 7;i<=cells.size();i+=8)
        {
            System.out.print(i + "\n");

            for(int x = 0;x<8;x++)
            {
                inverseCells.add(cells.get(i-x));
            }
        }
        return inverseCells;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }
    /*private ArrayList<Cell> inverseMatrix()
    {
        ArrayList<Cell> inverseCells = new ArrayList<>();
        for(int i = 0;i<cells.size();i++){
            if()
        }

    }*/
    /**
     * Metodo che converte le coordinate cartesiane nelle coordinate isometriche
     * @param vector vettore da convertire
     * @return vettore convertito
     */
    private Point cartToIso(Point vector){
        return new Point(vector.x-vector.y,(vector.x+vector.y)/2);
    }
    @Override
    public void draw(Graphics graphics) {
        for (Cell c : cells){
            c.draw(graphics);
        }

    }
}
