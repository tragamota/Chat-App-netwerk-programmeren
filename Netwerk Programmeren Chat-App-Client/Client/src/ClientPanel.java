import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;

/**
 * Created by Ian on 8-5-2017.
 */
public class ClientPanel extends JPanel {
    private ClientUser user;

    private JTextArea messageSend;
    private JTextArea messageView;

    private Button sendButton;

    public ClientPanel() {
        super(new BorderLayout());
        JPanel southPanel = new JPanel(new FlowLayout());
        messageSend = new JTextArea(4 , 20);
        messageSend.setPreferredSize(new Dimension(getWidth()-50 , 40));
        messageSend.setSize(getWidth()-50 , 40);
        messageSend.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(messageSend, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        southPanel.add(scroll);

        messageView = new JTextArea(20 , 20);
        add(new JScrollPane(messageView) , BorderLayout.NORTH);

        sendButton = new Button("SEND");
        sendButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageSend.getText();
                messageSend.setText("");


            }
        });
        southPanel.add(sendButton);
        add(southPanel , BorderLayout.SOUTH);
        user = new ClientUser();
    }

    public ClientUser getUser() {
        return user;
    }
}
