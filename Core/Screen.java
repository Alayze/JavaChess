package Core;
/**
 * Created by dimaer on 19/03/17.
 *  La classe principale di rendering
 */

import Actors.Pawn;
import Components.Event.MouseObserver;
import Components.Graphics.Gui.*;
import Components.Graphics.Gui.Button;
import Components.Graphics.Sprite;
import Scenes.Game;
import Terrain.Board;
import Utils.Log;

import java.awt.*;
import java.awt.Rectangle;
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
    Scene mainMenu;
    Game game;
    Board board;
    Timer timer;
    @Override
    public synchronized void addMouseListener(MouseListener mouseListener) {
        super.addMouseListener(mouseListener);
    }

    public Screen(int Width, int Height)
    {
        initMouseListeners();
        game = new Game();
        /*game = new Scene(Scene.SCENE_TYPE.RUNNED_GAME);
        mainMenu = new Scene(Scene.SCENE_TYPE.MAIN_MENU);*/
        sceneManager = new SceneManager();
        sceneManager.addScene(game);
        sceneManager.setCurrentScene(game);
        observers = new ArrayList<>();
        //mainMenu.addElement();
        //board = new Board(new Point(400,-200),this,new Weather(Weather.WEATHER_TYPE.Summer));
        //game.addElement(board);
        //pawn = new Pawn(board.getCells().get(0));
        width= Width;
        height = Height;
        timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                repaint();

            }
        },1*1000,1*1000);
        //button = new Button();
        //sprite = new Sprite(new Point(0,0));
        //sprite.setImage(ResourceLoader.getInstance().LoadResource("Earth_Grass_3","sprite"));

        /*button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.out.print("Clicked");
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                //System.out.print("Entered");
            }
        });*/
    }
    /**Определить как concrete class*/
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
    private void initScenes(){

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
        //sprite.draw(graphics);
        //board.draw(graphics);
        sceneManager.draw(graphics);
        //pawn.draw(graphics);
        //Log.getInstance().showDepth(graphics,board.getCells());
        //Log.getInstance().showOrigins(graphics,pawn.getSprite());
    }

    @Override
    public void update(Graphics graphics) {
        super.update(graphics);
    }


}
