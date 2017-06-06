package Client;

import Client.GUI.UsersListTable;
import Client.Listeners.MessageSender;
import Server.Chat;
import Server.ServerUser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ian on 8-5-2017.
 */
public class ClientPanel extends JPanel implements ActionListener {
    private ClientUser user;
    private List<ServerUser> connectedClients;
    private List<Chat> chats;

    private JTextArea messageSend;
    private JTextArea messageView;
    private JButton sendButton;
    private UsersListTable UsersList;

    public ClientPanel() {
        super(new BorderLayout());
        connectedClients = Collections.synchronizedList(new ArrayList<>());
        chats = new ArrayList<>();


        JPanel middelPanel = new JPanel(new FlowLayout());
        UsersList = new UsersListTable(connectedClients);
        JScrollPane scrollPane = new JScrollPane(UsersList);
        scrollPane.setPreferredSize(new Dimension(200, 325));
        middelPanel.add(scrollPane);

        JPanel southPanel = new JPanel(new FlowLayout());
        sendButton = new JButton("Send message");
        sendButton.addActionListener(e -> sendMessage());
        messageSend = new JTextArea(4, 24);
        messageSend.setLineWrap(true);
        messageSend.setWrapStyleWord(true);

        southPanel.add(new JScrollPane(messageSend));
        southPanel.add(sendButton);
        add(southPanel, BorderLayout.SOUTH);

        messageView = new JTextArea(20 , 40);
        messageView.setLineWrap(true);
        messageView.setWrapStyleWord(true);
        messageView.setEditable(false);
        middelPanel.add(new JScrollPane(messageView));
        add(middelPanel, BorderLayout.CENTER);
        user = new ClientUser(connectedClients,chats, messageView);

        new Timer(100, this).start();
    }

    public ClientUser getUser() {
        return user;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UsersList.updateTable();
        System.out.println(chats);
    }

    public void sendMessage() {
        String message = messageSend.getText() + "-" + System.currentTimeMillis() + "-" + user.getUserName();
        new Thread(new MessageSender(message, user.getToServerObject())).start();
        messageSend.setText("");
    }
}