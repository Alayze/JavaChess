package Core;

import Components.Event.MouseObserver;
import Components.Graphics.Drawable;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
    public abstract void Init();
    public abstract void Update();

    public Scene(){observers = new ArrayList<>();elements = new ArrayList<>();Init();}

    /**
     *
     * @return
     */
    public ArrayList<MouseObserver> getObservers(){
        return observers;
    }

    /**
     *
     * @param element
     */
    public void addElement(Drawable element)
    {
        elements.add(element);
        if(element instanceof MouseObserver){
            observers.add((MouseObserver) element); //Se element anche e' instance di MouseObserver
            // allora faccio cast
        }
    }

    public List<Drawable> getElements() {
        return elements;
    }

    public void notifyObservers(MouseEvent mouseEvent){
        for(MouseObserver obs : getObservers())
            obs.update(mouseEvent);
    }
    /**
     * Metodo che torna il tipo di scena
     * @return SCENES il tipo di scena
     */
    public SCENE_TYPE getSceneType()
    {
        return sceneType;
    }

    public void setSceneType(SCENE_TYPE sceneType){
        this.sceneType = sceneType;
    }
    /**
     * Disegna tutti i elementi che stanno dentro il contenitore
     * @param graphics l'oggetto che si occupa di operazioni grafiche
     */
    public void draw(Graphics graphics)
    {
        for(Drawable d : elements)
        {
            d.draw(graphics);
        }
    }



}
