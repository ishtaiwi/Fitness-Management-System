package fitness.programmanager;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Represents a notification sent to users.
 *
 * <p>Each notification has a receiver, a message text, and a status indicating
 * whether it has been seen.</p>
 *
 * @author saif-shayeb
 */
public class Notification {
    private boolean seen;
    private String notificationText;
    public static Integer maxId = 0;
    public static final Type type = new TypeToken<List<Notification>>() {}.getType();
    private String receiverId;

    /**
     * Increments the global {@code maxId} counter when a new notification is created.
     */
    public Notification() {
        maxId++;
    }

    /**
     * Sets the notification as seen or unseen.
     *
     * @param seen {@code true} if the notification has been seen, {@code false} otherwise
     */
    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    /**
     * Checks whether the notification has been seen.
     *
     * @return {@code true} if the notification has been seen, {@code false} otherwise
     */
    public boolean isSeen() {
        return seen;
    }

    /**
     * Sets the text content of the notification.
     *
     * @param notificationText the notification message
     */
    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    /**
     * Sets the ID of the receiver for this notification.
     *
     * @param receiverId the receiver's ID
     */
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * Returns a string representation of the notification.
     *
     * @return a string containing the notification text
     */
    @Override
    public String toString() {
        return "[text=" + notificationText + "]";
    }
}
