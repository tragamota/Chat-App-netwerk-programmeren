package Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian on 29-5-2017.
 */
public class UpdateAllClients implements Runnable {
    private List<ServerUser> connectedUsers;

    public UpdateAllClients(List<ServerUser> connectedUsers) {
        this.connectedUsers = new ArrayList<>();
        this.connectedUsers.addAll(connectedUsers);
    }

    @Override
    public void run() {
        updateAllUsers(connectedUsers.size() - 1, connectedUsers);
    }

    public void updateAllUsers(int index, List<ServerUser> connectedUsers) {
        if(index < 0) {
            return;
        }
        else {
            ServerUser user = connectedUsers.get(0);
            connectedUsers.remove(0);
            try {
                user.getToObjectClient().writeObject(connectedUsers);
                user.getToObjectClient().flush();
                connectedUsers.add(user);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                updateAllUsers(index - 1, connectedUsers);
            }
        }
    }
}