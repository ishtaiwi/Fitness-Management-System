package fitness;

/**
 * Represents a review given by a client for a specific fitness program.
 * The review includes the client's ID, username, program name, rating,
 * feedback text, and suggestions.
 */
public class Review {

    private String clientID;
    private String clientUserName;
    private String programName;
    private String rating;
    private String feedBackText;
    private String suggestion;
    private String status = "Client review confirmed successfully";

    /**
     * Gets the username of the client who gave the review.
     *
     * @return the client's username
     */
    public String getClientUserName() {
        return clientUserName;
    }

    /**
     * Sets the username of the client who gave the review.
     *
     * @param clientUserName the client's username
     */
    public void setClientUserName(String clientUserName) {
        this.clientUserName = clientUserName;
    }

    /**
     * Gets the name of the program being reviewed.
     *
     * @return the program name
     */
    public String getProgramName() {
        return programName;
    }

    /**
     * Sets the name of the program being reviewed.
     *
     * @param programName the program name
     */
    public void setProgramName(String programName) {
        this.programName = programName;
    }

    /**
     * Gets the rating given to the program.
     *
     * @return the rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * Sets the rating for the program.
     *
     * @param rating the rating given by the client
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * Gets the feedback text provided by the client.
     *
     * @return the feedback text
     */
    public String getFeedBackText() {
        return feedBackText;
    }

    /**
     * Sets the feedback text provided by the client.
     *
     * @param feedBackText the feedback text
     */
    public void setFeedBackText(String feedBackText) {
        this.feedBackText = feedBackText;
    }

    /**
     * Gets the status of the review submission.
     *
     * @return the status message
     */
    public String getStatus() {
        return status;
    }

    /**
     * Gets the client ID of the person who submitted the review.
     *
     * @return the client ID
     */
    public String getClientId() {
        return clientID;
    }

    /**
     * Sets the client ID of the person who submitted the review.
     *
     * @param clientId the client ID
     */
    public void setClientId(String clientId) {
        this.clientID = clientId;
    }

    /**
     * Gets the suggestions provided by the client in the review.
     *
     * @return the suggestion text
     */
    public String getSuggestion() {
        return suggestion;
    }

    /**
     * Sets the suggestions provided by the client in the review.
     *
     * @param suggestion the suggestion text
     */
    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}
