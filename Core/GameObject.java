package Core;

import Terrain.Cell;

import java.awt.*;

/**
 * Created by dimaer on 24/03/17.
 */
public class GameObject{
    Point position;
    Rectangle boundRect;
    public GameObject(){};
    public GameObject(Point position)
    {
        this.position = position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setBoundRect(Rectangle boundRect) {
        this.boundRect = boundRect;
    }

    public Point getPosition() {
        return position;
    }

    public Rectangle getBoundRect() {
        return boundRect;
    }
}
