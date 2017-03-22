/**
 * Created by dimaer on 19/03/17.
 *  La classe principale di rendering
 */
import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Screen extends Canvas{
    Sprite sprite;

    private int width,height;


    @Override
    public synchronized void addMouseListener(MouseListener mouseListener) {
        super.addMouseListener(mouseListener);
    }

    public Screen(int Width, int Height)
    {
        width= Width;
        height = Height;

        sprite = new Sprite(new Point(20,20));
        sprite.setImage("src/1.jpg");
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        setBackground(new Color(100));
        //rectangle.draw(graphics);
        sprite.draw(graphics);

    }

    @Override
    public void update(Graphics graphics) {
        super.update(graphics);
        //System.out.print(getMousePosition().x);
    }


}
