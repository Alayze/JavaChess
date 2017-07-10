package Terrain;

import Core.*;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Classe che descrive il tavolo da gioco
 * Created by dimaer on 27/03/17.
 */
public class Board extends GameObject implements WeatherObserver {
    ArrayList<Cell> cells;
    Cell convertedCells [][];
    Weather weather;
    private Point position;
    Point mouse;
    public Board(Point position,Weather weather) {
        super(position);
        mouse = new Point();
        setActive(false);
        this.position = position;
        this.weather = weather;
        weather.addWeatherObserver(this);//iscrizione di scachierra sulle notifiche de Weather
        cells = new ArrayList<>();
        convertedCells = new Cell[8][8];
        generateBoard(position);
        //System.out.println(weather.getWeather().toString());

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
                offsetY+=80; //E' bruto , bisogna incapsulare
                offsetX=0;
            }

            Point position = new Point(origin.x + offsetX,origin.y + offsetY);

            cells.add(new Cell(cartToIso(position),spriteType,weather.getWeather().toString()));
            cells.get(i).getSprite().setDepth(i);
            cells.get(i).setType(cellType);
            offsetX+=80; //E' bruto , bisogna incapsulare

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
    public Cell getCell(int n,char ch){

            //for(int y = 0;y<8;y++) {
              return  convertedCells[n][ch - 97];
            //}
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

    public ArrayList<Cell> getRow(int n){
        ArrayList<Cell> row = new ArrayList<>();
        for(int i = 0;i<8;i++){
            row.add(getCell(n,i));
        }
        return row;
    }
    public ArrayList<Cell> getColumn(int n){
        ArrayList<Cell> column = new ArrayList<>();
        for(int i = 0;i<8;i++){
            column.add(getCell(i,n));
        }
        return column;
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
    public void update(MouseEvent mouseEvent) {
        super.update(mouseEvent);
        mouse = new Point(mouseEvent.getX(),mouseEvent.getY());
        for (Cell c : cells){
            c.update(mouseEvent);
        }
    }

    @Override
    public void draw(Graphics graphics) {
        //graphics.drawString(" "+ cartToIso(mouse).x + " " + cartToIso(mouse).y,mouse.x,mouse.y);
        for (Cell c : cells){
            c.draw(graphics);
            //Log.getInstance().showOrigins(graphics,c.getSprite());
        }
    }
}
