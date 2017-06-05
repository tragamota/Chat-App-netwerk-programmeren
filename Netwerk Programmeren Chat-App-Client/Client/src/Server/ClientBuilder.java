package Server;

import java.util.List;

/**
 * Created by Ian on 5-6-2017.
 */
public class ClientBuilder implements Runnable {
    private ServerUser user;
    private List<ServerUser> connectedUsers;

    public ClientBuilder(ServerUser user, List<ServerUser> connectedUsers) {
        this.user = user;
        this.connectedUsers = connectedUsers;
    }
    @Override
    public void run() {
        user.buildStreams(connectedUsers);
        new Thread(new UpdateAllClients(connectedUsers)).start();
        new Thread(new MessageListener(connectedUsers, user.getFromObjectClient())).start();
    }
}
