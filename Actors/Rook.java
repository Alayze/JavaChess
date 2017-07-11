package Actors;

import Terrain.Board;
import Terrain.Cell;

import java.awt.*;

/**
 * Created by dimaer on 27/03/17.
 */
public class Rook extends Piece {
    /**
     * Costruttore di Rook
     * @param cell cella iniziale
     * @param teamtype tipo di squadra
     */
    public Rook(Cell cell, Team.TEAMTYPE teamtype) {
        super(cell,teamtype);
        getSprite().setOrigin(new Point(-45,-145));
        Point position = new Point(getSprite().getOrigin().x + cell.getSprite().getCenter().x,
                getSprite().getOrigin().y + cell.getSprite().getCenter().y);
        getSprite().setPosition(position);
        getSpriteOutline().setPosition(position);
        setSprite(teamtype.toString(),"Rook");
        setSpriteOutline(teamtype.toString(),"Rook-outline");
    }

}
