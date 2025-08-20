package fitness.programmanager;

import fitness.clientenroll.Enrollment;
import fitness.instructor_interaction.DataBase;

/**
 * Manages notifications sent to users.
 */
public class NotificationManager {
    private final DataBase<Notification> db = new DataBase<>();

    /**
     * Sends a notification to all participants of a given program.
     *
     * @param notification the notification to send
     * @param programId    the ID of the program whose participants should be notified
     * @return a message indicating success ("participants notified") or failure ("not notified")
     */
    public String sendToAllParticipants(Notification notification, String programId) {
        DataBase<Enrollment> dbProg = new DataBase<>();
        Enrollment[] enrollments = new Enrollment[0];
        enrollments = dbProg.searchAllByField("Enrolls.json", "programId", programId, Enrollment.type, enrollments);

        boolean allNotified = true;
        for (Enrollment enrollment : enrollments) {
            notification.setReceiverId(enrollment.getClientId());
            allNotified = allNotified && sendNotification(notification);
        }

        return allNotified ? "participants notified" : "not notified";
    }

    /**
     * Saves a notification to the database.
     *
     * @param notification the notification to save
     * @return {@code true} if the notification was saved successfully, {@code false} otherwise
     */
    private boolean sendNotification(Notification notification) {
        return db.saveObjectToFile("Notifications.json", notification);
    }
}
