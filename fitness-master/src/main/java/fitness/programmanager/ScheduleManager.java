package fitness.programmanager;

import com.google.gson.reflect.TypeToken;
import fitness.instructor_interaction.DataBase;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Manages the scheduling of fitness program sessions, including adding and deleting schedules.
 */
public class ScheduleManager {
    public static final String FILENAME = "schedules.json";
    DataBase<Schedule> db = new DataBase<>();
    Type type = new TypeToken<List<Schedule>>() {}.getType();

    /**
     * Adds a new schedule to the system.
     *
     * @param sc the schedule to be added
     * @return a message indicating the result of the operation
     */
    public String add(Schedule sc) {
        if (sc.getLocation().trim().isEmpty()) {
            return "Missing location field";
        }
        if (sc.getDate().trim().isEmpty()) {
            return "Missing session date field";
        }
        if (sc.getTime().trim().isEmpty()) {
            return "Missing session time field";
        }
        if (sc.getType().trim().isEmpty()) {
            return "Missing type field";
        }
        if (sc.getProgramId().trim().isEmpty()) {
            return "Missing schedule name field";
        }
        if (sc.getMaxParticipants() == null || sc.getMaxParticipants() <= 0) {
            return "Missing max-participants field";
        }
        if (sc.getNum().trim().isEmpty()) {
            return "Missing schedule number field";
        }
        db.saveObjectToFile(FILENAME, sc);
        return "Session scheduled successfully";
    }

    /**
     * Deletes a schedule based on its number.
     *
     * @param string the schedule number to delete
     * @return a message indicating whether the schedule was found and deleted
     */
    public String deleteSchedule(String string) {
        Schedule schedule = db.searchByField(FILENAME, "num", string, type);
        if (schedule != null) {
            db.deleteByField(FILENAME, "num", string, type);
            return "Schedule Deleted Successfully";
        }
        return "Schedule Not Found";
    }
}
