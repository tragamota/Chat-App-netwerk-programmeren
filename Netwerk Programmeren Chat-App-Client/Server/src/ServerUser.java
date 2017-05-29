import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Ian on 8-5-2017.
 */
public class ServerUser {
    private String userName;
    private Socket userSocket;
    private DataInputStream input;
    private DataOutputStream output;

    public ServerUser(Socket userSocket) {
        this.userSocket = userSocket;
        try {
            input = new DataInputStream(this.userSocket.getInputStream());
            output = new DataOutputStream(this.userSocket.getOutputStream());
            while (userName == null) {
                userName = input.readUTF();
            }
            output.writeUTF("Welkom " + userName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {
            output.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPing(int timeInMS) {
        try {
            output.writeInt(timeInMS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}