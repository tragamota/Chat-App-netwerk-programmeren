package Client.Listeners;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Ian on 2-6-2017.
 */
public class MessageSender implements Runnable {
    private String message;
    private ObjectOutputStream outputStream;

    public MessageSender(String message, ObjectOutputStream outputStream) {
        this.message = message;
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        try {
            System.out.println("Sending message");
            outputStream.writeUTF(message);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
