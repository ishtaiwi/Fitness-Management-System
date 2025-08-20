package fitness.instructor_interaction;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

/**
 * The {@code Massage} class represents a message exchanged between users.
 * Each message includes a sender, a receiver, and a unique message ID.
 */
public class Massage {
    private String receiverId;
    private String messageText;
    private String senderId;
    private String messageId;
    public static Integer maxId = 0;
    public static Type type = new TypeToken<List<Massage>>() {}.getType();
    /**
     * Sets the unique ID for the message.
     *
     * @param messageId the unique message ID
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * Sets the ID of the sender of the message.
     *
     * @param senderId the ID of the sender
     */
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    /**
     * Sets the text content of the message.
     *
     * @param messageText the text of the message
     */
    public void setMassageText(String messageText) {
        this.messageText = messageText;
    }

    /**
     * Gets the ID of the sender of the message.
     *
     * @return the sender's ID
     */
    public String getSenderId() {
        return this.senderId;
    }

    /**
     * Gets the unique ID of the message.
     *
     * @return the message ID
     */
    public String getMessageId() {
        return this.messageId;
    }

    /**
     * Gets the text content of the message.
     *
     * @return the text of the message
     */
    public String getMessageText() {
        return this.messageText;
    }

    /**
     * Gets the ID of the receiver of the message.
     *
     * @return the receiver's ID
     */
    public String getReceiverId() {
        return receiverId;
    }

    /**
     * Sets the ID of the receiver of the message.
     *
     * @param receiverId the ID of the receiver
     */
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }
}
