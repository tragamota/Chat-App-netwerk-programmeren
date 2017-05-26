import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * Created by Ian on 8-5-2017.
 */
public class ServerUser implements Serializable {
    private String userName;
    private transient Socket userSocket;
    private transient DataInputStream fromClientData;
    private transient DataOutputStream toClientData;
    private transient ObjectInputStream fromClientObject;
    private transient ObjectOutputStream toClientObject;

    public ServerUser(Socket userSocket, List<ServerUser> connectUsers) {
        this.userSocket = userSocket;
        try {
            fromClientData = new DataInputStream(this.userSocket.getInputStream());
            toClientData = new DataOutputStream(this.userSocket.getOutputStream());
            toClientObject = new ObjectOutputStream(this.userSocket.getOutputStream());
            toClientObject.flush();
            fromClientObject = new ObjectInputStream(this.userSocket.getInputStream());
            while (userName == null) {
                userName = fromClientData.readUTF();
            }
            toClientObject.writeObject(connectUsers);
            toClientObject.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUserName() {
        return userName;
    }

    public DataInputStream getFromClientData() {
        return fromClientData;
    }

    public DataOutputStream getToClientData() {
        return toClientData;
    }

    public ObjectInputStream getFromClientObject() {
        return fromClientObject;
    }

    public ObjectOutputStream getToClientObject() {
        return toClientObject;
    }

    public class CommandListener implements Runnable{

        @Override
        public void run() {

        }
    }
}