package fitness.instructor_interaction;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The {@code Comment} class represents a comment made by a user or instructor.
 * Each comment contains the text, the author's ID, and the date it was created.
 */
public class Comment {
    private String commentText; // The content of the comment
    private String authorId;    // The ID of the comment's author
    private String date;        // The date the comment was created

    /**
     * Constructs a {@code Comment} object with the specified comment text and author ID.
     * The date of the comment is automatically set to the current date in "dd/MM/yyyy" format.
     *
     * @param commentText the text of the comment
     * @param authorId    the ID of the comment's author
     */
    public Comment(String commentText, String authorId) {
        this.commentText = commentText;
        this.authorId = authorId;
        this.date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    /**
     * Constructs a {@code Comment} object with no initial comment text or author ID.
     * The date of the comment is automatically set to the current date in "dd/MM/yyyy" format.
     */
    public Comment() {
        this.date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    /**
     * Returns the text of the comment.
     *
     * @return the comment text
     */
    public String getCommentText() {
        return commentText;
    }

    /**
     * Returns the date when the comment was created.
     *
     * @return the creation date of the comment in "dd/MM/yyyy" format
     */
    public String getDate() {
        return date;
    }
}