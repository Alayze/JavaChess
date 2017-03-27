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

    /**
     * Metodo che sposta la piedina
     * @param cell cella della nuova posizione
     */
    public void Move(Cell cell){

    }

    /**
     * Metodo che torna cella corrente
     * @return L'oggetto di tipo Cell
     */
    public Cell getCell()
    {
        return null;
    }
    @Override
    public void draw(Graphics graphics) {
        sprite.draw(graphics);
    }

    @Override
    public void update(MouseEvent mouseEvent, String message) {

    }
}
