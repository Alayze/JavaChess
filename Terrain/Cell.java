package Terrain;

import Components.Event.MouseObserver;
import Components.Graphics.Drawable;
import Components.Graphics.Sprite;
import Core.GameObject;
import Core.ResourceLoader;
import Core.Weather;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by dimaer on 27/03/17.
 */
public class Cell extends GameObject implements Drawable,MouseObserver{
    private Sprite sprite;
    private Type type;
    private String spriteType;
    public Cell(Point position, String spriteType, String weatherType){
        super(position);
        this.spriteType=spriteType;
        sprite = new Sprite(position);
        System.out.print(getClass().toString() + ":[spriteType: " + spriteType + "]\n");
        sprite.setImage(ResourceLoader.getInstance().LoadTile(weatherType,spriteType));
    }
    //Enumerazione che contiene dei valori di tipo di cella (il colore sulla scacchiera)
    enum Type{
        TYPE1,TYPE2
    }

    /**
     *
     * @param type
     */
    public void setType(Type type)
    {
        this.type = type;
    }
    public String getSpriteType(){return spriteType;}
    /**
     *
     * @return
     */
    public Sprite getSprite() {
        return sprite;
    }
    /*@Override
    public Point getPosition() {

    }
    */
    @Override
    public void update(MouseEvent mouseEvent, String message) {

    }

    @Override
    public void draw(Graphics graphics) {
    sprite.draw(graphics);
    }
}
