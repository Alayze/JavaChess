package Core;

import Components.Event.MouseObserver;
import Components.Graphics.Drawable;
import Components.Graphics.Sprite;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by dimaer on 24/03/17.
 * La classe base di tutti le entita' interattive
 */
public class GameObject implements Drawable, MouseObserver {

    private boolean selected;

    private Sprite sprite;

    private Point position;


    private boolean visibility;
    private boolean active;

    private Rectangle boundRect;

    /**
     * Costruttore di GameObject
     */
    public GameObject(){}

    /**
     * Costruttore di GameObject
     * @param position posizione iniziale
     */
    public GameObject(Point position)
    {
        
        active = true;
        visibility = true;
        this.position = position;

        sprite = new Sprite(position);
    }

    /**
     *Metodo che attiva GameObject
     * @param active flag
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     *Metodo che torna flag
     * @return true se GameObject e' attivo invece false
     */
    public boolean isActive() {
        return active;
    }

    /**
     *Metodo che verifica se oggetto e' visibile
     * @return
     */
    public boolean isVisibility() {
        return visibility;
    }

    /**
     * Metodo setter della posizione di oggetto
     * @param position
     */
    public void setPosition(Point position) {
        this.position = position;
        sprite.setPosition(position);
    }

    /**
     * Metodo che imposta la regione attiva di oggetto
     * @param boundRect rettangolo della regione
     */
    public void setBoundRect(Rectangle boundRect) {
        this.boundRect = boundRect;
    }

    /**
     *Metodo che carica sprite dalla memmoria di massa
     * @param Team Squadra
     * @param id Nome di file che e' stato impostato in Resources.xml
     */
    public void setSprite(String Team,String id){
        sprite.setImage(ResourceLoader.getInstance().LoadSprite(Team,id));
    }

    /**
     *Metodo che imposta sprite
     * @param sprite sprite da impostare
     */
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     *Metodo che torna sprite corrente
     * @return sprite corrente
     */
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * Metodo gettere della posizione di oggetto
     * @return posizione corrente sullo schermo
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Metodo getter della regione attiva di oggetto
     * @return rettangolo della regione
     */
    public Rectangle getBoundRect() {
        return boundRect;
    }

    /**
     *Metodo che imposta se l'ogetto e' stato selezionato
     * @param selected flag
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     *Metodo che verifica se l'ogetto e' stato selezionate
     * @return flag
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     *Metodo che disegna l'oggetto
     * @param graphics instanza di Graphics
     */
    @Override
    public void draw(Graphics graphics) {
        if(isVisibility())
        sprite.draw(graphics);
    }

    /**
     *Metodo viene invocato ad ogni evento di mouse
     * @param mouseEvent
     */
    @Override
    public void update(MouseEvent mouseEvent) {
        if(isActive()) {
            Point mouse = new Point(mouseEvent.getX(), mouseEvent.getY());

            if (mouseEvent.getID() == MouseEvent.MOUSE_MOVED) {


            }
            if (mouseEvent.getID() == MouseEvent.MOUSE_CLICKED) {
                if (getSprite().perPixelCollision(mouse)) {

                }

            }
        }
    }
}
