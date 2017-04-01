package Core;

/**
 * Created by dimaer on 31/03/17.
 */
public class Weather {
    private WEATHER_TYPE currentType;
    private Weather(){

    }

    private static Weather weather = new Weather();

    public static Weather getInstance(){
        return weather;
    }
    public void setWeather(WEATHER_TYPE weatherType){
        currentType = weatherType;
    }
    public WEATHER_TYPE getWeather(){
        return currentType;
    }
    enum WEATHER_TYPE{
        AUTUMN,WINTER,SPRING,SUMMER
    }
}
