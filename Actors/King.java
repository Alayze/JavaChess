package Actors;

import Terrain.Board;
import Terrain.Cell;

import java.awt.*;

/**
 * Created by dimaer on 27/03/17.
 */
public class King extends Piece {
    public King(Cell cell, Team.TEAMTYPE teamtype) {
        super(cell,teamtype);
        setSprite(teamtype.toString(),"King");
        getSprite().setOrigin(new Point(-50,-80));
        Point position = new Point(getSprite().getOrigin().x + cell.getSprite().getCenter().x,
                getSprite().getOrigin().y + cell.getSprite().getCenter().y);
        getSprite().setPosition(position);
        getSpriteOutline().setPosition(position);
        setSpriteOutline(teamtype.toString(),"King-outline");
    }

    @Override
    public void Move(Cell cell) {
        setCurrentCell(cell);
        Point position = new Point(getSprite().getOrigin().x + cell.getSprite().getCenter().x,
                getSprite().getOrigin().y + cell.getSprite().getCenter().y);
        getSprite().setPosition(position);
        getSpriteOutline().setPosition(position);
    }

    @Override
    public void Die() {

    }

}
