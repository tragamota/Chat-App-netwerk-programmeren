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

    public Main() {
        connectedUsers = new ArrayList<>();
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
                Socket socket = serverSocket.accept();
                ServerUser serverUser = new ServerUser(socket, (ArrayList<ServerUser>) connectedUsers);
                connectedUsers.add(serverUser);
                System.out.println(connectedUsers);
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