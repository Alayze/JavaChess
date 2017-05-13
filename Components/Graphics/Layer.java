package Components.Graphics;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by dimaer on 05/04/17.
 */
public class Layer implements Drawable {
    private ArrayList<Drawable> elements;
    public Layer(){
        elements = new ArrayList<>();
    }

    @Override
    public void draw(Graphics graphics) {
        for(Drawable e : elements){
            e.draw(graphics);
        }
    }
}
