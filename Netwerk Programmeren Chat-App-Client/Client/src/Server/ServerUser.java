package Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Ian on 8-5-2017.
 */
public class ServerUser implements Serializable, Comparable<ServerUser> {
    private String userName;
    private transient Socket userSocket;
    private transient ObjectInputStream fromObjectClient;
    private transient DataInputStream fromClient;
    private transient ObjectOutputStream toObjectClient;
    private transient DataOutputStream toClient;

    public ServerUser(Socket userSocket, ArrayList<ServerUser> users) {
        this.userSocket = userSocket;
        try {
            toObjectClient = new ObjectOutputStream(this.userSocket.getOutputStream());
            toObjectClient.flush();
            fromObjectClient = new ObjectInputStream(this.userSocket.getInputStream());
            toClient = new DataOutputStream(this.userSocket.getOutputStream());
            fromClient = new DataInputStream(this.userSocket.getInputStream());
            while(userName == null) {
                userName = fromClient.readUTF();
            }
            toObjectClient.writeObject(users);
            toClient.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectInputStream getFromObjectClient() {
        return fromObjectClient;
    }

    public DataInputStream getFromClient() {
        return fromClient;
    }

    public ObjectOutputStream getToObjectClient() {
        return toObjectClient;
    }

    public DataOutputStream getToClient() {
        return toClient;
    }

    @Override
    public int compareTo(ServerUser o) {
        return userName.compareTo(o.userName);
    }
}