package fitness.instructor_interaction;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * The {@code ForumManager} class handles the management of forums, including adding new forums
 * and merging comments if a forum with the same program ID already exists.
 */
public class ForumManager {
    /**
     * The name of the JSON file where forum data is stored.
     */
    public static final String FILENAME = "forums.json";

    private final DataBase<Forum> db = new DataBase<>(); // Instance of the database for managing forums
    private final Type type = new TypeToken<List<Forum>>() {}.getType(); // Type for deserialization of Forum list

    /**
     * Adds a forum to the database. If a forum with the same program ID already exists,
     * the comments from the existing forum are merged with the new forum's comments.
     *
     * @param forum the {@code Forum} object to add
     * @return "success" if the forum is successfully added
     */
    public String add(Forum forum) {
        Forum existingForum = db.searchByField(FILENAME, "programID", forum.programID, type);

        if (existingForum != null) {
            forum.comments.addAll(existingForum.comments);
            db.deleteByField(FILENAME, "programID", existingForum.programID, type);
        }

        // Save the new forum to the database
        db.saveObjectToFile(FILENAME, forum);
        return "success";
    }
}
