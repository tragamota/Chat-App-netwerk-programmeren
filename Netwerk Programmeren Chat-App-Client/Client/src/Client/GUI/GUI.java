package Client.GUI;

import Client.ClientPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ian on 8-5-2017.
 */
public class GUI extends JFrame {

    public GUI() {
        super("MasterSpeak");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //setMinimumSize(new Dimension(100, 600));

        Client.ClientPanel panel = new ClientPanel();

        setContentPane(panel);
        setJMenuBar(new ClientMenuBar(panel));
        setVisible(true);
        pack();
    }

    public static void main(String args[]) {
        new GUI();
    }
}