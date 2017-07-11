package Scenes;

import Components.Graphics.Gui.Button;
import Core.Scene;
import Core.SceneManager;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**Menu Principale
 * Created by dimaer on 17/05/17.
 */
public class MainMenu extends Scene{
    /**
     *Metodo che inizializza la scena
     */
    @Override
    public void Init() {
        setSceneType(SCENE_TYPE.MAIN_MENU);

        Button play_btn= new Button(new Rectangle(350,100,100,50),"Play");
        Button stats_btn = new Button(new Rectangle(350,200,100,50),"Statistics");
        Button options_btn = new Button(new Rectangle(350,300,100,50),"Options");
        Button exit_btn = new Button(new Rectangle(350,400,100,50),"Exit");

        play_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                //System.out.println("clicked");
                SceneManager.getInstance().setCurrentScene(SCENE_TYPE.RUNNED_GAME);
            }
        });
        stats_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                SceneManager.getInstance().setCurrentScene(SCENE_TYPE.STATS);
            }
        });
        exit_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.exit(1);
            }
        });
        options_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                SceneManager.getInstance().setCurrentScene(SCENE_TYPE.OPTIONS);
            }
        });

        addElement(play_btn);
        addElement(stats_btn);
        addElement(exit_btn);
        addElement(options_btn);
    }

    /**
     * Metodo che aggiorna la Scena
     */
    @Override
    public void Update() {

    }
}
