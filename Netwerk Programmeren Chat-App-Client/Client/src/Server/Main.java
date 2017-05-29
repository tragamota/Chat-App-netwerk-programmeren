package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Created by Ian on 8-5-2017.
 */
public class Main {
    private final int port = 8000;
    private ServerSocket serverSocket;
    private List<ServerUser> connectedUsers;

    private Thread reupdate;
    private Thread messageReader;

    public Main() {
        connectedUsers = new ArrayList<>();
        messageReader = new Thread();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        run();
    }

    private void run() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                connectedUsers.add(new ServerUser(socket, (ArrayList<ServerUser>) connectedUsers));
                reupdate = new Thread(new UpdateAllClients(connectedUsers));
                reupdate.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        new Main();
    }
}