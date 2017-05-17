package Core;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimaer on 20/03/17.
 * La classe Core.SceneManager si occupa della gestione delle varie scene.Core.SceneManager ha il potere di cambiare
 * le scene da rappresentare.
 */
public class SceneManager {

    private List<Scene> scenes;
    private Scene currentScene;

    public SceneManager(){
        scenes = new ArrayList<>();
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
     * Funzione che disegna la scena
     * @param graphics
     */
    public void draw(Graphics graphics)
    {

        currentScene.draw(graphics);
    }
}
