package Client;

import Client.Listeners.InputListener;
import Server.Chat;
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
    private ObjectOutputStream toServerObject;
    private ObjectInputStream fromServerObject;

    private List<ServerUser> connectedClients;
    private List<Chat> chats;

    public ClientUser(List<ServerUser> connectedClients, List<Chat> chats) {
        this.connectedClients = connectedClients;
        this.chats = chats;
        userName = null;
        toServerObject = null;
        fromServerObject = null;
    }

    public void connectToServer(String address, int port, String username) {
        try {
            if(clientSocket == null) {
                this.userName = username;
                clientSocket = new Socket(address, port);
                toServerObject = new ObjectOutputStream(this.clientSocket.getOutputStream());
                toServerObject.flush();
                fromServerObject = new ObjectInputStream(this.clientSocket.getInputStream());
                toServerObject.writeUTF(username);
                toServerObject.flush();
                List<ServerUser> serverUsers = (List<ServerUser>) fromServerObject.readObject();
                connectedClients.addAll(serverUsers);
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
        new Thread(new InputListener(fromServerObject, connectedClients, chats)).start();
    }

    public void disconnectFromServer() {
        if(clientSocket != null) {
            try {
                //closing connections
                clientSocket.close();
                fromServerObject.close();
                toServerObject.close();
                //cleaning the variables
                clientSocket = null;
                fromServerObject = null;
                toServerObject = null;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getUserName() {
        return userName;
    }

    public ObjectOutputStream getToServerObject() {
        return toServerObject;
    }

    public ObjectInputStream getFromServerObject() {
        return fromServerObject;
    }
}