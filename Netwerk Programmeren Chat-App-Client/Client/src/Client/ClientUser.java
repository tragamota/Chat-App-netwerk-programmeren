package Client;

import Server.ServerUser;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * Created by Ian on 8-5-2017.
 */
public class ClientUser {
    private String userName;
    private Socket clientSocket;
    private DataInputStream fromServerData;
    private DataOutputStream toServerData;
    private ObjectOutputStream toServerObject;
    private ObjectInputStream fromServerObject;

    private List<ServerUser> connectedClients;

    public ClientUser(List<ServerUser> connectedClients) {
        this.connectedClients = connectedClients;
        userName = null;
        fromServerData = null;
        toServerData = null;
        toServerObject = null;
        fromServerObject = null;
    }

    public void connectToServer(String address, int port, String username) {
        try {
            if(clientSocket == null) {
                this.userName = username;
                clientSocket = new Socket(address, port);
                fromServerData = new DataInputStream(this.clientSocket.getInputStream());
                toServerData = new DataOutputStream(this.clientSocket.getOutputStream());
                toServerObject = new ObjectOutputStream(this.clientSocket.getOutputStream());
                toServerObject.flush();
                fromServerObject = new ObjectInputStream(this.clientSocket.getInputStream());
                toServerData.writeUTF(username);
                toServerData.flush();
                connectedClients = (List<ServerUser>) fromServerObject.readObject();
                System.out.println(connectedClients);
            }
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Host or port incorrect. Please try again!",
                    "Connection error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void disconnectFromServer() {
        if(clientSocket != null) {
            try {
                //closing connections
                clientSocket.close();
                fromServerData.close();
                fromServerObject.close();
                toServerData.close();
                toServerObject.close();
                //cleaning the variables
                clientSocket = null;
                fromServerData = null;
                fromServerObject = null;
                toServerData = null;
                toServerObject = null;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public DataInputStream getFromServerData() {
        return fromServerData;
    }

    public DataOutputStream getToServerData() {
        return toServerData;
    }

    public ObjectOutputStream getToServerObject() {
        return toServerObject;
    }

    public ObjectInputStream getFromServerObject() {
        return fromServerObject;
    }
}