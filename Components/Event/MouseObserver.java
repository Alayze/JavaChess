package Components.Event;
import java.awt.event.MouseEvent;
/**
 * Created by dimaer on 22/03/17.
 */
/**Fare refactoring e verificare i tipi*/
public interface MouseObserver {
    void update(MouseEvent mouseEvent,String message);
}
