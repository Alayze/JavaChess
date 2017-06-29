package Actors;

import Terrain.Cell;

import java.awt.*;


/**
 * Created by dimaer on 25/03/17.
 */
public class Pawn extends Piece {

    private Team.TEAMTYPE team;


    public Pawn(){}

    public Pawn(Cell cell, Team.TEAMTYPE team)
    {
        super(cell);
        setOrigin(new Point(-50,-75));
        Point position = new Point(getOrigin().x + cell.getSprite().getCenter().x,
                                  getOrigin().y + cell.getSprite().getCenter().y);
        getSprite().setPosition(position);
        this.team = team;
        setSprite(team.toString(),"Pawn");
    }


    @Override
    public void Move(Cell cell) {

    }

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
