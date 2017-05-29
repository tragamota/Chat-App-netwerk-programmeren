import javax.swing.*;
import java.awt.*;

/**
 * Created by Ian on 8-5-2017.
 */
public class ClientConnect extends JPanel {
    private JFrame frame;
    private ClientUser user;

    private JTextField adress, port, userName;

    public ClientConnect(ClientUser user) {
        super(new BorderLayout());
        this.user = user;
        frame = new JFrame("Connect");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setMinimumSize(new Dimension(300, 150));

        buildPanel();

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setContentPane(this);
        frame.pack();
        frame.setVisible(true);
    }

    private void buildPanel() {
        JPanel bottomButtons = new JPanel();
        JButton connect = new JButton("Connect");
        JButton cancel = new JButton("Cancel");

        connect.addActionListener(e -> proceed());
        cancel.addActionListener(e -> cancel());

        bottomButtons.add(connect);
        bottomButtons.add(cancel);
        add(bottomButtons, BorderLayout.SOUTH);

        JPanel adressPanel = new JPanel();
        adress = new JTextField(6);
        port = new JTextField(4);
        userName = new JTextField(5);
        adressPanel.add(adress);
        adressPanel.add(port);
        add(adressPanel, BorderLayout.NORTH);

        JPanel userNamePanel = new JPanel();
        userNamePanel.add(userName);
        add(userNamePanel, BorderLayout.CENTER);
    }

    private void proceed() {
        user.connectToServer(adress.getText(), Integer.parseInt(port.getText()), userName.getText());
        frame.dispose();
    }

    private void cancel() {
        frame.dispose();
    }
}