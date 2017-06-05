package Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian on 8-5-2017.
 */
public class ServerUser implements Serializable, Comparable<ServerUser> {
    private String userName;
    private transient Socket userSocket;
    private transient ObjectInputStream fromObjectClient;
    private transient ObjectOutputStream toObjectClient;

    public ServerUser(Socket userSocket) {
        this.userSocket = userSocket;
    }

    public void buildStreams(List<ServerUser> users) {
        try {
            //ObjectStreams
            toObjectClient = new ObjectOutputStream(this.userSocket.getOutputStream());
            toObjectClient.flush();
            fromObjectClient = new ObjectInputStream(this.userSocket.getInputStream());
            //read username
            userName = fromObjectClient.readUTF();
            //send all connectedUsers
            toObjectClient.writeObject(users);
            toObjectClient.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectInputStream getFromObjectClient() {
        return fromObjectClient;
    }

    public ObjectOutputStream getToObjectClient() {
        return toObjectClient;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public int compareTo(ServerUser o) {
        return userName.compareTo(o.userName);
    }
}