package Actors;

import Terrain.Board;
import Terrain.Cell;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by dimaer on 27/03/17.
 */
public class Knight extends Piece {
    /**
     *
     * Costruttore di Knight
     * @param cell cella iniziale
     * @param teamtype tipo di squadrae
     */
    public Knight(Cell cell, Team.TEAMTYPE teamtype,Board board) {
        super(cell,teamtype,board);

        getSprite().setOrigin(new Point(-50,-118));
        Point position = new Point(getSprite().getOrigin().x + cell.getSprite().getCenter().x,
                getSprite().getOrigin().y + cell.getSprite().getCenter().y);
        getSprite().setPosition(position);
        getSpriteOutline().setPosition(position);
        setSprite(teamtype.toString(),"Knight");

        setSpriteOutline(teamtype.toString(),"Knight-outline");

    }
    @Override
    public ArrayList<Cell> getValidCells(){

        ArrayList<Cell> cells = new ArrayList<>();

        int currentColumn = getCurrentCell().getCoord().y;
        int currentRow = getCurrentCell().getCoord().x;

        if(currentColumn+1<8 && currentRow+2<8)
            cells.add(getBoard().getColumn(currentColumn + 1).get(currentRow + 2));

        if(currentColumn+2<8 && currentRow+1<8)
            cells.add(getBoard().getColumn(currentColumn+2).get(currentRow+1));
        if(currentColumn-1>=0 && currentRow+2<8)
            cells.add(getBoard().getColumn(currentColumn-1).get(currentRow+2));
        if(currentColumn-2>=0 && currentRow+1<8)
            cells.add(getBoard().getColumn(currentColumn-2).get(currentRow+1));
        if(currentColumn+2<8 && currentRow-1>=0)
            cells.add(getBoard().getColumn(currentColumn+2).get(currentRow-1));
        if(currentColumn+1<8 && currentRow-2>=0)
            cells.add(getBoard().getColumn(currentColumn+1).get(currentRow-2));
        if(currentColumn-2>=0 && currentRow-1>=0)
            cells.add(getBoard().getColumn(currentColumn-2).get(currentRow-1));
        if(currentColumn-1>=0 && currentRow-2>=0)
            cells.add(getBoard().getColumn(currentColumn-1).get(currentRow-2));

        return cells;
    }
}
