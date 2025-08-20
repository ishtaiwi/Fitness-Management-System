package fitness.programmanager;

/**
 * Represents a fitness program schedule, including details such as location, participants, time, and date.
 */
public class Schedule {
    private String location;
    private Integer maxParticipants;
    private String time;
    private String date;
    private String type;
    private String programId;
    private String num;

    /**
     * Default constructor for Schedule.
     */
    public Schedule() {}

    /**
     * Constructor to initialize a Schedule with specific details.
     *
     * @param location        the location of the session
     * @param maxParticipants the maximum number of participants allowed
     * @param time            the time of the session
     * @param date            the date of the session
     * @param type            the type of session (e.g., virtual, in-person)
     * @param programId       the ID of the program the session belongs to
     * @param num             the session number
     */
    public Schedule(String location, Integer maxParticipants, String time, String date, String type, String programId, String num) {
        this.location = location;
        this.maxParticipants = maxParticipants;
        this.time = time;
        this.date = date;
        this.type = type;
        this.programId = programId;
        this.num = num;
    }

    /**
     * Gets the location of the session.
     *
     * @return the location of the session
     */
    public String getLocation() {
        return location;
    }

    /**
     * Gets the maximum number of participants allowed in the session.
     *
     * @return the maximum number of participants
     */
    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    /**
     * Gets the time of the session.
     *
     * @return the time of the session
     */
    public String getTime() {
        return time;
    }

    /**
     * Gets the date of the session.
     *
     * @return the date of the session
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets the type of session (e.g., virtual, in-person).
     *
     * @return the type of session
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the program ID associated with the session.
     *
     * @return the program ID
     */
    public String getProgramId() {
        return programId;
    }

    /**
     * Gets the session number.
     *
     * @return the session number
     */
    public String getNum() {
        return num;
    }
}
