package Core;

/**
 * Created by dimaer on 05/04/17.
 */
public final class GameManager {
    Screen screen;

    private GameManager(){

    }
    private static final GameManager gm = new GameManager();

    public static GameManager getInstance(){
        return gm;
    }
    public static void Run(){

    }
}
