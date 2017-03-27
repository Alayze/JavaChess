package Terrain;

import Components.Graphics.Drawable;
import Components.Graphics.Sprite;
import Core.GameObject;

import java.awt.*;

/**
 * Created by dimaer on 27/03/17.
 */
public class Cell extends GameObject implements Drawable {
    Sprite sprite;
    public Cell(Point position) {
        super(position);
        sprite = new Sprite(position);
    }

    @Override
    public void draw(Graphics graphics) {
    sprite.draw(graphics);
    }
}
