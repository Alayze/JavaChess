package Actors;

import Terrain.Board;
import Terrain.Cell;

import java.awt.*;
import java.util.ArrayList;


/**
 * Created by dimaer on 25/03/17.
 */
public class Pawn extends Piece {

    public Pawn(){}

    /**
     *
     * Costruttore di Pawn
     * @param cell cella iniziale
     * @param teamtype tipo di squadra
     */
    public Pawn(Cell cell, Team.TEAMTYPE teamtype,Board board)
    {
        super(cell,teamtype,board);

        getSprite().setOrigin(new Point(-50,-75));
        Point position = new Point(getSprite().getOrigin().x + cell.getSprite().getCenter().x,
                getSprite().getOrigin().y + cell.getSprite().getCenter().y);
        getSprite().setPosition(position);
        getSpriteOutline().setPosition(position);
        setSprite(teamtype.toString(),"Pawn");
        setSpriteOutline(teamtype.toString(),"Pawn-outline");
    }

    @Override
    public ArrayList<Cell> getValidCells() {

        ArrayList<Cell> cells = new ArrayList<>();

        int currentColumn = getCurrentCell().getCoord().y;
        int currentRow = getCurrentCell().getCoord().x;

        boolean breakCellUp = false;

        if(getTeam()== Team.TEAMTYPE.Blue) {
            if (currentRow == 6) {
                if(getBoard().getColumn(currentColumn).get(currentRow - 1).isEmpty() && !breakCellUp){
                    cells.add(getBoard().getColumn(currentColumn).get(currentRow - 1));
                    if(getBoard().getColumn(currentColumn).get(currentRow - 2).isEmpty())
                        getBoard().getColumn(currentColumn).get(currentRow - 2);
                }
            } else
            if(getBoard().getColumn(currentColumn).get(currentRow - 1).isEmpty())
                cells.add(getBoard().getColumn(currentColumn).get(currentRow - 1));
        } else {
            if(currentRow == 1 ){
                cells.add(getBoard().getColumn(currentColumn).get(currentRow + 1));
                cells.add(getBoard().getColumn(currentColumn).get(currentRow + 2));
            } else
                cells.add(getBoard().getColumn(currentColumn).get(currentRow + 1));
        }
        return cells;
    }
}
