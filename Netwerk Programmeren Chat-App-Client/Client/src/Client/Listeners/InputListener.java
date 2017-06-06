package Client.Listeners;

import Server.Chat;
import Server.ServerUser;

import javax.swing.*;
import java.awt.*;
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
    private JTextArea messageView;
    private String tekst;

    public InputListener(ObjectInputStream fromServer, List<ServerUser> connectedUsers, List<Chat> chats, JTextArea mesView) {
        this.fromServer = fromServer;
        this.connectedUsers = connectedUsers;
        this.chats = chats;
        this.messageView = mesView;
        this.tekst = "";
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
                        chats.add(chat);
                        Collections.sort(chats, Chat.getComparator());
                        for(Chat c : chats){
                            if(tekst.equals("")){
                                tekst = c.getTimeFromSending() + " " + c.getMessageFrom() + " ; \n" + c.getMessage() + "\n";
                            }else {
                                tekst += c.getTimeFromSending() + " " + c.getMessageFrom() + " ;\n" + c.getMessage() + "\n";
                            }
                        }
                        messageView.setText(tekst);
                        tekst= "";
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