package Scenes;

import Actors.*;
import Components.Event.MouseObserver;
import Components.Graphics.Drawable;
import Components.Graphics.Gui.Label;
import Core.GameObject;
import Core.Scene;
import Core.Weather;
import Terrain.Board;
import Terrain.Cell;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by dimaer on 17/05/17.
 */
public final class Game extends Scene {

    Board board;
    Team teamRed;
    Team teamBlue;
    Label labelTurn;
    Label labelSeason;
    MouseObserver selectedSprite;
    Piece selectedPiece;
    //Cell selectedCell;
    Turn currentTurn;
    enum Turn{
        Blue,Red
    }

    @Override
    public void Init() {


        currentTurn = Turn.Blue;
        setSceneType(SCENE_TYPE.RUNNED_GAME);
        board = new Board(new Point(390,-300),new Weather(Weather.WEATHER_TYPE.Winter));

        labelTurn = new Label("Turno di giocatore :",new Point(100,100));
        labelSeason = new Label("Stagione :",new Point(400,50));

        addElement(board);
        for(Cell cell : board.getCells())
            addElement(cell);

        for (int i = 0 ;i<8;i++){
            Pawn pawn = new Pawn(board.getCell(6,(char)('a'+i)), Team.TEAMTYPE.Blue);
            addElement(pawn);
        }

        addElement(new Rook(board.getCell(7,'a'), Team.TEAMTYPE.Blue));
        addElement(new Knight(board.getCell(7,'b'), Team.TEAMTYPE.Blue));
        addElement(new Bishop(board.getCell(7,'c'), Team.TEAMTYPE.Blue));
        addElement(new Queen(board.getCell(7,'d'), Team.TEAMTYPE.Blue));
        addElement(new Bishop(board.getCell(7,'f'), Team.TEAMTYPE.Blue));
        addElement(new Knight(board.getCell(7,'g'), Team.TEAMTYPE.Blue));

        addElement(new King(board.getCell(7,'e'), Team.TEAMTYPE.Blue));

        addElement(new Rook(board.getCell(7,'h'), Team.TEAMTYPE.Blue));



        addElement(new Rook(board.getCell(0,'a'), Team.TEAMTYPE.Red));
        addElement(new Knight(board.getCell(0,'b'), Team.TEAMTYPE.Red));
        addElement(new Bishop(board.getCell(0,'c'), Team.TEAMTYPE.Red));
        addElement(new Queen(board.getCell(0,'d'), Team.TEAMTYPE.Red));
        addElement(new Bishop(board.getCell(0,'f'), Team.TEAMTYPE.Red));
        addElement(new Knight(board.getCell(0,'g'), Team.TEAMTYPE.Red));

        addElement(new King(board.getCell(0,'e'), Team.TEAMTYPE.Red));

        addElement(new Rook(board.getCell(0,'h'), Team.TEAMTYPE.Red));

        for(int i = 0;i<8;i++){
            Pawn pawn = new Pawn(board.getCell(1,(char)('a' + i)), Team.TEAMTYPE.Red);
            addElement(pawn);
        }

        addElement(labelTurn);
        addElement(labelSeason);


    }
    public MouseObserver getSelected(MouseEvent mouseEvent){

        ArrayList<MouseObserver> gameObjects = new ArrayList<>();

        Point mouse = new Point(mouseEvent.getX(),mouseEvent.getY());

        if(mouseEvent.getID()==MouseEvent.MOUSE_CLICKED) {
            for (MouseObserver obs : getObservers()) {
                GameObject gm = (GameObject) obs;

                if (gm.isActive()){
                    if (gm.getSprite().perPixelCollision(mouse)) {
                        gameObjects.add(obs);
                    }
                }


            }
            int maxDepth = 0;
            MouseObserver obs = null;
            for (MouseObserver mouseObserver : gameObjects) {

                GameObject gm = (GameObject) mouseObserver;

                if (maxDepth < gm.getSprite().getDepth()) {
                    maxDepth = gm.getSprite().getDepth();
                    obs = mouseObserver;
                }
            }


            return obs;
        }
        return null;
    }
    public void makeCellActive(Piece piece){

        int currentColumn = piece.getCurrentCell().getCoord().y;
        int currentRow = piece.getCurrentCell().getCoord().x;
        //int columnRight = currentColumn;
        int row = currentRow;
        //int columnLeft = currentColumn;

        boolean breakCellUp = false;
        boolean breakCellDown = false;
        boolean breakCellLeft = false;
        boolean breakCellRight = false;
        boolean breakCellUpLeft = false;
        boolean breakCellUpRight = false;
        boolean breakCellDownLeft = false;
        boolean breakCellDownRight = false;

        switch (piece.getClass().getName()){

            case "Actors.Queen" :
                Queen queen = (Queen) piece;

                for(int i = 1;i<8;i++){
                    if(currentRow - i>=0) {
                        if (isValid(board.getColumn(currentColumn).get(currentRow - i)) && !breakCellUp)
                            board.getColumn(currentColumn).get(currentRow - i).setActive(true);
                        else
                            breakCellUp = true;
                    }
                    if(currentColumn - i>=0){
                        if(isValid(board.getColumn(currentColumn-i).get(currentRow)) && !breakCellLeft)
                            board.getColumn(currentColumn-i).get(currentRow).setActive(true);
                        else
                            breakCellLeft = true;
                    }
                }
                for(int i = 1;i<8;i++){
                    if(currentRow + i<8) {
                        if (isValid(board.getColumn(currentColumn).get(currentRow + i)) && !breakCellDown)
                            board.getColumn(currentColumn).get(currentRow + i).setActive(true);
                        else
                            breakCellDown = true;
                    }
                    if(currentColumn + i<8){
                        if(isValid(board.getColumn(currentColumn+i).get(currentRow)) && !breakCellRight)
                            board.getColumn(currentColumn+i).get(currentRow).setActive(true);
                        else
                            breakCellRight = true;
                    }
                }

                for(int i = 1;i<8;i++){

                    if(currentColumn+i<8 && (currentRow - i)>=0)
                        if(isValid(board.getColumn(currentColumn + i).get(currentRow-i)) && !breakCellUpRight)
                            board.getColumn(currentColumn + i).get(currentRow-i).setActive(true);
                        else
                            breakCellUpRight = true;

                    if(currentColumn - i>=0 && (currentRow - i)>=0)
                        if(isValid(board.getColumn(currentColumn - i).get(currentRow-i)) && !breakCellUpLeft)
                            board.getColumn(currentColumn - i).get(currentRow-i).setActive(true);
                        else
                            breakCellUpLeft = true;

                }

                for(int i = 1;i<8;i++){

                    if(currentColumn + i<8 && (currentRow + i)<8)
                        if(isValid(board.getColumn(currentColumn + i).get(currentRow+i)) && !breakCellDownRight)
                            board.getColumn(currentColumn + i).get(currentRow+i).setActive(true);
                        else
                            breakCellDownRight = true;


                    if(currentColumn - i>=0 && (currentRow + i)<8)
                        if(isValid(board.getColumn(currentColumn - i).get(currentRow+i)) && !breakCellDownLeft)
                            board.getColumn(currentColumn - i).get(currentRow+i).setActive(true);
                        else
                            breakCellDownLeft=true;
                }
                break;

            case "Actors.Pawn" :
                Pawn pawn = (Pawn) piece;
                if(pawn.getTeam()== Team.TEAMTYPE.Blue) {
                    if (currentRow == 6) {
                        if(isValid(board.getColumn(currentColumn).get(currentRow - 1)) && !breakCellUp){
                            board.getColumn(currentColumn).get(currentRow - 1).setActive(true);
                            if(isValid(board.getColumn(currentColumn).get(currentRow - 2)))
                                board.getColumn(currentColumn).get(currentRow - 2).setActive(true);
                        }
                    } else
                        if(isValid(board.getColumn(currentColumn).get(currentRow - 1)))
                            board.getColumn(currentColumn).get(currentRow - 1).setActive(true);
                } else {
                    if(currentRow == 1 ){
                        board.getColumn(currentColumn).get(currentRow + 1).setActive(true);
                        board.getColumn(currentColumn).get(currentRow + 2).setActive(true);
                    } else
                        board.getColumn(currentColumn).get(currentRow + 1).setActive(true);
                }

                break;

            case "Actors.Knight" :
                Knight knight = (Knight) piece;

                if(currentColumn+1<8 && currentRow+2<8)
                    if(isValid(board.getColumn(currentColumn+1).get(currentRow+2)))
                        board.getColumn(currentColumn+1).get(currentRow+2).setActive(true);
                if(currentColumn+2<8 && currentRow+1<8)
                    if(isValid(board.getColumn(currentColumn+2).get(currentRow+1)))
                        board.getColumn(currentColumn+2).get(currentRow+1).setActive(true);

                if(currentColumn-1>=0 && currentRow+2<8)
                    if(isValid(board.getColumn(currentColumn-1).get(currentRow+2)))
                        board.getColumn(currentColumn-1).get(currentRow+2).setActive(true);
                if(currentColumn-2>=0 && currentRow+1<8)
                    if(isValid(board.getColumn(currentColumn-2).get(currentRow+1)))
                        board.getColumn(currentColumn-2).get(currentRow+1).setActive(true);

                if(currentColumn+2<8 && currentRow-1>=0)
                    if(isValid(board.getColumn(currentColumn+2).get(currentRow-1)))
                        board.getColumn(currentColumn+2).get(currentRow-1).setActive(true);
                if(currentColumn+1<8 && currentRow-2>=0)
                    if(isValid(board.getColumn(currentColumn+1).get(currentRow-2)))
                        board.getColumn(currentColumn+1).get(currentRow-2).setActive(true);

                if(currentColumn-2>=0 && currentRow-1>=0)
                    if(isValid(board.getColumn(currentColumn-2).get(currentRow-1)))
                        board.getColumn(currentColumn-2).get(currentRow-1).setActive(true);
                if(currentColumn-1>=0 && currentRow-2>=0)
                    if(isValid(board.getColumn(currentColumn-1).get(currentRow-2)))
                        board.getColumn(currentColumn-1).get(currentRow-2).setActive(true);

                break;

            case "Actors.Bishop" :
                Bishop bishop = (Bishop) piece;

                for(int i = 1;i<8;i++){
                    if(currentColumn + i<8 && (currentRow - i)>=0)
                        if(isValid(board.getColumn(currentColumn + i).get(currentRow-i)) && !breakCellUpRight)
                            board.getColumn(currentColumn + i).get(currentRow-i).setActive(true);
                        else
                            breakCellUpRight = true;
                    if(currentColumn - i>=0 && (currentRow - i)>=0)
                        if(isValid(board.getColumn(currentColumn - i).get(currentRow-i)) && !breakCellUpLeft)
                            board.getColumn(currentColumn - i).get(currentRow-i).setActive(true);
                        else
                            breakCellUpLeft = true;
                }

                for(int i = 1;i<8;i++){

                    if(currentColumn + i<8 && (currentRow +i)<8)
                        if(isValid(board.getColumn(currentColumn + i).get(currentRow+i)) && !breakCellDownRight)
                            board.getColumn(currentColumn + i).get(currentRow+i).setActive(true);
                        else
                            breakCellDownRight = true;

                    if(currentColumn - i>=0 && (currentRow + i)<8)
                        if(isValid(board.getColumn(currentColumn - i).get(currentRow+i)) && !breakCellDownLeft)
                            board.getColumn(currentColumn - i).get(currentRow+i).setActive(true);
                        else
                            breakCellDownLeft = true;
                }
                break;

            case "Actors.King" :
                King king = (King) piece;

                if(currentColumn+1<8 && currentRow-1>=0){
                    if(isValid(board.getColumn(currentColumn+1).get(currentRow-1)))
                        board.getColumn(currentColumn+1).get(currentRow-1).setActive(true);
                    else{

                    }
                }
                if(currentRow-1>=0) {
                    if (isValid(board.getColumn(currentColumn).get(currentRow - 1)))
                        board.getColumn(currentColumn).get(currentRow - 1).setActive(true);
                    else{

                    }
                }

                if(currentColumn-1<8 && currentRow+1<8){
                    if(isValid(board.getColumn(currentColumn - 1).get(currentRow+1)))
                        board.getColumn(currentColumn - 1).get(currentRow+1).setActive(true);
                    else {

                    }
                }
                if(currentRow + 1 < 8)
                    if(isValid(board.getColumn(currentColumn).get(currentRow + 1)))
                        board.getColumn(currentColumn).get(currentRow + 1).setActive(true);

                if(currentColumn + 1 < 8)
                    if(isValid(board.getColumn(currentColumn + 1).get(currentRow)))
                        board.getColumn(currentColumn + 1).get(currentRow).setActive(true);

                if(currentColumn - 1>=0)
                    if(isValid(board.getColumn(currentColumn - 1).get(currentRow)))
                        board.getColumn(currentColumn - 1).get(currentRow).setActive(true);

                if(currentColumn - 1>=0 && currentRow - 1>=0)
                    if(isValid(board.getColumn(currentColumn - 1).get(currentRow - 1)))
                        board.getColumn(currentColumn - 1).get(currentRow-1).setActive(true);

                if(currentColumn + 1 < 8 && currentRow + 1 < 8)
                    if(isValid(board.getColumn(currentColumn + 1).get(currentRow + 1)))
                        board.getColumn(currentColumn + 1).get(currentRow+1).setActive(true);
                break;

            case "Actors.Rook" :
                Rook rook = (Rook) piece;

                for(int i = 1;i<8;i++){
                    if(currentRow-i>=0) {
                        if (isValid(board.getColumn(currentColumn).get(currentRow - i)) && !breakCellUp)
                            board.getColumn(currentColumn).get(currentRow - i).setActive(true);
                        else
                            breakCellUp = true;
                    }
                    if(currentColumn-i>=0){
                        if(isValid(board.getColumn(currentColumn-i).get(currentRow)) && !breakCellLeft)
                            board.getColumn(currentColumn-i).get(currentRow).setActive(true);
                        else
                            breakCellLeft = true;
                    }
                }
                for(int i = 1;i<8;i++){
                    if(currentRow+i<8) {
                        if (isValid(board.getColumn(currentColumn).get(currentRow + i)) && !breakCellDown)
                            board.getColumn(currentColumn).get(currentRow + i).setActive(true);
                        else
                            breakCellDown = true;
                    }
                    if(currentColumn+i<8){
                        if(isValid(board.getColumn(currentColumn+i).get(currentRow)) && !breakCellRight)
                            board.getColumn(currentColumn+i).get(currentRow).setActive(true);
                        else
                            breakCellRight = true;
                    }
                }
                break;
        }
    }

    public void disableCell(){
        for(Cell cell : board.getCells()){
            cell.setActive(false);
        }
    }
    public boolean isValid(Cell cell){
        for(Drawable drawable : getElements()){
            if(drawable instanceof Piece){
                Piece piece = (Piece) drawable;
                if(piece.getCurrentCell()==cell)
                return false;
            }
        }
        return true;
    }
    @Override
    public void notifyObservers(MouseEvent mouseEvent) {
        selectedSprite = getSelected(mouseEvent);
        GameObject gm = (GameObject) selectedSprite;
        if(selectedSprite!=null) {
            if (gm.isSelected())
                ((GameObject) selectedSprite).setSelected(false); //Polimorfismo
            else{
                gm.setSelected(true);
            }

        }

    }

    @Override
    public void Update() {
            if(selectedSprite instanceof Piece) {
                Piece piece = (Piece) selectedSprite;

                if(piece.isSelected()) {
                    makeCellActive((Piece) selectedSprite);
                    selectedPiece = piece;
                }
                else{
                    disableCell();
                }
            }
            if(selectedSprite instanceof Cell){
                Cell cell = (Cell) selectedSprite;
                selectedPiece.Move(cell);
                disableCell();
                selectedPiece.setSelected(false);
            }


    }
}
