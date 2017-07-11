package Actors;

import Terrain.Board;
import Terrain.Cell;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by dimaer on 27/03/17.
 */
public class King extends Piece {
    /**
     *
     * Costruttore di King
     * @param cell cella iniziale
     * @param teamtype tipo di squadra
     */
    public King(Cell cell, Team.TEAMTYPE teamtype,Board board) {
        super(cell,teamtype,board);
        setSprite(teamtype.toString(),"King");
        getSprite().setOrigin(new Point(-50,-80));
        Point position = new Point(getSprite().getOrigin().x + cell.getSprite().getCenter().x,
                getSprite().getOrigin().y + cell.getSprite().getCenter().y);
        getSprite().setPosition(position);
        getSpriteOutline().setPosition(position);
        setSpriteOutline(teamtype.toString(),"King-outline");
    }

    @Override
    public ArrayList<Cell> getValidCells() {

        ArrayList<Cell> cells = new ArrayList<>();

        int currentColumn = getCurrentCell().getCoord().y;
        int currentRow = getCurrentCell().getCoord().x;

        if(currentColumn+1<8 && currentRow-1>=0){
            cells.add(getBoard().getColumn(currentColumn+1).get(currentRow-1));
        }
        if(currentRow-1>=0) {
            cells.add(getBoard().getColumn(currentColumn).get(currentRow - 1));
        }

        if(currentColumn-1<8 && currentRow+1<8){
            cells.add(getBoard().getColumn(currentColumn - 1).get(currentRow+1));
        }
        if(currentRow + 1 < 8)
            cells.add(getBoard().getColumn(currentColumn).get(currentRow + 1));

        if(currentColumn + 1 < 8)
            cells.add(getBoard().getColumn(currentColumn + 1).get(currentRow));

        if(currentColumn - 1>=0)
            cells.add(getBoard().getColumn(currentColumn - 1).get(currentRow));

        if(currentColumn - 1>=0 && currentRow - 1>=0)
            cells.add(getBoard().getColumn(currentColumn - 1).get(currentRow-1));

        if(currentColumn + 1 < 8 && currentRow + 1 < 8)
            cells.add(getBoard().getColumn(currentColumn + 1).get(currentRow+1));

        return cells;
    }
}
