package Actors;

import Terrain.Board;
import Terrain.Cell;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by dimaer on 27/03/17.
 */
public class Queen extends Piece{
    /**
     *
     * Costruttore di Queen
     * @param cell cella iniziale
     * @param teamtype tipo di squadra
     */
    public Queen(Cell cell, Team.TEAMTYPE teamtype,Board board) {
        super(cell,teamtype,board);
        getSprite().setOrigin(new Point(-50,-118));
        Point position = new Point(getSprite().getOrigin().x + cell.getSprite().getCenter().x,
                getSprite().getOrigin().y + cell.getSprite().getCenter().y);
        getSprite().setPosition(position);
        getSpriteOutline().setPosition(position);
        setSprite(teamtype.toString(),"Queen");
        setSpriteOutline(teamtype.toString(),"Queen-outline");
    }
    /*public boolean checkCell(Cell cell){

    }*/

    @Override
    public ArrayList<Cell> getValidCells() {

        ArrayList<Cell> cells = new ArrayList<>();

        int currentColumn = getCurrentCell().getCoord().y;
        int currentRow = getCurrentCell().getCoord().x;

        boolean breakCellUp = false;
        boolean breakCellDown = false;
        boolean breakCellLeft = false;
        boolean breakCellRight = false;
        boolean breakCellUpLeft = false;
        boolean breakCellUpRight = false;
        boolean breakCellDownLeft = false;
        boolean breakCellDownRight = false;

        Cell cellUp,cellDown,cellLeft,cellRight,cellUpRight,cellUpLeft,cellDownRight,cellDownLeft;

        for(int i = 1;i<8;i++){
            if(currentRow - i>=0) {

                cellUp = getBoard().getColumn(currentColumn).get(currentRow - i);

                if (cellUp.isEmpty() && !breakCellUp)
                    cells.add(cellUp);
                else{
                    if(!breakCellUp){
                        cells.add(cellUp);
                        breakCellUp = true;
                    }

                }

            }
            if(currentColumn - i>=0){
                cellLeft = getBoard().getColumn(currentColumn-i).get(currentRow);
                if(cellLeft.isEmpty() && !breakCellLeft)
                    cells.add(cellLeft);
                else{
                    if(!breakCellLeft){
                        cells.add(cellLeft);
                        breakCellLeft = true;
                    }

                }
            }
        }
        for(int i = 1;i<8;i++){
            if(currentRow + i<8) {

                cellDown = getBoard().getColumn(currentColumn).get(currentRow + i);

                if (cellDown.isEmpty() && !breakCellDown)
                    cells.add(cellDown);
                else{
                    if(!breakCellDown){
                        cells.add(cellDown);
                        breakCellDown = true;
                    }
                }
            }

            if(currentColumn + i<8){

                cellRight = getBoard().getColumn(currentColumn+i).get(currentRow);

                if(cellRight.isEmpty() && !breakCellRight)
                    cells.add(cellRight);
                else{
                    if(!breakCellRight){
                        cells.add(cellRight);
                        breakCellRight = true;
                    }
                }
            }
        }

        for(int i = 1;i<8;i++){

            if(currentColumn+i<8 && (currentRow - i)>=0) {

                cellUpRight = getBoard().getColumn(currentColumn + i).get(currentRow - i);

                if (cellUpRight.isEmpty() && !breakCellUpRight)
                    cells.add(cellUpRight);
                else {
                    if(!breakCellUpRight){
                        cells.add(cellUpRight);
                        breakCellUpRight = true;
                    }
                }
            }

            if(currentColumn - i>=0 && (currentRow - i)>=0) {

                cellUpLeft = getBoard().getColumn(currentColumn - i).get(currentRow - i);

                if (cellUpLeft.isEmpty() && !breakCellUpLeft)
                    cells.add(cellUpLeft);
                else{
                    if(!breakCellUpLeft){
                        cells.add(cellUpLeft);
                        breakCellUpLeft = true;
                    }

                }
            }
        }

        for(int i = 1;i<8;i++){

            if(currentColumn + i<8 && (currentRow + i)<8) {

                cellDownRight = getBoard().getColumn(currentColumn + i).get(currentRow + i);

                if (cellDownRight.isEmpty() && !breakCellDownRight)
                    cells.add(cellDownRight);
                else{
                    if(!breakCellDownRight){
                        cells.add(cellDownRight);
                        breakCellDownRight = true;
                    }

                }
            }

            if(currentColumn - i>=0 && (currentRow + i)<8) {

                cellDownLeft = getBoard().getColumn(currentColumn - i).get(currentRow + i);

                if (cellDownLeft.isEmpty() && !breakCellDownLeft)
                    cells.add(cellDownLeft);
                else{
                    if(!breakCellDownLeft){
                        cells.add(cellDownLeft);
                        breakCellDownLeft = true;
                    }
                }

            }
        }
        return cells;
    }
}
