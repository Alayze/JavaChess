import Components.MouseObserver;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by dimaer on 25/03/17.
 */
public class Pawn extends GameObject implements Drawable, MouseObserver {
    Sprite sprite;
    public Pawn(Point position)
    {
        super(position);
        sprite = new Sprite(position);
    }

    @Override
    public void draw(Graphics graphics) {

    }

    @Override
    public void update(MouseEvent mouseEvent, String message) {

    }
}
