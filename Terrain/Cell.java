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
     *Costruttore di cella
     * @param position posizione iniziale sullo scherma
     * @param spriteType tipo di sprite
     * @param weatherType il tipo di stagione
     */
    public Cell(Point position, String spriteType, String weatherType){
        super(position);
        atackable=false;
        setActive(false);
        colorFill = new Color(0,0,0,100);
        attackColor = new Color(200,0,0,200);
        this.spriteType = spriteType;
        spriteOutline = new Sprite(position);

        getSprite().setImage(ResourceLoader.getInstance().LoadTile(weatherType,spriteType));
    }
    //Enumerazione che contiene dei valori di tipo di cella (il colore sulla scacchiera)

    enum Type{
        TYPE1,TYPE2
    }

    /**
     *Metodo che torna le coordinate della cella in base di scachiera
     * @return
     */
    public Point getCoord(){

        int depth = getSprite().getDepth();

        int n = depth / 8;

        return new Point(n,depth - (8*n));
    }
    /**
     *Metodo che imposta il tipo di cella
     * @param type tipo di cella
     */
    public void setType(Type type)
    {
        this.type = type;
    }

    /**
     *Metodo che torna il topo di sprite
     * @return il tipo di sprite
     */
    public String getSpriteType(){return spriteType;}

    /**
     *Metodo che imposta se la cella e' attacabile
     * @param atackable flag
     */
    public void setAtackable(boolean atackable) {
        this.atackable = atackable;
    }

    /**
     *Metodo che verifica se la cella e' attacabile
     * @return flag
     */
    public boolean isAtackable() {
        return atackable;
    }

    /**
     *Aggiorna la cella
     * @param mouseEvent evento di Mouse
     */
    @Override
    public void update(MouseEvent mouseEvent) {
        super.update(mouseEvent);
    }

    /**
     *Metodo che disegna la cella
     * @param graphics instanza di Graphics
     */
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

    }
}
