package Terrain;

import Components.Graphics.Drawable;
import Core.GameObject;
import Core.ResourceLoader;
import Core.Screen;
import Core.WheaterObserver;


import java.awt.*;
import java.util.ArrayList;

/**
 * Classe che descrive il tavolo da gioco
 * Created by dimaer on 27/03/17.
 */
public class Board extends GameObject implements Drawable, WheaterObserver {
    ArrayList<Cell> cells;
    Screen screen;
    public Board(Point position,Screen screen) {
        super(position);
        cells = new ArrayList<>();
        this.screen = screen;
        generateBoard(position);
    }

    @Override
    public void setSprite() {

    }

    /**
     * Metodo che costruisce il tavolo di gioco
     */
    private void generateBoard(Point origin) {
        int offsetX = 0;
        int offsetY = 0;
        //Template e' un livelo preimpostato
        /*String template = "X0X0X0X0" +
                          "0X0X0X0X" +
                          "X0X0X0X0" +
                          "0X0X0X0X" +
                          "X0X0X0X0" +
                          "0X0X0X0X" +
                          "X0X0X0X0" +
                          "0X0X0X0X";*/
        String template = ResourceLoader.getInstance().loadLevel("2");
        String spriteType = "";
        Cell.Type cellType;

        for(int i = 0;i<64;i++)
        {
            /*if(template.charAt(i)=='X'){
                spriteType = "Earth_Rock_1";
                cellType = Cell.Type.TYPE1;
            }else{
                spriteType = "Earth_Grass_1";
                cellType = Cell.Type.TYPE2;
            }*/

            spriteType = ResourceLoader.getInstance().checkAnnotation(template.charAt(i));
            cellType = Cell.Type.TYPE1;
            if(i % 8 == 0)
            {
                offsetY+=43; //E' bruto , bisogna incapsulare
                offsetX=0;
            }

            Point position = new Point(origin.x + offsetX,origin.y + offsetY);

            cells.add(new Cell(cartToIso(position),spriteType));
            cells.get(i).getSprite().setDepth(i);
            cells.get(i).setType(cellType);

            offsetX+=43; //E' bruto , bisogna incapsulare
        }
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

    /**
     * Metodo che torna l'insieme di celle
     * @return ArrayList<Cell> l'insieme di celle che compongono la scachiera
     */
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
