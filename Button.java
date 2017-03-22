/**
 * Created by dimaer on 19/03/17.
 */
import java.awt.*;

public class Button implements Drawable{
    private Rectangle boundRect;

    public Button(Rectangle BoundRect)
    {
        boundRect = BoundRect;
    }
    public void setBoundRect(Rectangle BoundRect)
    {
        boundRect = BoundRect;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawRect(boundRect.x,boundRect.y,boundRect.width,boundRect.height);
    }
}
