package Core;

import Core.Scene;

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

    public SceneManager()
    {
        scenes = new ArrayList<Scene>();
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
    public void setCurrentScene(Scene.SCENE_TYPE scene)
    {
        for (Scene s : scenes)
        {
            if(s.getSceneType()==scene)
            {
                currentScene = s;
            }
        }
    }

    /**
     * Funzione che disegna la scena
     * @param graphics
     */
    public void draw(Graphics graphics)
    {
        for (Scene s : scenes)
        {
            s.draw(graphics);
        }
    }
}
