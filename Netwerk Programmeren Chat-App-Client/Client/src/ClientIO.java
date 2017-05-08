import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Ian on 8-5-2017.
 */
public class ClientIO {
    private Socket clientSocket;
    private DataInputStream input;
    private DataOutputStream output;

    public ClientIO(Socket socket) {
        try {
            clientSocket = socket;
            input = new DataInputStream(clientSocket.getInputStream());
            output = new DataOutputStream(clientSocket.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataInputStream getInputStream() {
        return input;
    }

    public DataOutputStream getOutputStream() {
        return output;
    }

    public void disconnect() {
        try {
            clientSocket.close();
            input.close();
            output.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}