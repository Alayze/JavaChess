package Core; /**
 * Created by dimaer on 19/03/17.
 *  La classe principale di rendering
 */

import Components.Event.MouseObserver;
import Components.Graphics.Gui.*;
import Components.Graphics.Gui.Button;
import Components.Graphics.Sprite;
import Core.ResourceLoader;
import Core.Scene;
import Core.SceneManager;
import Terrain.Board;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Screen extends Canvas{
    private Sprite sprite;
    private int width,height;
    private Components.Graphics.Gui.Button button;
    private ArrayList<MouseObserver> observers;

    SceneManager sceneManager = new SceneManager();
    Scene mainMenu = new Scene(Scene.SCENE_TYPE.MAIN_MENU);
    Scene game = new Scene(Scene.SCENE_TYPE.RUNNED_GAME);
    Board board;

    @Override
    public synchronized void addMouseListener(MouseListener mouseListener) {
        super.addMouseListener(mouseListener);
    }

    public Screen(int Width, int Height)
    {
        initMouseListeners();

        observers = new ArrayList<>();
        //mainMenu.addElement();
        board = new Board(new Point(400,-200),this);
        width= Width;
        height = Height;

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
        //sprite.draw(graphics);
        board.draw(graphics);
    }

    @Override
    public void update(Graphics graphics) {
        super.update(graphics);
    }


}
