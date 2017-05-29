import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ian on 8-5-2017.
 */
public class Main {
    private final int port = 8000;
    private ServerSocket serverSocket;
    private List<ServerUser> connectedUsers;

    public Main() {
        try {
            serverSocket = new ServerSocket(port);
            connectedUsers = Collections.synchronizedList(new ArrayList<ServerUser>());
            System.out.println(serverSocket.getInetAddress().getHostAddress());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void run() {
        while(true) {
            try {
                Socket socket = serverSocket.accept();
                connectedUsers.add(new ServerUser(socket, connectedUsers));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        new Main().run();
    }

}