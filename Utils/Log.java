package Utils;

import Components.Graphics.Drawable;
import Components.Graphics.Sprite;
import Terrain.Cell;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by dimaer on 29/03/17.
 */
public class Log {
    private Log()
    {

    }

    private static Log log = new Log();

    public void showDepth(Graphics graphics, Sprite sprite)
    {
        int depth = sprite.getDepth();
        graphics.drawString(new String("" + depth),sprite.getPosition().x,sprite.getPosition().y);
    }
    public void showDepth(Graphics graphics, ArrayList<Cell> cells)
    {
        for (Cell cell : cells){
            int depth = cell.getSprite().getDepth();
            graphics.drawString(new String("" + depth),
                    cell.getSprite().getCenter().x , //35
                    cell.getSprite().getCenter().y ); //20
        }

    }
    public void showOrigins(Graphics graphics,Sprite sprite)
    {
        //for(Drawable d : objects){
            graphics.fillOval(sprite.getCenter().x,sprite.getCenter().y,10,10);
            //graphics.drawOval();
        //}
    }
    public static Log getInstance()
    {
        return log;
    }
}
