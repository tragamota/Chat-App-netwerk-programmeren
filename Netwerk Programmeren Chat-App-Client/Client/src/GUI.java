import javax.swing.*;
import java.awt.*;

/**
 * Created by Ian on 8-5-2017.
 */
public class GUI extends JFrame {

    public GUI() {
        super("MasterSpeak");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 600));

        ClientPanel panel = new ClientPanel();

        setContentPane(panel);
        setJMenuBar(new ClientMenuBar(panel));
        setVisible(true);
    }

    public static void main(String args[]) {
        new GUI();
    }
}