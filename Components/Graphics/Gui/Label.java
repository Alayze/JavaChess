package Components.Graphics.Gui;

import Components.Graphics.Drawable;

import java.awt.*;

/**Classe che descrive etichetta
 * Created by dimaer on 21/06/17.
 */
public class Label implements Drawable{

    private Point position;
    private String text;

    public Label(String text,Point position){
        this.position = position;
        this.text = text;
    }

    public Point getPosition(){
        return position;
    }
    public void setPosition(Point position){
        this.position = position;
    }
    public String getText(){
        return text;
    }
    public void setText(String text){
        this.text = text;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawString(text,position.x,position.y);
    }
}
