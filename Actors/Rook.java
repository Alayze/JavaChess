package Actors;

import Terrain.Board;
import Terrain.Cell;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by dimaer on 27/03/17.
 */
public class Rook extends Piece {
    /**
     * Costruttore di Rook
     * @param cell cella iniziale
     * @param teamtype tipo di squadra
     */
    public Rook(Cell cell, Team.TEAMTYPE teamtype,Board board) {
        super(cell,teamtype,board);
        getSprite().setOrigin(new Point(-45,-145));
        Point position = new Point(getSprite().getOrigin().x + cell.getSprite().getCenter().x,
                getSprite().getOrigin().y + cell.getSprite().getCenter().y);
        getSprite().setPosition(position);
        getSpriteOutline().setPosition(position);
        setSprite(teamtype.toString(),"Rook");
        setSpriteOutline(teamtype.toString(),"Rook-outline");
    }

    @Override
    public ArrayList<Cell> getValidCells() {

        ArrayList<Cell> cells = new ArrayList<>();

        int currentColumn = getCurrentCell().getCoord().y;
        int currentRow = getCurrentCell().getCoord().x;

        boolean breakCellUp = false;
        boolean breakCellLeft = false;
        boolean breakCellDown = false;
        boolean breakCellRight = false;

        for(int i = 1;i<8;i++){
            if(currentRow-i>=0) {
                if (getBoard().getColumn(currentColumn).get(currentRow - i) .isEmpty() && !breakCellUp)
                    cells.add(getBoard().getColumn(currentColumn).get(currentRow - i));
                else{
                    if(!breakCellUp){
                        cells.add(getBoard().getColumn(currentColumn).get(currentRow - i));
                        breakCellUp = true;
                    }

                }

            }
            if(currentColumn-i>=0){
                if(getBoard().getColumn(currentColumn-i).get(currentRow).isEmpty() && !breakCellLeft)
                    cells.add(getBoard().getColumn(currentColumn-i).get(currentRow));
                else {
                    if(!breakCellLeft){
                        cells.add(getBoard().getColumn(currentColumn-i).get(currentRow));
                        breakCellLeft = true;
                    }
                }
            }
        }
        for(int i = 1;i<8;i++){
            if(currentRow+i<8) {
                if (getBoard().getColumn(currentColumn).get(currentRow + i).isEmpty() && !breakCellDown)
                    cells.add(getBoard().getColumn(currentColumn).get(currentRow + i));
                else{
                    if(!breakCellDown){
                        cells.add(getBoard().getColumn(currentColumn).get(currentRow + i));
                        breakCellDown = true;
                    }
                }

            }
            if(currentColumn+i<8){
                if(getBoard().getColumn(currentColumn+i).get(currentRow).isEmpty() && !breakCellRight)
                    cells.add(getBoard().getColumn(currentColumn+i).get(currentRow));
                else{
                    if(!breakCellRight){
                        cells.add(getBoard().getColumn(currentColumn+i).get(currentRow));
                        breakCellRight = true;
                    }
                }

            }
        }
        return cells;
    }
}
