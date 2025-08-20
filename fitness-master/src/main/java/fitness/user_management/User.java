package fitness.user_management;


import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class User {
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private String email;
    private String userType;
    private boolean active;
    private String id;
    public static Type type = new TypeToken<List<User>>() {}.getType();
    public static int maxId = 0;


    public User() {
        this.username = "";
        this.firstName = "";
        this.lastName = "";
        this.phoneNumber = "";
        this.password = "";
        this.email = "";
        this.userType = "";
        setActive(true);
        setId("100"+maxId);
        maxId++;
    }

    public User(String username, String firstName, String lastName,
                String phoneNumber, String password, String email,
                 String userType) {
        setUsername(username);
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setPassword(password);
        setEmail(email);
        setUserType(userType);
        setActive(true);
        setId("100"+ maxId);
        maxId++;
    }

    public User(User user) {
        this(user.getUsername(), user.getFirstName(), user.getLastName(),
                user.getPhoneNumber(), user.getPassword(), user.getEmail(),user.getUserType());
    }

    public static Type getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }






    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
