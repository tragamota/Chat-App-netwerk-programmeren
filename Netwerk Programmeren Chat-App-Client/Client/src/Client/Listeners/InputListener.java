package Client.Listeners;

import Server.Chat;
import Server.ServerUser;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by Ian on 1-6-2017.
 */
public class InputListener implements Runnable {
    private ObjectInputStream fromServer;
    private List<ServerUser> connectedUsers;
    private List<Chat> chats;

    public InputListener(ObjectInputStream fromServer, List<ServerUser> connectedUsers, List<Chat> chats) {
        this.fromServer = fromServer;
        this.connectedUsers = connectedUsers;
        this.chats = chats;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (fromServer) {
                try {
                    Object inputObject = fromServer.readObject();
                    if (inputObject instanceof List) {
                        List<ServerUser> listToAdd = (List<ServerUser>) inputObject;
                        connectedUsers.clear();
                        connectedUsers.addAll(listToAdd);
                        Collections.sort(connectedUsers);
                    }
                    else if(inputObject instanceof Chat) {
                        Chat chat = (Chat) inputObject;
                        System.out.println(chat.getMessage());
                        chats.add((Chat) inputObject);
                        Collections.sort(chats);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}