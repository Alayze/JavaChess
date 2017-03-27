package Components.Graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

/**
 * Created by dimaer on 20/03/17.
 * La classe Components.Graphics.Sprite rappresenta il componente principale di rendering
 *
 */

public class Sprite implements Drawable {

    private Point position;

    private Image image;
    private File imageFile;
    
    private ImageObserver imageObserver = new ImageObserver() {
        @Override
        public boolean imageUpdate(Image image, int i0, int i1, int i2, int i3, int i4) {
            return false;
        }
    };

    /**
     *Funzione che torna file di immagine
     * @return file di immagine
     */
    public File getFile()
    {
        return imageFile;
    }

    /**
     * Costruttore che assegna ad ogni istanza la posizione iniziale
     * @param position posizione di locazione di Components.Graphics.Sprite
     */
    public Sprite(Point position)
    {
        this.position=position;
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
            System.out.print(e.getMessage());
        }
    }
    /**
     * Metodo che disegna l'immagine
     * @param graphics
     */
    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(image,position.x,position.y,imageObserver);
    }
}
