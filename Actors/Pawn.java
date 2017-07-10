package Actors;

import Terrain.Board;
import Terrain.Cell;

import java.awt.*;


/**
 * Created by dimaer on 25/03/17.
 */
public class Pawn extends Piece {

    public Pawn(){}

    public Pawn(Cell cell, Team.TEAMTYPE teamtype)
    {
        super(cell,teamtype);

        getSprite().setOrigin(new Point(-50,-75));
        Point position = new Point(getSprite().getOrigin().x + cell.getSprite().getCenter().x,
                getSprite().getOrigin().y + cell.getSprite().getCenter().y);
        getSprite().setPosition(position);
        getSpriteOutline().setPosition(position);
        setSprite(teamtype.toString(),"Pawn");
        setSpriteOutline(teamtype.toString(),"Pawn-outline");
    }


    /*@Override
    public void Move(Cell cell) {

    }*/

    @Override
    public void Die() {

    }

    /* @Override
    public void draw(Graphics graphics) {
        sprite.draw(graphics);
    }

    @Override
    public void update(MouseEvent mouseEvent, String message) {

    }*/
}
