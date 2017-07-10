package Core;

import Components.Event.MouseObserver;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


/**
 * Created by dimaer on 20/03/17.
 * La classe Core.SceneManager si occupa della gestione delle varie scene.Core.SceneManager ha il potere di cambiare
 * le scene da rappresentare.
 */
public final class SceneManager {

    private ArrayList<Scene> scenes;
    private Scene currentScene;

    private SceneManager(){
        scenes = new ArrayList<>();
    }

    private static final SceneManager sceneManager = new SceneManager();

    /**
     * Metodo che torna l'istanza della classe
     * @return instanza di SceneManager
     */
    public static SceneManager getInstance(){
        return sceneManager;
    }

    /**
     * Funzione che aggiunge la scena ad contenitore di Core.SceneManager
     * @param scene la scena da aggiungere
     */
    public void addScene(Scene scene)
    {
        scenes.add(scene);
    }

    /**
     * Funzione che imposta la scena corrente
     * @param scene Tipo di scena
     */
    public void setCurrentScene(Scene scene){

        currentScene = scene;
    }

    /**
     * Metodo
     * @param sceneType
     */
    public void setCurrentScene(Scene.SCENE_TYPE sceneType){
        for (Scene scene : scenes){
            if(scene.getSceneType()==sceneType){
                currentScene=scene;
                return;
            }

        }
    }

    /**
     *
     * @return
     */
    public Scene getCurrentScene() {
        return currentScene;
    }

    /**
     *
     * @param mouseEvent
     */
    public void notifyObservers(MouseEvent mouseEvent){
        getCurrentScene().notifyObservers(mouseEvent);
    }

    /**
     *
     * @return
     */
    public ArrayList<Scene> getScenes(){
        return scenes;
    }


    /*public ArrayList<MouseObserver> getObservers(){
        return currentScene.getObservers();
    }*/
    /**
     * Funzione che disegna la scena
     * @param graphics
     */
    public void draw(Graphics graphics)
    {

        currentScene.draw(graphics);
    }
}
