package Core;

import Actors.Piece;
import Components.Event.MouseObserver;
import Components.Graphics.Drawable;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * Created by dimaer on 20/03/17.
 * La classe Core.Scene rappresenta il contenitore di oggetti che devono essere disegnati
 *
 */

public abstract class Scene {

    private List<Drawable> elements;
    SCENE_TYPE sceneType;

    private ArrayList<MouseObserver> observers;

    public enum SCENE_TYPE {
        MAIN_MENU,OPTIONS,STATS,RUNNED_GAME
    }

    /**
     *Metodo di inizializazzione della scena
     */
    public abstract void Init();

    /**
     *Metodo di aggirnamento della scena
     */
    public abstract void Update();

    /**
     *Costruttore della scena
     */
    public Scene(){observers = new ArrayList<>();elements = new ArrayList<>();Init();}

    /**
     *Torna oggetti che sono interagiscono con il Mouse
     * @return insieme di ogetti
     */
    public ArrayList<MouseObserver> getObservers(){
        return observers;
    }

    /**
     *Aggiunge l'elemento in render coda della scene
     * @param element elemento da aggiungere
     */
    public void addElement(Drawable element)
    {
        elements.add(element);
        if(element instanceof MouseObserver){
            observers.add((MouseObserver) element); //Se element anche e' instance di MouseObserver
            // allora faccio cast
        }
    }

    /**
     *Torna elementi che stanno in coda di render
     * @return insieme di oggetti
     */
    public List<Drawable> getElements() {
        return elements;
    }

    /**
     *Metodo che invia l'evento di Mouse a tutti gli elementi di coda di render che sono interattivi con Mouse
     * @param mouseEvent evento di Mouse
     */
    public void notifyObservers(MouseEvent mouseEvent){
        for(MouseObserver obs : getObservers())
            obs.update(mouseEvent);
    }

    /**
     *Metodo che esegue bubbleSort sui elementi di coda rendering
     */
    public void arrange(){
        for(int i = 0; i < elements.size(); i++) {

            boolean flag = false;

            for(int j = 0; j < elements.size()-1; j++) {
                if (elements.get(j) instanceof Piece && elements.get(j + 1) instanceof Piece) {
                    Piece temp1 = (Piece) elements.get(j);
                    Piece temp2 = (Piece) elements.get(j + 1);

                    if (temp1.getSprite().getDepth() > temp2.getSprite().getDepth()) {
                        Drawable element = elements.get(j);
                        elements.set(j, elements.get(j + 1));
                        elements.set(j + 1, element);
                        flag = true;
                    }


                }
            }

            if(!flag) break;
        }
    }
    /**
     * Metodo che torna il tipo di scena
     * @return SCENES il tipo di scena
     */
    public SCENE_TYPE getSceneType()
    {
        return sceneType;
    }

    /**
     *Metodo che imposta tipo di scena
     * @param sceneType il tipo di scena
     */
    public void setSceneType(SCENE_TYPE sceneType){
        this.sceneType = sceneType;
    }
    /**
     * Disegna tutti i elementi che stanno nella coda
     * @param graphics l'oggetto che si occupa di operazioni grafiche
     */
    public void draw(Graphics graphics)
    {


        arrange(); //Z-Order
        for(Drawable d : elements)
        {
            d.draw(graphics);
        }
    }



}
