/**
 * Created by dimaer on 16/03/17.
 */
import Core.Screen;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args)
    {
        Screen screen = new Screen();
        System.out.print("Program Started");

        Frame mainFrame = new Frame("ChessGame");
        mainFrame.add(screen);



        mainFrame.setSize(1024,768);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        mainFrame.setVisible(true);



    }
}
