package Scenes;

import Actors.Team;
import Components.Graphics.Gui.*;
import Components.Graphics.Gui.Label;
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
        board = new Board(new Point(350,-150),new Weather(Weather.WEATHER_TYPE.Winter));
        Team team_red = new Team(Team.TEAMTYPE.Red,board);
        Team team_blue = new Team(Team.TEAMTYPE.Blue,board);
        Label label_turn = new Label("Turno di giocatore :",new Point(100,100));
        Label label_season = new Label("Stagione :",new Point(400,50));
        addElement(board);
        addElement(team_blue);
        addElement(label_turn);
        addElement(label_season);

    }

    @Override
    public void Update() {

    }
}
