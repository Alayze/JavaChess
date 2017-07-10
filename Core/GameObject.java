package Core;

import Components.Event.MouseObserver;
import Components.Graphics.Drawable;
import Components.Graphics.Sprite;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by dimaer on 24/03/17.
 * La classe base di tutti le entita' che interagiscono con utente
 */
public class GameObject implements Drawable, MouseObserver {

    private boolean selected;

    public String name;
    private Sprite sprite;

    private Point position;
    private State state;

    private boolean visibility;
    private boolean active;

    private Rectangle boundRect;

    public GameObject(){}

    public GameObject(Point position)
    {
        state = new State(State.Type.active);
        active = true;
        visibility = true;
        this.position = position;

        sprite = new Sprite(position);
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }


    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

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

    public void setSprite(String Team,String id){
        sprite.setImage(ResourceLoader.getInstance().LoadSprite(Team,id));
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }

    /**
     * Metodo gettere della posizione di oggetto
     * @return
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

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    @Override
    public void draw(Graphics graphics) {
        if(isVisibility())
        sprite.draw(graphics);
    }

    @Override
    public void update(MouseEvent mouseEvent) {
        if(isActive()) {
            Point mouse = new Point(mouseEvent.getX(), mouseEvent.getY());

            if (mouseEvent.getID() == MouseEvent.MOUSE_MOVED) {


            }
            if (mouseEvent.getID() == MouseEvent.MOUSE_CLICKED) {
                if (getSprite().perPixelCollision(mouse)) {
                    /*if (!isSelected()) {
                        setSelected(true);

                    } else {
                        setSelected(false);
                    }*/

                }

            }
        }
    }
}
