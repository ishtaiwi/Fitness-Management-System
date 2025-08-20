package fitness.programmanager;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Represents a fitness program with various attributes such as name, duration, difficulty, price, media, and goals.
 */
public class Program {
    private String name;
    private String id;
    private String duration;
    private String difficulty;
    private double price;
    private String media;
    private String goals;

    public static int maxId = 0;
    public static final Type type = new TypeToken<List<Program>>() {}.getType();

    /**
     * Default constructor for the Program class.
     */
    public Program() {
    }

    /**
     * Constructs a Program object with the specified attributes.
     *
     * @param name       the name of the program
     * @param difficulty the difficulty level of the program
     * @param duration   the duration of the program
     * @param price      the price of the program
     * @param media      associated media or resources for the program
     * @param goals      the goals of the program
     */
    public Program(String name, String difficulty, String duration, double price, String media, String goals) {
        this.name = name;
        this.duration = duration;
        this.id = "100" + maxId;
        maxId++;
        this.difficulty = difficulty;
        this.price = price;
        this.media = media;
        this.goals = goals;
    }

    @Override
    public String toString() {
        return "[id=" + id + " name=" + name + " \t duration=" + duration + "\t difficulty=" + difficulty + "]";
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setPrice(String price) {
        this.price = price.isEmpty() ? 0 : Double.parseDouble(price);
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDuration() {
        return duration;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public double getPrice() {
        return price;
    }

    public String getMedia() {
        return media;
    }

    public String getGoals() {
        return goals;
    }
}
