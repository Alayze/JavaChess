package Actors;

import Components.Graphics.Sprite;
import Core.GameObject;
import Core.ResourceLoader;
import Terrain.Board;
import Terrain.Cell;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by dimaer on 21/06/17.
 */
public abstract class Piece extends GameObject{


    private Sprite spriteOutline;

    private Cell currentCell;

    private Team.TEAMTYPE team;
    private Board board;
    /**
     *Costruttore di Piece
     */
    public Piece(){}

    /**
     *Costruttore di Piece
     * @param cell cella iniziale
     * @param team tipo di squadra
     */
    public Piece(Cell cell, Team.TEAMTYPE team, Board board){
        super(cell.getPosition());
        this.team = team;

        this.board = board;

        spriteOutline = new Sprite(cell.getPosition());
        spriteOutline.setVisibility(false);
        currentCell = cell;
        currentCell.setEmpty(false);

        getSprite().setDepth(getCurrentCell().getSprite().getDepth());
        getSpriteOutline().setDepth(getCurrentCell().getSprite().getDepth());

    }

    /**
     * Metodo che sposta la piedina
     * @param cell cella della nuova posizione
     */
    public void Move(Cell cell){

        setCurrentCell(cell);
        Point position = new Point(getSprite().getOrigin().x + cell.getSprite().getCenter().x,
                getSprite().getOrigin().y + cell.getSprite().getCenter().y);
        getSprite().setPosition(position);
        getSpriteOutline().setPosition(position);

        getSprite().setDepth(getCurrentCell().getSprite().getDepth());
        getSpriteOutline().setDepth(getCurrentCell().getSprite().getDepth());

    }
    public abstract ArrayList<Cell> getValidCells();
    /**
     *Metodo che imposta la cella corrente
     * @param currentCell cella da impostare
     */
    public void setCurrentCell(Cell currentCell) {
        this.currentCell.setEmpty(true);
        this.currentCell = currentCell;
        this.currentCell.setEmpty(false);
    }

    /**
     *Metodo che torna la cella corrente
     * @return cella corrente
     */
    public Cell getCurrentCell() {
        return currentCell;
    }

    /**
     * Metodo che ritorna oggetto della scachiera
     * @return scachiera
     */
    public Board getBoard() {
        return board;
    }

    /**
     *Metodo che torna tipo di squadra
     * @return tipo di squadra
     */

    public Team.TEAMTYPE getTeam() {
        return team;
    }

    /**
     *Metodo che imposta la nuova posizione
     * @param position nuova posizione
     */
    @Override
    public void setPosition(Point position) {
        super.setPosition(position);
        spriteOutline.setPosition(position);
    }

    /**
     *Metodo che carica sprite con pedina evidenziata
     * @param Team tipo di squadra
     * @param id il nome di sprite specificato in Resources.xml
     */
    protected void setSpriteOutline(String Team, String id){
        spriteOutline.setImage(ResourceLoader.getInstance().LoadSprite(Team,id));
    }

    /**
     *Metodo che torna sprite di pedina evidenziata
     * @return sprite di pedina evidenziata
     */
    public Sprite getSpriteOutline(){
        return spriteOutline;
    }

    /**
     *Metodo che disgena la pedina
     * @param graphics instanza di Graphics
     */
    @Override
    public void draw(Graphics graphics) {
        if(isVisibility()){
            super.draw(graphics);
            spriteOutline.draw(graphics);
        }
    }

    /**
     *Metodo che imposta la selezione
     * @param selected
     */
    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        spriteOutline.setVisibility(selected);
    }

    /**
     *Metodo che aggiorna la pedina
     * @param mouseEvent evento di Mouse
     */
    @Override
    public void update(MouseEvent mouseEvent) {
        super.update(mouseEvent);
        if(isSelected()){
            spriteOutline.setVisibility(true);
        }
        else {
            spriteOutline.setVisibility(false);
        }
    }
}
