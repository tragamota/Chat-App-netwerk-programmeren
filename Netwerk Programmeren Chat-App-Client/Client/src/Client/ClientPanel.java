package Client;

import Server.ServerUser;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian on 8-5-2017.
 */
public class ClientPanel extends JPanel {
    private ClientUser user;
    private List<ServerUser> connectedClients;
    //private List<Chat> chats;

    private JTable listConnectedUsers;

    public ClientPanel() {
        super(new BorderLayout());
        connectedClients = new ArrayList<>();
        user = new ClientUser(connectedClients);

        listConnectedUsers = new JTable();
        listConnectedUsers.ad;



    }

    public ClientUser getUser() {
        return user;
    }
}