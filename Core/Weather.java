package Core;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dimaer on 31/03/17.
 * Classe che gestisce l'entita' delle stagioni.
 */
public final class Weather {

    private WEATHER_TYPE currentType;
    private Timer timer;
    private ArrayList<WeatherObserver> weatherObservers;

    /**
     *
     * @param weather
     */
    public Weather(WEATHER_TYPE weather){
        weatherObservers = new ArrayList<>();
        currentType = weather;

        /*Aggironamento di stagione*/
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                switchWeather();
                notifyObservers();
                //System.out.print(currentType.toString()+"\n");
            }
        },5*1000,5*1000);
    }

    /**
     * Metodo che porta le notifiche ad oservatori
     */
    public void notifyObservers(){
            for(WeatherObserver w : weatherObservers)
            {
                w.setSprite();
            }
    }

    /**
     * Metodo che aggiunge oservatori al array
     * @param weatherObserver osservatore da aggiungere
     */
    public void addWeatherObserver(WeatherObserver weatherObserver){
        this.weatherObservers.add(weatherObserver);
    }

    /**
     * Setter per il tempo
     * @param weatherType stagione
     */
    private void setWeather(WEATHER_TYPE weatherType){
        currentType = weatherType;
    }

    /**
     * Metodo che cambia il tempo corrente
     */
    public void switchWeather(){
        if(currentType == WEATHER_TYPE.Autumn){
            setWeather(WEATHER_TYPE.Winter);
            return;//Serve per uscire dal metodo
        }if(currentType == WEATHER_TYPE.Winter){
            setWeather(WEATHER_TYPE.Spring);
            return;
        }if(currentType == WEATHER_TYPE.Spring){
            setWeather(WEATHER_TYPE.Summer);
            return;
        }if(currentType == WEATHER_TYPE.Summer) {
            setWeather(WEATHER_TYPE.Autumn);
            return;
        }
    }

    /**
     * Getter della stagione
     * @return enum la stagione
     */
    public WEATHER_TYPE getWeather(){
        return currentType;
    }

    /**
     *
     */
    public enum WEATHER_TYPE{
        Autumn,Winter,Spring,Summer
    }
}
