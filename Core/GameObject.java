package Core;

import java.awt.*;

/**
 * Created by dimaer on 24/03/17.
 * La classe base di tutti le entita' che interagiscono con utente
 */
public class GameObject{
    Point position;
    Rectangle boundRect;
    public GameObject(){}
    public GameObject(Point position)
    {
        this.position = position;
    }

    /**
     * Metodo setter della posizione di oggetto
     * @param position
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * Metodo che imposta la regione attiva di oggetto
     * @param boundRect rettangolo della regione
     */
    public void setBoundRect(Rectangle boundRect) {
        this.boundRect = boundRect;
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
}
