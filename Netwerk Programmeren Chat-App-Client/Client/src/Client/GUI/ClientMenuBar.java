package Client.GUI;

import Client.ClientPanel;

import javax.swing.*;

/**
 * Created by Ian on 8-5-2017.
 */
public class ClientMenuBar extends JMenuBar {
    private Client.ClientPanel panel;

    public ClientMenuBar(ClientPanel panel) {
        super();
        this.panel = panel;

        JMenu connectionMenu = new JMenu("Connections");
        JMenu infoMenu = new JMenu("Info");
        add(connectionMenu);
        add(infoMenu);

        JMenuItem connect = new JMenuItem("Connect");
        JMenuItem disconnect = new JMenuItem("Disconnect");
        JMenuItem quit = new JMenuItem("Quit");

        connect.addActionListener(e -> connect());
        disconnect.addActionListener(e -> disconnect());
        quit.addActionListener(e -> quit());

        connectionMenu.add(connect);
        connectionMenu.add(disconnect);
        connectionMenu.add(quit);

        JMenuItem info = new JMenuItem("About");
        infoMenu.add(info);
    }

    private void connect() {
        new ClientConnect(panel.getUser());
    }

    private void disconnect() {
        panel.getUser().disconnectFromServer();
    }

    private void quit() {
        panel.getUser().disconnectFromServer();
        System.exit(0);
    }
}