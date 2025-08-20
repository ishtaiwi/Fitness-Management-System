package fitness.instructor_interaction;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Forum} class represents a discussion forum where users can post comments
 * associated with a specific program.
 */
public class Forum {
    protected String programID;
    public List<Comment> comments;

    /**
     * Constructs a {@code Forum} instance with an empty list of comments.
     */
    public Forum() {
        comments = new ArrayList<>();
    }

    /**
     * Sets the program ID associated with this forum.
     *
     * @param programID the ID of the program
     */
    public void setProgramID(String programID) {
        this.programID = programID;
    }

    /**
     * Adds a comment to the forum.
     *
     * @param comment the {@code Comment} object to be added
     * @return a message indicating the success or failure of the operation:
     *         - "Comment posted successfully" if the comment is added
     *         - "Comment content is required" if the comment text is empty
     */
    public String addComment(Comment comment) {
        if (comment.getCommentText().isEmpty()) {
            return "Comment content is required";
        }
        comments.add(comment);
        return "Comment posted successfully";
    }
}
