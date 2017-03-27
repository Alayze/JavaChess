import java.awt.*;
import java.util.ArrayList;

/**
 * Created by dimaer on 27/03/17.
 */
public class Board extends GameObject implements Drawable {
    ArrayList<Cell> cells;
    public Board(Point position) {
        super(position);
        cells = new ArrayList<>();
    }

    @Override
    public void draw(Graphics graphics) {
        for (Cell c : cells){
            c.draw(graphics);
        }

    }
}