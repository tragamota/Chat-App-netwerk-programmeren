import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Ian on 8-5-2017.
 */
public class Main {
    private final int port = 8000;
    private ServerSocket serverSocket;

    public Main() {
        try {
            serverSocket = new ServerSocket(port);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        run();
    }

    private void run() {
        while(true) {
            try {
                System.out.println();
                Socket socket = serverSocket.accept();
                new ServerUser(socket);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String args[]) {
        new Main();
    }

}