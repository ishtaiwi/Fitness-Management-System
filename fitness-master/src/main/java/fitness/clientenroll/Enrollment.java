package fitness.clientenroll;

import com.google.gson.reflect.TypeToken;
import fitness.instructor_interaction.DataBase;
import fitness.user_management.User;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Represents an enrollment of a client in a fitness program.
 */
public class Enrollment {

    private String enrollmentId;
    private String clientId;
    private String programId;
    private double progress;
    private double attendance;
    private static Integer maxId = 0;
    public static final Type type = new TypeToken<List<Enrollment>>() {}.getType();

    /**
     * Default constructor that increments the maximum enrollment ID.
     */
    public Enrollment() {
        setMaxId(getMaxId() + 1);
    }

    /**
     * Gets the maximum enrollment ID.
     *
     * @return The current maximum enrollment ID.
     */
    public static Integer getMaxId() {
        return maxId;
    }

    /**
     * Sets the maximum enrollment ID.
     *
     * @param maxId The maximum ID to be set.
     */
    public static void setMaxId(Integer maxId) {
        Enrollment.maxId = maxId;
    }

    /**
     * Sets the client ID for this enrollment.
     *
     * @param clientID The client ID to be set.
     */
    public void setClientId(String clientID) {
        this.clientId = clientID;
    }

    /**
     * Sets the program ID for this enrollment.
     *
     * @param programID The program ID to be set.
     */
    public void setProgramId(String programID) {
        this.programId = programID;
    }

    /**
     * Sets the progress of the client in the program.
     *
     * @param v The progress value to be set.
     */
    public void setProgress(double v) {
        this.progress = v;
    }

    /**
     * Provides a string representation of the enrollment, including client name and program details.
     *
     * @return A string representing the enrollment.
     */
    @Override
    public String toString() {
        DataBase<User> db = new DataBase<>();
        User u = db.searchByField("Users.json", "id", this.clientId, User.type);
        return "Enrollment [enrollmentId=" + enrollmentId + ", clientName=" + u.getFirstName() + " " + u.getLastName()
                + ", programId=" + programId + ", progress=" + progress + "]";
    }

    /**
     * Sets the attendance of the client in the program.
     *
     * @param v The attendance value to be set.
     */
    public void setAttendance(double v) {
        this.attendance = v;
    }

    /**
     * Gets the client ID for this enrollment.
     *
     * @return The client ID.
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Gets the program ID for this enrollment.
     *
     * @return The program ID.
     */
    public String getProgramId() {
        return programId;
    }

    /**
     * Gets the progress of the client in the program.
     *
     * @return The progress value.
     */
    public double getProgress() {
        return progress;
    }

    /**
     * Gets the attendance of the client in the program.
     *
     * @return The attendance value.
     */
    public double getAttendance() {
        return attendance;
    }

    /**
     * Gets the enrollment ID.
     *
     * @return The enrollment ID.
     */
    public String getEnrollmentId() {
        return enrollmentId;
    }

    /**
     * Sets the enrollment ID.
     *
     * @param enrollmentId The enrollment ID to be set.
     */
    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }
}
