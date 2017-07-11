package Scenes;

import Actors.*;
import Components.Event.MouseObserver;
import Components.Graphics.Drawable;
import Components.Graphics.Gui.Label;
import Core.GameObject;
import Core.Scene;
import Core.SceneManager;
import Core.Weather;
import Terrain.Board;
import Terrain.Cell;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/** Gioco
 * Created by dimaer on 17/05/17.
 */
public final class Game extends Scene {

    Board board;
    Label labelTurn;
    Label labelSeason;
    MouseObserver selectedSprite;
    Piece selectedPiece;
    Team.TEAMTYPE currentTurn;
    /**
     * Metodo che inizializza la scena
     */
    @Override
    public void Init() {

        currentTurn = Team.TEAMTYPE.Blue;

        setSceneType(SCENE_TYPE.RUNNED_GAME);
        board = new Board(new Point(390,-300),new Weather(Weather.WEATHER_TYPE.Winter));

        labelTurn = new Label("Turno di giocatore :",new Point(100,100));
        labelSeason = new Label("Stagione :",new Point(400,50));

        for(Cell cell : board.getCells())
            addElement(cell);

        for (int i = 0 ;i<8;i++){
            Pawn pawn = new Pawn(board.getCell(6,(char)('a'+i)), Team.TEAMTYPE.Blue,board);
            addElement(pawn);
        }

        addElement(new Rook(board.getCell(7,'a'), Team.TEAMTYPE.Blue,board));
        addElement(new Knight(board.getCell(7,'b'), Team.TEAMTYPE.Blue,board));
        addElement(new Bishop(board.getCell(7,'c'), Team.TEAMTYPE.Blue,board));
        addElement(new Queen(board.getCell(7,'d'), Team.TEAMTYPE.Blue,board));
        addElement(new Bishop(board.getCell(7,'f'), Team.TEAMTYPE.Blue,board));
        addElement(new Knight(board.getCell(7,'g'), Team.TEAMTYPE.Blue,board));

        addElement(new King(board.getCell(7,'e'), Team.TEAMTYPE.Blue,board));

        addElement(new Rook(board.getCell(7,'h'), Team.TEAMTYPE.Blue,board));



        addElement(new Rook(board.getCell(0,'a'), Team.TEAMTYPE.Red,board));
        addElement(new Knight(board.getCell(0,'b'), Team.TEAMTYPE.Red,board));
        addElement(new Bishop(board.getCell(0,'c'), Team.TEAMTYPE.Red,board));
        addElement(new Queen(board.getCell(0,'d'), Team.TEAMTYPE.Red,board));
        addElement(new Bishop(board.getCell(0,'f'), Team.TEAMTYPE.Red,board));
        addElement(new Knight(board.getCell(0,'g'), Team.TEAMTYPE.Red,board));

        addElement(new King(board.getCell(0,'e'), Team.TEAMTYPE.Red,board));

        addElement(new Rook(board.getCell(0,'h'), Team.TEAMTYPE.Red,board));

        for(int i = 0;i<8;i++){
            Pawn pawn = new Pawn(board.getCell(1,(char)('a' + i)), Team.TEAMTYPE.Red,board);
            addElement(pawn);
        }

        addElement(labelTurn);
        addElement(labelSeason);


    }

    /**
     *Metodo che torna oggetto selezionato dal mouse
     * @param mouseEvent evento di Mouse
     * @return ogetto selezionato
     */
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
    /**
     *Cambia la squadra corrente
     */
    public void switchTeam(){
        if(currentTurn == Team.TEAMTYPE.Blue){
            currentTurn = Team.TEAMTYPE.Red;
        }else
            currentTurn = Team.TEAMTYPE.Blue;
    }

    /**
     *Attiva le celle in base della pedina
     * @param piece pedina
     */
    public void makeCellActive(Piece piece){

        for(Cell cell : piece.getValidCells()){
            if(isValid(cell)){
                cell.setActive(true);
            }
            else {
                Piece p = getPiece(cell);

                if(p.getTeam()!=piece.getTeam()){
                    cell.setActive(true);
                    cell.setAtackable(true);
                }
            }
        }
    }

    /**
     *Disabilita' tutte le celle
     */
    public void disableCell(){
        for(Cell cell : board.getCells()){
            cell.setActive(false);
        }
    }

    /**
     *Cerca se la cella e' vuota
     * @param cell cella da verificare
     * @return true se la cella e' vuota altrimenti false
     */
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

    /**
     *Torna la pedina sulla cella specifica
     * @param cell cella da esaminare
     * @return pedina
     */
    public Piece getPiece(Cell cell){
        for(Drawable drawable : getElements()){
            if(drawable instanceof Piece){
                Piece piece = (Piece) drawable;
                if(piece.getCurrentCell()==cell)
                    return piece;
            }
        }
        return null;
    }

    /**
     * Metodo che porta l'evento ai elementi di scene
     * @param mouseEvent evento di Mouse
     */
    @Override
    public void notifyObservers(MouseEvent mouseEvent) {
        selectedSprite = getSelected(mouseEvent);
        GameObject gm = (GameObject) selectedSprite;
        if(selectedSprite!=null && selectedSprite instanceof Piece) {
                Piece p = (Piece) selectedSprite;
                if (p.getTeam() == currentTurn) {
                    if (gm.isSelected())
                        ((GameObject) selectedSprite).setSelected(false); //Polimorfismo
                    else {
                        gm.setSelected(true);
                    }
                }
        }

    }

    /**
     * Aggiorna la scena
     */
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
                if(cell.isAtackable()) {
                    Piece deathPiece = getPiece(cell);
                    if(deathPiece instanceof King){
                        SceneManager.getInstance().setCurrentScene(SCENE_TYPE.MAIN_MENU);
                    }
                    getElements().remove(deathPiece);
                    cell.setAtackable(false);
                }
                switchTeam();
                disableCell();
                selectedSprite = null;
                selectedPiece.setSelected(false);
                selectedPiece = null;

            }


    }
}
