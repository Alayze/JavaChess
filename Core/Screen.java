package Core;
/**
 * Created by dimaer on 19/03/17.
 *  La classe principale di rendering
 */


import Components.Event.MouseObserver;
import Scenes.Game;
import Scenes.MainMenu;


import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Screen extends Canvas{
    private int bufferWidth,bufferHeight;
    private ArrayList<MouseObserver> observers;

    private Graphics bufferGraphics;
    private Image bufferImage;

    SceneManager sceneManager;

    Scene game;
    Scene mainMenu;
    Timer timer;

    @Override
    public synchronized void addMouseListener(MouseListener mouseListener) {
        super.addMouseListener(mouseListener);
    }

    public Screen()
    {
        initMouseListeners();
        game = new Game();
        mainMenu = new MainMenu();

        sceneManager = SceneManager.getInstance();

        sceneManager.addScene(game);
        sceneManager.addScene(mainMenu);
        sceneManager.setCurrentScene(mainMenu);

        //observers = new ArrayList<>();

        /** Timer serve per aggiornare lo schermo perche da solo non si aggiorna*/
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        },1,1);////repaint viene chiamato ogni secondo;

    }

    /**
     * Reset di buffer
     */
    public void resetBuffer(){
        bufferHeight = getSize().height;
        bufferWidth = getSize().width;
        System.out.println(bufferWidth);

        if(bufferImage!=null){
            bufferGraphics.dispose();
            bufferGraphics=null;
        }
        if(bufferImage!=null){
            bufferImage.flush();
            bufferImage=null;
        }
        //System.gc();

        bufferImage = createImage(bufferWidth,bufferHeight);
        bufferGraphics = bufferImage.getGraphics();
    }

    /**
     * Metodo che disegna frame in buffer
     * @param graphics
     */
    public void paintBuffer(Graphics graphics){
        setBackground(new Color(255,255,255,255));

        sceneManager.draw(graphics);
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

    /**
     * Funzione che invia le notifiche ai observers di scena corrente di sceneManager
     * @param mouseEvent evento da inviare
     * @param message messagio da inviare
     */
    private void NotifyObservers(MouseEvent mouseEvent , String message)
    {
        for(MouseObserver observer : sceneManager.getObservers())
        {
            observer.update(mouseEvent,message);
        }
    }
    @Override
    public void paint(Graphics graphics) {
        if(bufferWidth!=getSize().width ||
                bufferHeight!=getSize().height ||
                bufferImage==null || bufferGraphics==null)
        resetBuffer();

        //super.paint(graphics);
        if(bufferGraphics!=null){
            bufferGraphics.clearRect(0,0,bufferWidth,bufferHeight);
            paintBuffer(bufferGraphics);
            graphics.drawImage(bufferImage,0,0,this);

        }



    }

    @Override
    public void update(Graphics graphics) {
        /*super.update(graphics);*/
        paint(graphics);
    }


}
