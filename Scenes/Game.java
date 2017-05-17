package Scenes;

import Core.Scene;
import Core.Weather;
import Terrain.Board;

import java.awt.*;

/**
 * Created by dimaer on 17/05/17.
 */
public final class Game extends Scene {
    Board board;

    @Override
    public void Init() {
        setSceneType(SCENE_TYPE.RUNNED_GAME);
        board = new Board(new Point(400,-200),new Weather(Weather.WEATHER_TYPE.Winter));
        addElement(board);
    }

    @Override
    public void Update() {

    }
}
