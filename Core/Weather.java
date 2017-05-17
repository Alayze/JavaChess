package Core;

import Components.Graphics.Drawable;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dimaer on 31/03/17.
 */
public final class Weather {

    private WEATHER_TYPE currentType;
    Timer timer;
    ArrayList<WeatherObserver> weatherObservers;

    public Weather(WEATHER_TYPE weather){
        weatherObservers = new ArrayList<>();
        currentType=weather;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                switchWeather();
                notifyObservers();
                System.out.print(currentType.toString()+"\n");
            }
        },5*1000,5*1000);
    }

    //private static Weather weather = new Weather();

    /*public static Weather getInstance(){
        return weather;
    }*/
    public void notifyObservers(){
            for(WeatherObserver w : weatherObservers)
            {
                w.setSprite();
            }
    }
    public void addWeatherObserver(WeatherObserver weatherObserver){
        this.weatherObservers.add(weatherObserver);
    }

    private void setWeather(WEATHER_TYPE weatherType){
        currentType = weatherType;
    }

    public void switchWeather(){
        if(currentType==WEATHER_TYPE.Autumn){
            setWeather(WEATHER_TYPE.Winter);
            return;
        }if(currentType==WEATHER_TYPE.Winter){
            setWeather(WEATHER_TYPE.Spring);
            return;
        }if(currentType==WEATHER_TYPE.Spring){
            setWeather(WEATHER_TYPE.Summer);
            return;
        }if(currentType==WEATHER_TYPE.Summer) {
            setWeather(WEATHER_TYPE.Autumn);
            return;
        }
    }

    public WEATHER_TYPE getWeather(){
        return currentType;
    }
    public enum WEATHER_TYPE{
        Autumn,Winter,Spring,Summer
    }
}
