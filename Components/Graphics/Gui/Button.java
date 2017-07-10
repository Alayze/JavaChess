package Components.Graphics.Gui; /**
 * Created by dimaer on 19/03/17.
 */
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Components.Event.MouseObserver;
import Components.Graphics.Drawable;

public class Button implements Drawable,MouseObserver {
    private Rectangle boundRect;
    private MouseListener mouseListener;
    private String label;
    /**
     * Costruttore
     * @param BoundRect Dimensioni di rettangolo
     */
    public Button(Rectangle BoundRect,String label)
    {
        boundRect = BoundRect;
        this.label=label;
    }

    /**
     *
     * @param label
     */
    public void setLabel(String label){

    }
    /**
     * Metodo che imposta le dimensioni di rettangolo
     * @param BoundRect Dimensioni di rettangolo
     */
    public void setBoundRect(Rectangle BoundRect)
    {
        boundRect = BoundRect;
    }

    /**
     * Metodo che aggiorna il componente quando esso riceve il messagio (L'evento)
     * @param mouseEvent L'evento di mouse
     *
     */
    @Override
    public void update(MouseEvent mouseEvent) {
        if(mouseEvent.getID()==MouseEvent.MOUSE_CLICKED)
        {
            if(isContainMouse(mouseEvent))
            mouseListener.mouseClicked(mouseEvent);

        }
        if(mouseEvent.getID()==MouseEvent.MOUSE_MOVED)
        {
            if(isContainMouse(mouseEvent))
            mouseListener.mouseEntered(mouseEvent);
        }
    }

    /**
     *
     * @param mouseListener
     */
    public void addMouseListener(MouseListener mouseListener){
        this.mouseListener = mouseListener;
    }

    /**
     * Metodo che verifica se il puntatore di mouse e' contenuto nel componente
     * @param mouseEvent L'evento di mouse
     * @return true se e' contenuto nel componente
     */
    private boolean isContainMouse(MouseEvent mouseEvent)
    {
        if(mouseEvent.getX() > boundRect.getX() && mouseEvent.getX() < boundRect.getX() + boundRect.width )
            if (mouseEvent.getY() > boundRect.getY() && mouseEvent.getY() < boundRect.getY() + boundRect.height )
                return true;
        return false;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawRect(boundRect.x,boundRect.y,boundRect.width,boundRect.height);
        FontMetrics fm = graphics.getFontMetrics();

        graphics.drawString(label,boundRect.x + boundRect.width/2 - fm.stringWidth(label)/2,boundRect.y + boundRect.height/2 + fm.getHeight()/2);
    }
}
