package Actors;

import Components.Event.MouseObserver;
import Components.Graphics.Drawable;
import Components.Graphics.Sprite;
import Core.GameObject;
import Core.ResourceLoader;
import Terrain.Cell;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by dimaer on 21/06/17.
 */
public abstract class Piece extends GameObject implements Drawable,MouseObserver {

    private Sprite sprite;
    private Point origin;
    private Cell current_cell;

    public Piece(){}
    public Piece(Cell cell){
        sprite = new Sprite(cell.getPosition());
        current_cell = cell;
    }

    /**
     * Metodo che sposta la piedina
     * @param cell cella della nuova posizione
     */
    public abstract void Move(Cell cell);
    public abstract void Die();

    public void setOrigin(Point vector){
        origin = vector;
    }

    public Point getOrigin(){
        return origin;
    }

    public Cell getCell()
    {
        return current_cell;
    }

    public Sprite getSprite()
    {
        return sprite;
    }

    protected void setSprite(String Team,String id){
        sprite.setImage(ResourceLoader.getInstance().LoadSprite(Team,id));
    }


    @Override
    public void draw(Graphics graphics) {
        sprite.draw(graphics);
    }

    @Override
    public void update(MouseEvent mouseEvent, String message) {

    }
}
