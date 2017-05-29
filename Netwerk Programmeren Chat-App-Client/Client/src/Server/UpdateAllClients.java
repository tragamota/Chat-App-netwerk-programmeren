package Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian on 29-5-2017.
 */
public class UpdateAllClients implements Runnable {
    private List<ServerUser> connectedUsers, users;

    public UpdateAllClients(List<ServerUser> connectedUsers) {
        this.connectedUsers = new ArrayList<>();
        this.connectedUsers.addAll(connectedUsers);
        this.users = connectedUsers;
    }

    @Override
    public void run() {
        updateAllUsers(connectedUsers);
    }

    public void updateAllUsers(List<ServerUser> users) {
        ObjectOutputStream outputStream = users.get(0).getToObjectClient();
        if(users.isEmpty()) {
            return;
        }
        else {
            try {
                users.get(0).getToObjectClient().writeObject(this.users);
                users.remove(0);
                updateAllUsers(users);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}