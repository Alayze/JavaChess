package Actors;

import Terrain.Cell;

import java.awt.*;

/**
 * Created by dimaer on 27/03/17.
 */
public class Queen extends Piece{
    public Queen(Cell cell, Team.TEAMTYPE teamtype) {
        super(cell);
        setOrigin(new Point(-50,-118));
        Point position = new Point(getOrigin().x + cell.getSprite().getCenter().x,
                getOrigin().y + cell.getSprite().getCenter().y);
        getSprite().setPosition(position);
        setSprite(teamtype.toString(),"Queen");
    }

    @Override
    public void Move(Cell cell) {

    }

    @Override
    public void Die() {

    }
}
