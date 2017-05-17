package Components.Event;
import java.awt.event.MouseEvent;
/**
 * Created by dimaer on 22/03/17.
 */
/**Сделать рефактор имени.Делать проверку типов*/
public interface MouseObserver {
    void update(MouseEvent mouseEvent,String message);
}
