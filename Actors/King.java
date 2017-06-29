package Actors;

import Terrain.Cell;

import java.awt.*;

/**
 * Created by dimaer on 27/03/17.
 */
public class King extends Piece {
    public King(Cell cell, Team.TEAMTYPE teamtype) {
        super(cell);
        setSprite(teamtype.toString(),"King");
        setOrigin(new Point(-50,-80));
        Point position = new Point(getOrigin().x + cell.getSprite().getCenter().x,
                getOrigin().y + cell.getSprite().getCenter().y);
        getSprite().setPosition(position);
    }

    @Override
    public void Move(Cell cell) {

    }

    @Override
    public void Die() {

    }
}
