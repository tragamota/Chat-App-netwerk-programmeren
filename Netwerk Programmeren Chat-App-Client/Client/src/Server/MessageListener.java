package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian on 2-6-2017.
 */
public class MessageListener implements Runnable {
    private List<ServerUser> conUsers;
    private ObjectInputStream fromClient;

    public MessageListener(List<ServerUser> connectedUsers, ObjectInputStream fromClient) {
        conUsers = connectedUsers;
        this.fromClient = fromClient;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String input = fromClient.readUTF();
                String[] inputSplit = input.split("-");
                new Thread(new MessageSender(conUsers, new Chat(inputSplit[0], inputSplit[1], inputSplit[2]))).start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
