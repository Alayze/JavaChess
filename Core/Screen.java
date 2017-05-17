package Core;
/**
 * Created by dimaer on 19/03/17.
 *  La classe principale di rendering
 */


import Components.Event.MouseObserver;
import Scenes.Game;


import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Screen extends Canvas{
    private int width,height;
    private ArrayList<MouseObserver> observers;

    SceneManager sceneManager;
    Game game;

    Timer timer;

    @Override
    public synchronized void addMouseListener(MouseListener mouseListener) {
        super.addMouseListener(mouseListener);
    }

    public Screen(int Width, int Height)
    {
        initMouseListeners();
        game = new Game();

        sceneManager = new SceneManager();
        sceneManager.addScene(game);
        sceneManager.setCurrentScene(game);
        observers = new ArrayList<>();

        width = Width;
        height = Height;
        /** Timer serve per aggiornare lo schermo perche da solo non si aggiorna*/
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        },1*1000,1*1000); //repaint viene chiamato ogni secondo;

    }
    /**Definire come concrete class*/
    private void initMouseListeners()
    {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                NotifyObservers(mouseEvent , "MOUSE_CLICKED");
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                super.mouseMoved(mouseEvent);
                NotifyObservers(mouseEvent,"MOUSE_MOVED");
            }
        });

    }

    private void NotifyObservers(MouseEvent mouseEvent , String message)
    {
        for(MouseObserver observer : observers)
        {
            observer.update(mouseEvent,message);
        }
    }

    @Override
    public void paint(Graphics graphics) {

        super.paint(graphics);
        setBackground(new Color(255,255,255,255));

        sceneManager.draw(graphics);

    }

    @Override
    public void update(Graphics graphics) {
        super.update(graphics);
    }


}
