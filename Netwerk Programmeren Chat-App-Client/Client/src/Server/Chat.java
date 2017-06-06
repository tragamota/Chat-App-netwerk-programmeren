package Server;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Ian on 5-6-2017.
 */
public class Chat implements Serializable{
    private String message;
    private Date timeFromSending;
    private String messageFrom;

    public Chat(String message, String time, String messageFrom) {
        this.message = message;
        timeFromSending = new Date(Long.parseLong(time));
        this.messageFrom = messageFrom;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimeFromSending() {
        return timeFromSending;
    }

    public String getMessageFrom() {
        return messageFrom;
    }

    public static Comparator<Chat> getComparator() {
        return new Comparator<Chat>() {
            @Override
            public int compare(Chat o1, Chat o2) {
                return (int) (o1.timeFromSending.getTime() - o2.timeFromSending.getTime());
            }
        };
    }
}
