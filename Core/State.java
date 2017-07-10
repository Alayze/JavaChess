package Core;

import Components.Graphics.Sprite;

/**
 * Created by dimaer on 07/07/17.
 */
public class State {

    private Type type;

    public State(Type type){
        this.type = type;
    }

    enum Type{
        selected,hover,active
    }

    public Type getType() {
        return type;
    }
}
