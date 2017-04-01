package Actors;

import Components.Graphics.Drawable;
import Components.Event.MouseObserver;
import Components.Graphics.Sprite;
import Core.GameObject;
import Core.ResourceLoader;
import Terrain.Cell;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by dimaer on 25/03/17.
 */
public class Pawn extends GameObject implements Drawable, MouseObserver {

    private Sprite sprite;
    private Cell current_cell;

    public Pawn(Cell cell)
    {
        super(cell.getPosition());
        current_cell = cell;
        sprite = new Sprite(cell.getSprite().getCenter());

    }
    protected void setSprite(String id){
        sprite.setImage(ResourceLoader.getInstance().LoadResource(id,"sprite"));
    }
    /**
     * Metodo che sposta la piedina
     * @param cell cella della nuova posizione
     */
    public void Move(Cell cell){
        current_cell = cell;
        setPosition(current_cell.getSprite().getCenter());
    }

    /**
     * Metodo che torna cella corrente
     * @return L'oggetto di tipo Terrain.Cell
     */
    public Cell getCell()
    {
        return current_cell;
    }
    public Sprite getSprite()
    {
        return sprite;
    }
    @Override
    public void draw(Graphics graphics) {
        sprite.draw(graphics);
    }

    @Override
    public void update(MouseEvent mouseEvent, String message) {

    }
}
