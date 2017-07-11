package Actors;

import Terrain.Board;
import Terrain.Cell;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by dimaer on 27/03/17.
 */
public class Bishop extends Piece {
    /**
     *
     * Costruttore di Bishop
     * @param cell cella iniziale
     * @param teamtype tipo di squadra
     */
    public Bishop(Cell cell, Team.TEAMTYPE teamtype,Board board) {
        super(cell,teamtype,board);

        getSprite().setOrigin(new Point(-35,-135));
        Point position = new Point(getSprite().getOrigin().x + cell.getSprite().getCenter().x,
                getSprite().getOrigin().y + cell.getSprite().getCenter().y);
        getSprite().setPosition(position);
        getSpriteOutline().setPosition(position);
        setSprite(teamtype.toString(),"Bishop");
        setSpriteOutline(teamtype.toString(),"Bishop-outline");
    }

    @Override
    public ArrayList<Cell> getValidCells() {

        ArrayList<Cell> cells = new ArrayList<>();

        int currentColumn = getCurrentCell().getCoord().y;
        int currentRow = getCurrentCell().getCoord().x;

        boolean breakCellUpRight = false;
        boolean breakCellUpLeft = false;
        boolean  breakCellDownRight = false;
        boolean breakCellDownLeft = false;

        for(int i = 1;i<8;i++) {
            if (currentColumn + i < 8 && (currentRow - i) >= 0) {
                if ((getBoard().getColumn(currentColumn + i).get(currentRow - i)).isEmpty() && !breakCellUpRight)
                    cells.add(getBoard().getColumn(currentColumn + i).get(currentRow - i));
                else {
                    if (!breakCellUpRight) {
                        cells.add(getBoard().getColumn(currentColumn + i).get(currentRow - i));
                        breakCellUpRight = true;
                    }

                }
            }

            if (currentColumn - i >= 0 && (currentRow - i) >= 0) {
                if ((getBoard().getColumn(currentColumn - i).get(currentRow - i)).isEmpty() && !breakCellUpLeft)
                    cells.add(getBoard().getColumn(currentColumn - i).get(currentRow - i));
                else {
                    if (!breakCellUpLeft) {
                        cells.add(getBoard().getColumn(currentColumn - i).get(currentRow - i));
                        breakCellUpLeft = true;
                    }
                }
            }
        }

        for(int i = 1;i<8;i++){

            if(currentColumn + i<8 && (currentRow +i)<8) {
                if (getBoard().getColumn(currentColumn + i).get(currentRow + i).isEmpty() && !breakCellDownRight)
                    cells.add(getBoard().getColumn(currentColumn + i).get(currentRow + i));
                else {
                    if (!breakCellDownRight) {
                        cells.add(getBoard().getColumn(currentColumn + i).get(currentRow + i));
                        breakCellDownRight = true;
                    }

                }
            }

            if(currentColumn - i>=0 && (currentRow + i)<8) {
                if (getBoard().getColumn(currentColumn - i).get(currentRow + i).isEmpty() && !breakCellDownLeft)
                    cells.add(getBoard().getColumn(currentColumn - i).get(currentRow + i));
                else {
                    if (!breakCellDownLeft) {
                        cells.add(getBoard().getColumn(currentColumn - i).get(currentRow + i));
                        breakCellDownLeft = true;
                    }
                }
            }
        }
        return cells;
    }
}
