package Terrain;

import Components.Graphics.Drawable;
import Core.*;


import java.awt.*;
import java.util.ArrayList;

/**
 * Classe che descrive il tavolo da gioco
 * Created by dimaer on 27/03/17.
 */
public class Board extends GameObject implements Drawable, WeatherObserver {
    ArrayList<Cell> cells;
    Cell convertedCells [][];
    Weather weather;
    private Point position;

    public Board(Point position,Weather weather) {
        super(position);
        this.position = position;
        this.weather = weather;
        weather.addWeatherObserver(this);//iscrizione di scachierra sulle notifiche de Weather
        cells = new ArrayList<>();
        convertedCells = new Cell[8][8];
        generateBoard(position);
        System.out.println(weather.getWeather().toString());

    }
    /**Metodo che aggiorna oservatori di Weather*/
    @Override
    public void setSprite() {
       for (Cell c : cells){
            c.getSprite().setImage(ResourceLoader.getInstance().LoadTile(weather.getWeather().toString(),c.getSpriteType()));
        }

    }
    /**
     * Metodo che costruisce il tavolo di gioco
     */
    private void generateBoard(Point origin) {
        int offsetX = 0;
        int offsetY = 0;

        String template = ResourceLoader.getInstance().loadLevel("2");
        String spriteType = "";
        Cell.Type cellType;

        for(int i = 0;i<64;i++)
        {

            spriteType = ResourceLoader.getInstance().checkAnnotation(template.charAt(i));
            cellType = Cell.Type.TYPE1;
            if(i % 8 == 0)
            {
                offsetY+=43; //E' bruto , bisogna incapsulare
                offsetX=0;
            }

            Point position = new Point(origin.x + offsetX,origin.y + offsetY);

            cells.add(new Cell(cartToIso(position),spriteType,weather.getWeather().toString()));
            cells.get(i).getSprite().setDepth(i);
            cells.get(i).setType(cellType);
            offsetX+=43; //E' bruto , bisogna incapsulare

        }
        convertArray();
    }

    /**
     * Funzione che converte Array in array bidimensionale
     */
    private void convertArray(){
        int count = 0;
        for (int x = 0;x<8;x++){
            for(int y = 0;y<8;y++){
                //System.out.println(count);
                convertedCells[x][y] = cells.get(count);
                count++;
            }
        }
    }

    /**
     * Funzione che torna la cella di scachiera che e' contenuta nell array bidimensionale
     * @param x posizione orizzontale
     * @param y posizione verticale
     * @return cella di scachiera
     */
    public Cell getCell(int x ,int y){
        if(x <=7 || y<=7)
            return convertedCells[x][y];
        else
            return null;
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
