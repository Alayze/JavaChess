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
     * Costruttore di Label
     * @param text testo di etichetta
     * @param position posizione sullo schermo
     */
    public Label(String text,Point position){
        this.position = position;
        this.text = text;
    }

    /**
     *Metodo che torna la posizione sullo schermo
     * @return posizione sullo schermo
     */
    public Point getPosition(){
        return position;
    }

    /**
     *Metodo che imposta la posizione
     * @param position posizione sullo schermo
     */
    public void setPosition(Point position){
        this.position = position;
    }

    /**
     *Metodo che torna il testo di etichetta
     * @return il testo di etichetta
     */
    public String getText(){
        return text;
    }

    /**
     *Metodo che imposta il testo a etichetta
     * @param text testo da impostare
     */
    public void setText(String text){
        this.text = text;
    }

    /**
     * Metodo che disegna etichetta
     * @param graphics instanza di Graphics
     */
    @Override
    public void draw(Graphics graphics) {
        graphics.drawString(text,position.x,position.y);
    }
}
