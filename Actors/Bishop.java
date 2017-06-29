package Actors;

import Components.Event.MouseObserver;
import Terrain.Cell;

import java.awt.*;

/**
 * Created by dimaer on 27/03/17.
 */
public class Bishop extends Piece {
    public Bishop(Cell cell, Team.TEAMTYPE teamtype) {
        super(cell);
        setOrigin(new Point(-35,-135));
        Point position = new Point(getOrigin().x + cell.getSprite().getCenter().x,
                getOrigin().y + cell.getSprite().getCenter().y);
        getSprite().setPosition(position);
        setSprite(teamtype.toString(),"Bishop");
    }

    @Override
    public void Move(Cell cell) {

    }

    @Override
    public void Die() {

    }
}
