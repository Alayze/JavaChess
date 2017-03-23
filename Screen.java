/**
 * Created by dimaer on 19/03/17.
 *  La classe principale di rendering
 */
import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Screen extends Canvas{
    private Sprite sprite;
    private int width,height;
    private Button button;
    private ArrayList<MouseObserver> observers;
    @Override
    public synchronized void addMouseListener(MouseListener mouseListener) {
        super.addMouseListener(mouseListener);
    }

    public Screen(int Width, int Height)
    {
        initMouseListeners();
        button = new Button(new Rectangle(10,10,40,40));

        observers = new ArrayList<>();
        observers.add(button);
        width= Width;
        height = Height;

        sprite = new Sprite(new Point(20,20));
        sprite.setImage("src/1.jpg");

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.out.print("Clicked");
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                //System.out.print("Entered");
            }
        });
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
        setBackground(new Color(100));
        button.draw(graphics);
        //sprite.draw(graphics);

    }

    @Override
    public void update(Graphics graphics) {
        super.update(graphics);
        //System.out.print(getMousePosition().x);
    }


}
