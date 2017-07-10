package Components.Graphics.Gui;

import Components.Graphics.Drawable;

import java.awt.*;

/**Classe che descrive etichetta
 * Created by dimaer on 21/06/17.
 */
public class Label implements Drawable{

    private Point position;
    private String text;

    /**
     *
     * @param text
     * @param position
     */
    public Label(String text,Point position){
        this.position = position;
        this.text = text;
    }

    /**
     *
     * @return
     */
    public Point getPosition(){
        return position;
    }

    /**
     *
     * @param position
     */
    public void setPosition(Point position){
        this.position = position;
    }

    /**
     *
     * @return
     */
    public String getText(){
        return text;
    }

    /**
     *
     * @param text
     */
    public void setText(String text){
        this.text = text;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawString(text,position.x,position.y);
    }
}
