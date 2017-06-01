package Client;

import Server.ServerUser;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ian on 1-6-2017.
 */
public class ConnectedUsersListener implements Runnable {
    private ObjectInputStream fromServer;
    private ArrayList<ServerUser> connectedUsers;

    public ConnectedUsersListener(ObjectInputStream fromServer, ArrayList<ServerUser> connectedUsers) {
        this.fromServer = fromServer;
        this.connectedUsers = connectedUsers;
    }

    @Override
    public void run() {
        while(fromServer != null) {
            try {
                Object inputObject = fromServer.readObject();
                System.out.println(inputObject);
                if(inputObject instanceof List) {
                    ArrayList<ServerUser> listToAdd = (ArrayList<ServerUser>) inputObject;
                    connectedUsers.clear();
                    connectedUsers.addAll(listToAdd);
                    //TODO Sort the list on username.
                    Collections.sort(connectedUsers);
                }
            } catch (IOException e) {
                e.printStackTrace();
                fromServer = null;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}