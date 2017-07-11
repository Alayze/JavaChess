package Components.Graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

/**
 * Created by dimaer on 20/03/17.
 * La classe Sprite rappresenta il componente principale di rendering
 *
 */

public class Sprite implements Drawable {

    private Point position;
    private Point origin;
    private BufferedImage image;
    private File imageFile;
    private int depth;
    private ImageObserver imageObserver;
    private boolean visibility;

    /**
     *Funzione che torna file di immagine
     * @return file di immagine
     */
    public File getFile()
    {
        return imageFile;
    }

    /**
     *Costruttore di Sprite
     */
    public Sprite(){

        position = new Point(0,0);

        visibility = true;

        imageObserver = new ImageObserver() {
            @Override
            public boolean imageUpdate(Image image, int i0, int i1, int i2, int i3, int i4) {
                return false;

            }
        };
    }
    /**
     * Costruttore che assegna ad ogni istanza la posizione iniziale
     * @param position posizione di locazione di Components.Graphics.Sprite
     */
    public Sprite(Point position)
    {
        visibility = true;

        this.position = position;

        imageObserver = new ImageObserver() {
            @Override
            public boolean imageUpdate(Image image, int i0, int i1, int i2, int i3, int i4) {
                return false;

            }
        };
    }

    /**
     * Metodo che assegna file di immagine al oggetto di tipo Image
     * @param path indirizzo del file nel file system
     */
    public void setImage(String path)
    {
        imageFile = new File(path);
        try {
            image = ImageIO.read(imageFile);

        }catch (IOException e){
            System.out.println(e.getMessage() +    " " + path);
        }

    }

    /**
     * Metodo che torna immagine impostata
     * @return immagine impostata
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Metodo che torna la posizione corrente
     * @return vettore di posizione corrente
     */
    public Point getPosition() {
        return position;
    }

    /**
     *Il metodo che imposta la posizione di sprite sullo schermo
     * @param vector la posizione da impostare
     */
    public void setPosition(Point vector){position = vector;}
    /**
     * Il metodo che calcola le coordinate del centro di sprite rispetto all'immagine
     * @return le coordinate dell centro
     */
    public Point getCenter(){
        Point imageCenter = new Point(image.getWidth(imageObserver)/2,image.getHeight(imageObserver)/2);

        return new Point(position.x + imageCenter.x,position.y + imageCenter.y - 20);
    }

    /**
     *Metodo che imposta le coordinate d'origine di sprite
     * @param origin le coordinate nuove d'origine
     */
    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    /**
     *Metodo che torna le cooridinate d'origine
     * @return coordinate d'origine
     */
    public Point getOrigin() {
        return origin;
    }

    /**
     *Metodo che imposta la visibilita di sprite sullo schermo
     * @param visibility flag di visibilita
     */
    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    /**
     *Metodo che torna flag di visibilita'
     * @return
     */
    public boolean isVisibility() {
        return visibility;
    }

    /**
     *Metodo che verifica se il punto sullo schermo e' contenuto nella regione di sprite
     * @param point le coordinate del punto
     * @return true se e' contenuto , false se il punto e' fuori
     */
    public boolean isContainPoint(Point point){
        if(point.y >= getPosition().y && point.y <= image.getHeight() + getPosition().y)
            if(point.x >= getPosition().x && point.x <= image.getWidth() + getPosition().x)
                return true;

        return false;
    }

    /**
     *Metodo che verifica se il punto sullo schermo e' sovrapposto sui pixel di sprite
     * @param point le coordinate del punto
     * @return true se e' contenuto , false se il punto e' fuori
     */
    public boolean perPixelCollision(Point point){
        if(isContainPoint(point))
        {
            Point coord = new Point(point.x - getPosition().x,point.y - getPosition().y);
            if(coord.x<=0)
                coord.x = 0;
            if(coord.y<=0)
                coord.y = 0;

            int colour = getImage().getRGB(coord.x,coord.y);

                /*int  red = (colour & 0x00ff0000) >> 16;
                int  green = (colour & 0x0000ff00) >> 8;
                int  blue = colour & 0x000000ff;*/
            int alpha = (colour>>24) & 0xff;

            if(alpha!=0){
                return true;
            }

        }
        return false;
    }
    /**
     * Metodo che torna l'ordine di rendering
     * @return ordine di render
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Metodo che imposta l'ordine di rendering
     * @param depth l'ordine di rendering da impostare
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }
    /**
     * Metodo che disegna sprite
     * @param graphics
     */
    @Override
    public void draw(Graphics graphics){
        if(visibility)
        graphics.drawImage(image,position.x,position.y,imageObserver);

    }
}
