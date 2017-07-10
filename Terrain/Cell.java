package Terrain;

import Components.Graphics.Sprite;
import Core.GameObject;
import Core.ResourceLoader;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by dimaer on 27/03/17.
 */
public class Cell extends GameObject {

    private Sprite spriteOutline;

    private Type type;

    private String spriteType;

    /**
     *
     * @param position
     * @param spriteType
     * @param weatherType
     */
    public Cell(Point position, String spriteType, String weatherType){
        super(position);
        setActive(false);
        this.spriteType = spriteType;
        spriteOutline = new Sprite(position);
        //sprite = new Sprite(position);
        //System.out.print(getClass().toString() + ":[spriteType: " + spriteType + "]\n");
        getSprite().setImage(ResourceLoader.getInstance().LoadTile(weatherType,spriteType));
    }
    //Enumerazione che contiene dei valori di tipo di cella (il colore sulla scacchiera)

    /**
     *
     */
    enum Type{
        TYPE1,TYPE2
    }

    /**
     *
     * @return
     */
    public Point getCoord(){

        int depth = getSprite().getDepth();

        int n = depth / 8;

        return new Point(n,depth - (8*n));
    }
    /**
     *
     * @param type
     */
    public void setType(Type type)
    {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public String getSpriteType(){return spriteType;}

    @Override
    public void update(MouseEvent mouseEvent) {
        super.update(mouseEvent);
    }

    @Override
    public void draw(Graphics graphics) {

        if(isVisibility()){
            super.draw(graphics);
            spriteOutline.draw(graphics);
        }
        if(isActive())
        graphics.fillOval(getSprite().getCenter().x-20,getSprite().getCenter().y,40,25);
        graphics.drawString(" " + getCoord().x + "," + getCoord().y,getSprite().getCenter().x-5,getSprite().getCenter().y+20);
        //graphics.drawString(" " + ,getSprite().getCenter().x-5,getSprite().getCenter().y+20);
    }
}
