import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Ian on 8-5-2017.
 */
public class ClientUser {
    private String userName;
    private ClientIO io;

    public ClientUser() {

    }

    public void connectToServer(String address, int port, String username) {
        try {
            if(io == null) {
                this.userName = username;
                io = new ClientIO(new Socket(address, port));
                io.getOutputStream().writeUTF(username);
                System.out.println(io.getInputStream().readUTF());
            }
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Host or port incorect. Please try again!",
                    "Connection error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void disconnectFromServer() {
        if(io != null) {
            io.disconnect();
            io = null;
        }
    }

    public ClientIO getIO() {
        return io;
    }
}