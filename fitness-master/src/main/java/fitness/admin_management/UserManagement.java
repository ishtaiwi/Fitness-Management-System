package fitness.admin_management;

import fitness.instructor_interaction.DataBase;
import fitness.user_management.User;
import fitness.helper.Validation;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Manages user accounts in the system, including adding users, validating input, changing passwords,
 * and managing instructor approval and user activity status.
 */
public class UserManagement {

    public static final String USERNAME = "username";
    public static final String USERS_JSON = "Users.json";
    private String status;
    private DataBase<User> dataBase = new DataBase<>();
    static Logger logger = Logger.getLogger(UserManagement.class.getName());

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static boolean isFileEmpty(String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            logger.severe("File does not exist.");
            return false;
        }

        try {
            return file.length() == 0;
        } catch (SecurityException e) {
            logger.severe(e.getMessage());
            return false;
        }
    }

    public boolean isTaken(String name) {
        if (isFileEmpty(USERS_JSON)) {
            return false;
        }

        List<User> users = dataBase.loadObjectsFromFile(USERS_JSON, User.type);

        for (User user : users) {
            if (Objects.equals(name, user.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public void addUser(User user) {
        String validationStatus = Validation.userValidationTest(user);
        setStatus(validationStatus);

        if (getStatus().equals("Valid")) {
            if (isTaken(user.getUsername())) {
                setStatus("Username is already taken");
            } else {
                dataBase.saveObjectToFile(USERS_JSON, user);
                setStatus("User was added successfully");
            }
        }
    }

    public boolean changePassword(String password, String userName) {
        boolean success = false;
        User user = dataBase.searchByField(USERS_JSON, USERNAME, userName, User.type);

        if (user != null) {
            success = true;
            user.setPassword(password);
            dataBase.deleteByField(USERS_JSON, USERNAME, userName, User.type);
            dataBase.saveObjectToFile(USERS_JSON, user);
        }
        return success;
    }

    public boolean updateActive(String userName, boolean active) {
        boolean success = false;
        User user = dataBase.searchByField(USERS_JSON, USERNAME, userName, User.type);

        if (user != null) {
            success = true;
            user.setActive(active);
            dataBase.deleteByField(USERS_JSON, USERNAME, userName, User.type);
            dataBase.saveObjectToFile(USERS_JSON, user);
        }
        return success;
    }

    public String approveInstructor(String username) {
        List<User> users = dataBase.loadObjectsFromFile(USERS_JSON, User.type);

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (user.getUserType().equals("instructor") && user.isActive()) {
                    return "Instructor is already approved";
                }
                if (user.getUserType().equals("instructor")) {
                    user.setActive(true);
                    dataBase.saveObjectToFile(USERS_JSON, user);
                    return "Instructor approved successfully";
                }
            }
        }
        return "Instructor not found";
    }
}
