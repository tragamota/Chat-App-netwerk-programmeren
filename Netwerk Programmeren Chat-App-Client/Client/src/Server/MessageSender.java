package Server;

import java.io.IOException;
import java.util.List;

/**
 * Created by Ian on 2-6-2017.
 */
public class MessageSender implements Runnable{
    private Chat message;
    private List<ServerUser> users;

    public MessageSender(List<ServerUser> users, Chat message) {
        this.users = users;
        this.message = message;
    }

    @Override
    public void run() {
        for(ServerUser user : users) {
            try {
                user.getToObjectClient().writeObject(message);
                user.getToObjectClient().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
