package Core;

import Components.Event.MouseObserver;
import Components.Graphics.Drawable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimaer on 20/03/17.
 * La classe Core.Scene rappresenta il contenitore di oggetti che devono essere disegnati
 *
 */

public class Scene {

    private List<Drawable> elements;
    SCENE_TYPE sceneType;

    private ArrayList<MouseObserver> observers;

    public enum SCENE_TYPE {
        MAIN_MENU,OPTIONS,STATS,RUNNED_GAME
    }

    /**
     * Costruisce il contenitore
     * @param sceneType il tipo di scena
     */
    public Scene(SCENE_TYPE sceneType)
    {
        this.sceneType = sceneType;
        elements = new ArrayList<Drawable>();
    }
    public void addElement(Drawable element)
    {
        elements.add(element);
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
