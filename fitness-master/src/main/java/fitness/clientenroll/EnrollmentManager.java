package fitness.clientenroll;

import fitness.instructor_interaction.DataBase;
import fitness.programmanager.Program;

/**
 * Manages the enrollment process, including adding new enrollments.
 */
public class EnrollmentManager {
    static DataBase<Enrollment> db = new DataBase<>();
    public static Integer maxId = 0;

    private EnrollmentManager() {
    }
    public static EnrollmentManager getInstance() {
        return new EnrollmentManager();
    }
    /**
     * Adds a new enrollment to the system if the specified program exists.
     *
     * @param enrollment The enrollment to be added.
     * @return A message indicating the result of the operation.
     */
    public static String add(Enrollment enrollment) {
        DataBase<Program> programDB = new DataBase<>();

        if (programDB.searchByField("Programs.json", "id", enrollment.getProgramId(), Program.type) != null) {
            maxId++;
            enrollment.setEnrollmentId("100" + maxId);
            db.saveObjectToFile("Enrolls.json", enrollment);
            return "enrolled successfully";
        }

        return "program not found";
    }
}
