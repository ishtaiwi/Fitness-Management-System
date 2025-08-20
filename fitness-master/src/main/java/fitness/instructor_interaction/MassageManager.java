package fitness.instructor_interaction;

import com.google.gson.reflect.TypeToken;
import fitness.accountmanagement.AccountManagement;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Manages sending and storing messages between users.
 */
public class MassageManager {
    public static final String MESSAGES_JSON = "messages.json";
    private static final Type userType = new TypeToken<List<AccountManagement>>() {}.getType();

    public MassageManager() {
        /**/

    }

    /**
     * Sends a message after validating its content and receiver.
     *
     * @param m the {@code Massage} object to send
     * @return a string indicating the result of the operation:
     *         - "Message content is required" if the message text is empty
     *         - "Client ID is required" if the receiver ID is empty
     *         - "Invalid client ID: Message failed" if the receiver ID is not valid
     *         - "Message sent successfully" if the message is successfully sent
     */
    public static String send(Massage m) {
        DataBase<AccountManagement> userDb = new DataBase<>();
        DataBase<Massage> db = new DataBase<>();

        if (m.getMessageText().isEmpty()) {
            return "Message content is required";
        }

        if (m.getReceiverId().isEmpty()) {
            return "Client ID is required";
        }

        AccountManagement u = userDb.searchByField("accounts.json", "usrId", m.getReceiverId(), userType);

        if (u == null) {
            return "Invalid client ID: Message failed";
        }

        m.setMessageId(String.valueOf(Massage.maxId++));
        db.saveObjectToFile(MESSAGES_JSON, m);
        return "Message sent successfully";
    }
}