package fitness.instructor_interaction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * The {@code DataBase} class provides generic methods for storing, retrieving,
 * searching, and deleting objects in a JSON file.
 * It uses Gson for serialization and deserialization.
 *
 * @param <T> the type of objects managed by this database
 */
public class DataBase<T> {
    static final Logger logger = Logger.getLogger(DataBase.class.getName());
    private static final Gson gson = new Gson();

    /**
     * Saves a new object to a JSON file. If the file already contains objects,
     * the new object is added to the existing list.
     *
     * @param filename  the name of the file to save the object to
     * @param newObject the object to save
     * @return {@code true} if the object was successfully saved; {@code false} otherwise
     */
    public boolean saveObjectToFile(String filename, T newObject) {
        List<T> allObjects = new ArrayList<>();
        File file = new File(filename);

        if (file.exists()) {
            try (FileReader reader = new FileReader(filename)) {
                Type listType = new TypeToken<List<T>>() {}.getType();
                allObjects = gson.fromJson(reader, listType);
                if (allObjects == null) {
                    allObjects = new ArrayList<>();
                }
            } catch (IOException e) {
                logger.severe(e.getMessage());
            }
        }

        allObjects.add(newObject);

        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(allObjects, writer);
            return true;
        } catch (IOException e) {
            logger.severe(e.getMessage());
            return false;
        }
    }

    /**
     * Loads a list of objects from a JSON file.
     *
     * @param filename the name of the file to read from
     * @param type     the type of the objects in the file
     * @return a list of objects loaded from the file
     */
    public List<T> loadObjectsFromFile(String filename, Type type) {
        List<T> objects = new ArrayList<>();
        try (FileReader reader = new FileReader(filename)) {
            objects = gson.fromJson(reader, type);
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
        return objects;
    }

    /**
     * Clears the contents of a JSON file.
     *
     * @param filePath the path of the file to clear
     */
    public void clearJsonFile(String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write("");
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
    }

    /**
     * Searches for the first object in the file where a specific field matches the given value.
     *
     * @param filename  the file to search
     * @param fieldName the name of the field to match
     * @param value     the value to search for
     * @param type      the type of the objects in the file
     * @return the first object that matches the search criteria, or {@code null} if no match is found
     */
    public T searchByField(String filename, String fieldName, Object value, Type type) {
        List<T> objects = loadObjectsFromFile(filename, type);

        for (T obj : objects) {
            try {
                Field field = obj.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object fieldValue = field.get(obj);

                if (fieldValue != null && fieldValue.equals(value)) {
                    return obj;
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                logger.severe(e.getMessage());
            }
        }

        return null;
    }

    /**
     * Deletes the first object in the file where a specific field matches the given value.
     *
     * @param filename  the file to modify
     * @param fieldName the name of the field to match
     * @param value     the value to search for
     * @param type      the type of the objects in the file
     * @return {@code true} if an object was deleted; {@code false} otherwise
     */
    public boolean deleteByField(String filename, String fieldName, Object value, Type type) {
        List<T> objects = loadObjectsFromFile(filename, type);
        boolean isDeleted = false;

        for (int i = 0; i < objects.size(); i++) {
            T obj = objects.get(i);
            try {
                Field field = obj.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object fieldValue = field.get(obj);

                if (fieldValue != null && fieldValue.equals(value)) {
                    objects.remove(i);
                    isDeleted = true;
                    break;
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                logger.severe(e.getMessage());
            }
        }

        if (isDeleted) {
            try (FileWriter writer = new FileWriter(filename)) {
                gson.toJson(objects, writer);
            } catch (IOException e) {
                logger.severe(e.getMessage());
            }
        }

        return isDeleted;
    }

    /**
     * Searches for all objects in the file where a specific field matches the given value.
     *
     * @param filename  the file to search
     * @param fieldName the name of the field to match
     * @param value     the value to search for
     * @param type      the type of the objects in the file
     * @param array     an array of the type T to ensure type safety
     * @return an array of objects that match the search criteria
     */
    public T[] searchAllByField(String filename, String fieldName, Object value, Type type, T[] array) {
        List<T> objects = loadObjectsFromFile(filename, type);
        List<T> matchingObjects = new ArrayList<>();

        for (T obj : objects) {
            try {
                Field field = obj.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object fieldValue = field.get(obj);

                if (fieldValue != null && fieldValue.equals(value)) {
                    matchingObjects.add(obj);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                logger.severe(e.getMessage());
            }
        }

        return matchingObjects.toArray(array);
    }
}
