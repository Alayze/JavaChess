/**
 * Created by dimaer on 16/03/17.
 */
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args)
    {
        Screen screen = new Screen(400,400);
        System.out.print("Program Started");

        Frame mainFrame = new Frame("ChessGame");
        mainFrame.add(screen);



        mainFrame.setSize(400,400);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        mainFrame.setVisible(true);



    }
}
