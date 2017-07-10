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
    private Color colorFill;
    private Color attackColor;
    private String spriteType;
    private boolean atackable;
    /**
     *
     * @param position
     * @param spriteType
     * @param weatherType
     */
    public Cell(Point position, String spriteType, String weatherType){
        super(position);
        atackable=false;
        setActive(false);
        colorFill = new Color(0,0,0,100);
        attackColor = new Color(200,0,0,200);
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

    /**
     *
     * @param atackable
     */
    public void setAtackable(boolean atackable) {
        this.atackable = atackable;
    }

    /**
     *
     * @return
     */
    public boolean isAtackable() {
        return atackable;
    }

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
        if(isActive()) {

            graphics.setColor(colorFill);

            if(isAtackable())
                graphics.setColor(attackColor);

            graphics.fillOval(getSprite().getCenter().x - 20, getSprite().getCenter().y, 40, 25);
            graphics.setColor(new Color(0,0,0));
        }
       // graphics.drawString(" " + getCoord().x + "," + getCoord().y,getSprite().getCenter().x-5,getSprite().getCenter().y+20);
        //graphics.drawString(" " + ,getSprite().getCenter().x-5,getSprite().getCenter().y+20);
    }
}
