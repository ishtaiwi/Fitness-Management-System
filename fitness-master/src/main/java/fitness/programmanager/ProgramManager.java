package fitness.programmanager;

import com.google.gson.reflect.TypeToken;
import fitness.instructor_interaction.DataBase;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Manages the operations related to fitness programs, including adding, updating, retrieving, and deleting programs.
 */
public class ProgramManager {
    public static final String FILENAME = "Programs.json";
    private final DataBase<Program> dataBase = new DataBase<>();
    private final Type type = new TypeToken<List<Program>>() {}.getType();

    /**
     * Adds a new program to the database if it's valid and doesn't already exist.
     *
     * @param program the program to add
     * @return a message indicating the success or failure of the operation
     */
    public String addProgram(Program program) {
        String validationMessage = validProgram(program);
        if (validationMessage != null) return validationMessage;

        if (dataBase.searchByField(FILENAME, "id", program.getId(), type) == null) {
            dataBase.saveObjectToFile(FILENAME, program);
            return "Program created successfully";
        }

        return "Program already exists";
    }

    /**
     * Retrieves a program by its ID.
     *
     * @param programId the ID of the program
     * @return the program with the specified ID, or {@code null} if not found
     */
    public Program getProgram(String programId) {
        return dataBase.searchByField(FILENAME, "id", programId, type);
    }

    /**
     * Deletes a program from the database by its ID.
     *
     * @param programId the ID of the program to delete
     * @return a message indicating the success or failure of the deletion
     */
    public String deleteProgram(String programId) {
        if (dataBase.deleteByField(FILENAME, "id", programId, type)) {
            return "Program deleted successfully";
        }
        return "Program does not exist";
    }

    /**
     * Updates an existing program with new information.
     *
     * @param program the program with updated information
     * @return a message indicating the success or failure of the update
     */
    public String updateProgram(Program program) {
        String validationMessage = validProgram(program);
        if (validationMessage != null) return validationMessage;

        Program existingProgram = getProgram(program.getId());
        if (existingProgram != null) {
            dataBase.deleteByField(FILENAME, "id", existingProgram.getId(), type);
            dataBase.saveObjectToFile(FILENAME, program);
            return "Program updated successfully";
        }

        return "Program not found";
    }

    /**
     * Validates the fields of a program to ensure they are not empty.
     *
     * @param program the program to validate
     * @return an error message if validation fails, or {@code null} if the program is valid
     */
    private static String validProgram(Program program) {
        if (program.getDifficulty().trim().isEmpty()) {
            return "Missing difficulty field";
        }
        if (program.getDuration().trim().isEmpty()) {
            return "Missing duration field";
        }
        if (program.getName().trim().isEmpty()) {
            return "Missing program-title field";
        }
        if (program.getGoals().trim().isEmpty()) {
            return "Missing goals field";
        }
        return null;
    }
}
