package Components.Graphics;

import java.awt.*;

/**Interfaccia principale per tutte le entita' che devono essere disegnati
 * Created by dimaer on 19/03/17.
 */
public interface Drawable {
     /**
      *Metodo che disegna sul canvas
      * @param graphics instanza di Graphics
      */
     void draw(Graphics graphics);
}
