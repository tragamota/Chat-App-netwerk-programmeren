import javax.swing.*;

/**
 * Created by Ian on 8-5-2017.
 */
public class ClientPanel extends JPanel {
    private ClientUser user;

    public ClientPanel() {
        user = new ClientUser();
    }

    public ClientUser getUser() {
        return user;
    }
}
