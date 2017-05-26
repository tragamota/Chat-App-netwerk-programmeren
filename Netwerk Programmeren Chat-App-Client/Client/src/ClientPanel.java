import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian on 8-5-2017.
 */
public class ClientPanel extends JPanel {
    private ClientUser user;
    private List<ServerUser> connectedClients;
    //private List<Chat> chats;

    public ClientPanel() {
        connectedClients = new ArrayList<>();
        user = new ClientUser(connectedClients);
    }

    public ClientUser getUser() {
        return user;
    }
}
