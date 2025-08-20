package fitness;


import fitness.accountmanagement.AccountManagement;
import fitness.clientenroll.Enrollment;
import fitness.helper.DataBasee;
import fitness.helper.PDFReportGenerator;
import fitness.helper.Validation;
import fitness.instructor_interaction.DataBase;
import fitness.instructor_interaction.Massage;
import fitness.instructor_interaction.MassageManager;
import fitness.programmanager.Notification;
import fitness.programmanager.Program;
import fitness.user_management.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class Main {
    public static final String ACCOUNTS_PATH = "accounts.json";
    public static final String USERS_PATH = "Users.json";
    public static final String ENROLLMENT_JSON_PATH = "Enrolls.json";
    public static final String MESSAGES_JSON = "messages.json";
    public static final String PROGRAMS_JSON = "Programs.json";
    public static final String INVALID_CHOICE_PLEASE_TRY_AGAIN = "Invalid choice. Please try again.";
    public static final String ENTER_YOUR_CHOICE = "Enter your choice: ";
    public static final String ENTER_USERNAME = "Enter Username: ";
    public static final String INVALID_OPTION = "Invalid option";
    public static final String USR_ID = "usrId";
    public static final String CLIENT = "client";
    private final DataBasee database = new DataBasee();
    private final PDFReportGenerator pdfReportGenerator = new PDFReportGenerator();
    private final Scanner scanner = new Scanner(System.in);
    private User user;
    DataBase<AccountManagement> db = new DataBase<>();

    private void validateLogin(String userName, String password) {
        DataBase<User> dbUsr = new DataBase<>();
        User u = dbUsr.searchByField(USERS_PATH, "username", userName, User.type);
        if (u == null) {
            out.println("User not found");
        } else if (u.getPassword().equals(password)) {
            user = u;
        }

    }

    public void printLogin() {
        out.println("Welcome to fitness gym");
        out.println("please enter your username:");
        String username = scanner.nextLine();
        out.println("please enter your password:");
        String password = scanner.nextLine();
        validateLogin(username, password);
        showMainPage(user);


    }

    private void showMainPage(User user) {
        if (user == null) {
            return;
        }
        switch (user.getUserType()) {
            case CLIENT -> showUserMain(user);
            case "instructor" -> showInstructorMain();
            case "admin" -> showAdminMain();
            default -> throw new IllegalStateException("Unexpected value: " + user.getUserType());
        }

    }

    private void showAdminMain() {
        while (true) {
            out.println("\nAdmin Panel:");
            out.println("1. Add User");
            out.println("2. Change Password");
            out.println("3. Activate/Deactivate User");
            out.println("4. Generate Report");
            out.println("5. View Users");
            out.println("6. Exit");
            out.print(ENTER_YOUR_CHOICE);

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> addUser();
                case 2 -> changePassword();
                case 3 -> updateActiveStatus();
                case 4 -> generateReport();
                case 5 -> viewUsers();
                case 6 -> {
                    out.println("Exiting Admin Panel. Goodbye!");
                    return;
                }
                default -> out.println(INVALID_CHOICE_PLEASE_TRY_AGAIN);
            }
        }
    }


    private void addUser() {
        out.println("\n--- Add New User ---");

        out.print(ENTER_USERNAME);
        String username = scanner.nextLine();

        out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        out.print("Enter Password: ");
        String password = scanner.nextLine();

        out.print("Enter Email: ");
        String email = scanner.nextLine();

        out.print("Enter User Type (client/instructor): ");
        String userType = scanner.nextLine();
        User newUser = new User(username, firstName, lastName, phoneNumber, password, email, userType);




        String validationResult = Validation.userValidationTest(newUser);
        if (!validationResult.equals(Validation.VALID)) {
            out.println("Error: " + validationResult);
            return;
        }
        if(userType.equals(CLIENT)){
            out.println("Enter User age: ");
            int age = scanner.nextInt();

            out.println("Enter User fitness goals: ");
            String fitnessGoals = scanner.nextLine();
            out.println("Enter User dietary preferences: ");
            String dietaryPreferences = scanner.nextLine();
            AccountManagement a =new AccountManagement(age,fitnessGoals,dietaryPreferences,newUser.getId());
            db.saveObjectToFile(ACCOUNTS_PATH,a);
        }


        database.saveUserToFile(USERS_PATH, newUser);

        out.println("User added successfully.");
    }

    private void changePassword() {
        out.println("\n--- Change Password ---");

        out.print(ENTER_USERNAME);
        String username = scanner.nextLine();

        out.print("Enter New Password: ");
        String newPassword = scanner.nextLine();

        boolean isChanged = database.changePassword(newPassword, username);
        if (isChanged) {
            out.println(database.statuspass);
        } else {
            out.println("User not found. Password not changed.");
        }
    }

    private void updateActiveStatus() {
        out.println("\n--- Update Active Status ---");

        out.print(ENTER_USERNAME);
        String username = scanner.nextLine();

        out.print("Set Active (true/false): ");
        boolean isActive = Boolean.parseBoolean(scanner.nextLine());

        boolean isUpdated = database.updateActive(username, isActive);
        if (isUpdated) {
            out.println(database.statuspass);
        } else {
            out.println("User not found. Status not updated.");
        }
    }

    private void generateReport() {
        out.println("\n--- Generate Report ---");

        out.print("Enter PDF File Path (e.g., reports/users_report.pdf): ");
        String filePath = scanner.nextLine();

        List<User> users = database.loadUsersFromFile(USERS_PATH);
        if (users.isEmpty()) {
            out.println("No users found.");
            return;
        }

        String[] headers = {"Username", "First Name", "Last Name", "Email", "Active"};
        List<String[]> data = users.stream()
                .map(usr -> new String[]{usr.getUsername(), usr.getFirstName(), usr.getLastName(),
                        usr.getEmail(), String.valueOf(usr.isActive())})
                .toList();

        // Create a mutable list and add headers as the first row
        List<String[]> mutableData = new ArrayList<>(data);
        mutableData.add(0, headers); // Now this works because mutableData is mutable

        pdfReportGenerator.generateReport(filePath, "User Report", mutableData);
        out.println("Report generated successfully at: " + filePath);
    }


    private void viewUsers() {
        out.println("\n--- View Users ---");
        List<User> users = database.loadUsersFromFile(USERS_PATH);
        if (users.isEmpty()) {
            out.println("No users found.");
            return;
        }

        out.println("Users List:");
        for (User usr : users) out.println(usr);
    }

    private void showInstructorMain() {

        while (true) {
            out.println("Welcome to the instructor page");
            out.println("choose the page you would like to view");
            out.println("1-Programs management.");
            out.println("2-Message clients.");
            out.println("3-progress monitoring.");
            out.println("4-Notifications.");
            out.println("0-Exit.");

            boolean flag = false;
            while (!flag) {
                out.println("Choose your option:");

                String ins = scanner.nextLine();
                if (!ins.isEmpty()) {
                    int option = Integer.parseInt(ins);
                    if (option == 0) {
                        return;
                    }
                    flag = chooseOptionInstructor(option);}


            }
        }


    }

    private boolean chooseOptionInstructor(int option) {
        return switch (option) {
            case 1 -> {
                showProgramManagement();
                yield true;
            }
            case 2 -> {
                sendMessage();
                yield true;
            }
            case 3 -> {
                showProgressPage();
                yield true;
            }
            case 4 -> {
                showNotifications();
                yield true;
            }
            default -> {
                out.println(INVALID_OPTION);
                yield false;
            }
        };
    }

    private void showProgressPage() {
        DataBase<Program> programDataBase = new DataBase<>();
        List<Program> programs = programDataBase.loadObjectsFromFile(PROGRAMS_JSON, Program.type);
        int index = 1;
        for (Program program : programs) {
            out.print(index + "-");
            out.println(program.toString());
            index++;
        }
        out.println("0-go to previous page");
        out.println("choose option:");
        int option = scanner.nextInt();
        while (option < 0 || option > programs.size()) {
            out.println(INVALID_OPTION);
            option = scanner.nextInt();
        }
        if (option != 0) {
            showProgramProgress(programs.get(option - 1));
        }

    }

    private void showProgramProgress(Program program) {
        DataBase<Enrollment> enrollmentDataBase = new DataBase<>();
        Enrollment[] enrollments = new Enrollment[0];
        enrollments = enrollmentDataBase.searchAllByField(ENROLLMENT_JSON_PATH, "programId", program.getId(), Enrollment.type, enrollments);
        int index = 1;
        for (Enrollment enrollment : enrollments) {
            out.print(index + "-");
            out.println(enrollment.toString());
            index++;
        }
        out.println("0-go to previous page");
        out.println("choose client number to update:");
        int option = scanner.nextInt();


        while (option < 0 || option > enrollments.length) {
            out.println(INVALID_OPTION);
            option = scanner.nextInt();

        }
        if (option != 0) {
            showClientProgress(enrollments[option - 1]);
        }

    }

    private void showClientProgress(Enrollment enrollment) {

        while (true) {
            out.println("-----------------Update progress page-----------------------");
            out.println("Type \"E\" to cancel.");


            while (true) {
                out.println("Choose your option:");

                String option = scanner.next();
                if (option.charAt(0) == 'E') {
                    return;
                } else {
                    int progress = Integer.parseInt(option);
                    while (progress < 0 || progress > 100) {
                        out.println("Invalid option choose from 0 to 100");
                        progress = scanner.nextInt();
                    }
                    updateUserProgress(enrollment,progress);

                }


            }
        }
    }

    /**
     * Updates the progress of a given enrollment. This method modifies the progress
     * of the specified enrollment, removes the existing record from the database,
     * and saves the updated record back to the database. If successful, a confirmation
     * is displayed. If an error occurs in either the deletion or saving process, error
     * messages are displayed.
     *
     * @param enrollment the enrollment object whose progress is to be updated
     * @param option the new progress value to set for the enrollment
     */
    private void updateUserProgress(Enrollment enrollment, int option) {
        DataBase<Enrollment> enrollmentDataBase = new DataBase<>();
        enrollment.setProgress(option);

        // Delete the existing enrollment record
        boolean isDeleted = enrollmentDataBase.deleteByField(ENROLLMENT_JSON_PATH, "enrollmentId", enrollment.getEnrollmentId(), Enrollment.type);

        // Save the updated enrollment record
        boolean isSaved = enrollmentDataBase.saveObjectToFile(ENROLLMENT_JSON_PATH, enrollment);

        if (isDeleted && isSaved) {
            out.println("Progress updated successfully for enrollment ID: " + enrollment.getEnrollmentId());
        } else {
            if (!isDeleted) {
                out.println("Failed to delete the old enrollment record.");
            }
            if (!isSaved) {
                out.println("Failed to save the updated enrollment record.");
            }
        }
    }

    private void showNotifications() {
       DataBase<Notification> notificationDataBase = new DataBase<>();
       Notification[] notifications = new Notification[0];
       notifications = notificationDataBase.searchAllByField("Notifications.json", "receiverId", "1001", Notification.type, notifications);
       int index = 1;
       out.println("Notifications List:");
       for (Notification notification : notifications) {
           out.print(index + "-");
           out.println(notification.toString());
           index++;
       }
       if (notifications.length == 0) {
           out.println("No notifications found.");
       }
       out.println("Enter 0 to go back to previous page:");

       int option = scanner.nextInt();
       while (option != 0) {
           out.println(INVALID_OPTION);
           option = scanner.nextInt();
       }



    }

    private void showMessenger() {
      DataBase <Massage> massageDataBase = new DataBase<>();
      Massage[] messages = new Massage[0];
      messages = massageDataBase.searchAllByField(MESSAGES_JSON,"receiverId",user.getId(),Massage.type,messages);
      int index = 1;
      for (Massage message : messages) {
          out.print(index + "-");
          out.println(message.toString());
      }
        out.println("Enter 0 to go back to previous page:");

        int option = scanner.nextInt();
        while (option != 0) {
            out.println(INVALID_OPTION);
            option = scanner.nextInt();
        }

    }


        private void showProgramManagement () {
            while (true) {
                out.println("\n--- Program Management ---");
                out.println("1. Add Program");
                out.println("2. Delete Program");
                out.println("3. Update Program");
                out.println("0. Go Back");
                out.print(ENTER_YOUR_CHOICE);

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> addProgram();
                    case 2 -> deleteProgram();
                    case 3 -> updateProgram();
                    case 0 -> {
                        return;
                    }
                    default -> out.println(INVALID_CHOICE_PLEASE_TRY_AGAIN);
                }
            }
        }

        private void addProgram () {
        
            DataBase<Program> programDataBase = new DataBase<>();
            out.println("\n--- Add New Program ---");
            out.print("Enter Program Name: ");
            String name = scanner.nextLine();
            out.print("Enter Program Difficulty: ");
            String difficulty = scanner.nextLine();
            out.print("Enter Program Duration (weeks): ");
            int duration = Integer.parseInt(scanner.nextLine());
            out.print("Enter Program Price: ");
            double price = Double.parseDouble(scanner.nextLine());
            out.print("Enter Program Media (e.g., video, photo, text): ");
            String media = scanner.nextLine();
            out.print("Enter Program Goals (e.g., strength training, cardio, yoga): ");
            String goals = scanner.nextLine();
            Program newProgram = new Program( name,difficulty, String.valueOf(duration), price,media,goals);
            boolean isSaved = programDataBase.saveObjectToFile(PROGRAMS_JSON, newProgram);

            if (isSaved) {
                out.println("Program added successfully.");
            } else {
                out.println("Failed to add the program.");
            }
        }

        private void deleteProgram () {
            DataBase<Program> programDataBase = new DataBase<>();
            out.println("\n--- Delete Program ---");
            out.print("Enter Program ID to delete: ");
            String id = scanner.nextLine();

            boolean isDeleted = programDataBase.deleteByField(PROGRAMS_JSON, "id", id, Program.type);

            if (isDeleted) {
                out.println("Program deleted successfully.");
            } else {
                out.println("Program not found or failed to delete.");
            }
        }

        private void updateProgram () {
            DataBase<Program> programDataBase = new DataBase<>();
            out.println("\n--- Update Program ---");
            out.print("Enter Program ID to update: ");
            String id = scanner.nextLine();

            Program program = programDataBase.searchByField(PROGRAMS_JSON, "id", id, Program.type);
            if (program == null) {
                out.println("Program not found.");
                return;
            }

            out.println("Leave fields empty to keep current value.");
            out.print("Enter New Program Name (" + program.getName() + "): ");
            String newName = scanner.nextLine();
            out.print("Enter New Program Difficulty (" + program.getDifficulty() + "): ");
            String newDifficulty = scanner.nextLine();
            out.print("Enter New Program Duration (weeks) (" + program.getDuration() + "): ");
            String newDuration = scanner.nextLine();
            out.print("Enter New Program Price (" + program.getPrice() + "): ");
            String newPrice = scanner.nextLine();
            out.print("Enter New Program Media (" + program.getMedia() + "): ");
            String newMedia = scanner.nextLine();
            out.print("Enter New Program Goals (" + program.getGoals() + "): ");
            String newGoals = scanner.nextLine();

            if (!newName.isEmpty()) {
                program.setName(newName);
            }
            if (!newDifficulty.isEmpty()) {
                program.setDifficulty(newDifficulty);
            }
            if (!newDuration.isEmpty()) {
                program.setDuration(newDuration);
            }
            if (!newPrice.isEmpty()) {
                program.setPrice(newPrice);
            }
            if (!newMedia.isEmpty()) {
                program.setMedia(newMedia);
            }
            if (!newGoals.isEmpty()) {
                program.setGoals(newGoals);
            }
            programDataBase.deleteByField(PROGRAMS_JSON, "id", id, Program.type);
            boolean isUpdated = programDataBase.saveObjectToFile(PROGRAMS_JSON, program);

            if (isUpdated) {
                out.println("Program updated successfully.");
            } else {
                out.println("Failed to update the program.");
            }
        }



    private void showUserMain(User user) {
        while (true) {
            out.println("\nUser Dashboard:");
            out.println("1. Customize Profile");
            out.println("2. Browse and Enroll in Programs");
            out.println("3. Track Progress");
            out.println("4. Submit Feedback and Reviews");
            out.println("5. Show Messages");
            out.println("0. Logout");
            out.print(ENTER_YOUR_CHOICE);
            if (scanner.hasNextLine()) {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> customizeProfileMenu(user);
                case 2 -> browsePrograms();
                case 3 -> trackProgress(user);
                case 4 -> submitFeedback();
                case 5 -> showMessenger();
                case 0 -> {
                    out.println("Logging out. Goodbye!");
                    return;
                }
                default -> out.println(INVALID_CHOICE_PLEASE_TRY_AGAIN);
            }}
        }
    }

  private void submitFeedback() {
        out.println("\n--- Submit Feedback ---");



        out.print("Enter the Program Name: ");
        String programName = scanner.nextLine();

        out.print("Enter your Rating (1-5): ");
        String rating = scanner.nextLine();

        out.println("Enter your Feedback Text: ");
        String feedBackText = scanner.nextLine();

        out.println("Enter your Suggestions: ");
        String suggestion = scanner.nextLine();

        Review newReview = new Review();
        newReview.setClientId(user.getId());
        newReview.setClientUserName(user.getFirstName()+' '+user.getLastName());
        newReview.setProgramName(programName);
        newReview.setRating(rating);
        newReview.setFeedBackText(feedBackText);
        newReview.setSuggestion(suggestion);

        database.saveReviewToFile("Reviews.json", newReview);

        out.println("Feedback submitted successfully!");
    }

    



    private void trackProgress(User user) {
        out.println("\n--- Track Progress ---");

        DataBase<Enrollment> dbEnrll = new DataBase<>();
        Enrollment[] enrollments = dbEnrll.searchAllByField(ENROLLMENT_JSON_PATH, "clientId", user.getId(), Enrollment.type, new Enrollment[0]);
        DataBase<Program> dbProgram = new DataBase<>();

        if (enrollments.length == 0) {
            out.println("No progress records found for the user.");
            return;
        }

        int index = 1;
        out.println("Progress Records:");
        for (Enrollment enrollment : enrollments) {
            Program program = dbProgram.searchByField(PROGRAMS_JSON, "id",
                    enrollment.getProgramId(), Program.type);
            
            out.println(index++ + ". Program: " + program.getName() +
                    " - Progress: " + enrollment.getProgress() + "%");
        }

        out.println("Enter 0 to go back.");

        int option = scanner.nextInt();
        while (option != 0) {
            out.println("Invalid option. Please enter 0 to go back.");
            option = scanner.nextInt();
        }
    }


    private void browsePrograms() {
        DataBase<Program> dbProg = new DataBase<>();
        List<Program> programs = dbProg.loadObjectsFromFile(PROGRAMS_JSON, Program.type);

        if (programs.isEmpty()) {
            out.println("\nNo programs available at the moment.");
            return;
        }

        out.println("\nAvailable Programs:");
        int index = 1;
        for (Program program : programs) {
            out.println(index++ + ". Name: " + program.getName() +
                        ", Difficulty: " + program.getDifficulty() +
                        ", Duration: " + program.getDuration() + " weeks" +
                        ", Price: $" + program.getPrice() +
                        ", Media: " + program.getMedia() +
                        ", Goals: " + program.getGoals());
        }

        out.println("Choose a program to enroll (enter the number) or 0 to go back:");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 0) {
            return;
        }

        if (choice < 1 || choice > programs.size()) {
            out.println(INVALID_CHOICE_PLEASE_TRY_AGAIN);
            return;
        }

        Program selectedProgram = programs.get(choice - 1);

        out.print("Do you want to enroll in \"" + selectedProgram.getName() + "\"? (yes/no): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            Enrollment newEnrollment = new Enrollment();
            newEnrollment.setClientId(user.getId());
            newEnrollment.setProgramId(selectedProgram.getId());
            newEnrollment.setProgress(0);
            newEnrollment.setEnrollmentId("100"+ Enrollment.getMaxId());
            DataBase<Enrollment>enrollmentDataBase = new DataBase<>();
            boolean isEnrolled = enrollmentDataBase.saveObjectToFile(ENROLLMENT_JSON_PATH, newEnrollment);

            if (isEnrolled) {
                out.println("Successfully enrolled in the program: " + selectedProgram.getName());
            } else {
                out.println("Failed to enroll in the program. Please try again.");
            }
        } else {
            out.println("Enrollment cancelled.");
        }
    }

    private void customizeProfileMenu(User user) {
        DataBase<AccountManagement> dbAcc = new DataBase<>();
        AccountManagement a = dbAcc.searchByField(ACCOUNTS_PATH, USR_ID, user, AccountManagement.type);

        out.println("\n--- Customize Profile ---");
        out.println("Leave fields empty to keep current value.");
        out.print("Enter New First Name (" + user.getFirstName() + "): ");
        String newFirstName = scanner.nextLine();
        if (!newFirstName.trim().isEmpty()) {
            user.setFirstName(newFirstName);

        }

        out.print("Enter New Last Name (" + user.getLastName() + "): ");
        String newLastName = scanner.nextLine();
        if (!newLastName.trim().isEmpty()) {
            user.setLastName(newLastName);

        }

        out.print("Enter New Phone Number (" + user.getPhoneNumber() + "): ");
        String newPhoneNumber = scanner.nextLine();
        if (!newPhoneNumber.trim().isEmpty()) {
            user.setPhoneNumber(newPhoneNumber);

        }

        out.print("Enter New Email (" + user.getEmail() + "): ");
        String newEmail = scanner.nextLine();
        if (!newEmail.trim().isEmpty()) {
            user.setEmail(newEmail);

        }

        out.print("Enter New Password: ");
        String newPassword = scanner.nextLine();
        if (!newPassword.trim().isEmpty()) {
            user.setPassword(newPassword);

        }

        String validationResult = Validation.userValidationTest(user);
        if (!validationResult.equals(Validation.VALID)) {
            out.println("Error: " + validationResult);
            return;
        }

        dbAcc.deleteByField(ACCOUNTS_PATH, USR_ID,user.getId() , AccountManagement.type);
        dbAcc.saveObjectToFile(ACCOUNTS_PATH, a);
        dbAcc.deleteByField(USERS_PATH, "id", user.getId(), User.type);
        database.saveUserToFile(USERS_PATH,user);
        out.println("Profile updated successfully.");
    }
    
    private void sendMessage() {


        out.println("Enter receiver id:");
       String rcvrId = scanner.nextLine();

       if(db.searchByField(ACCOUNTS_PATH, USR_ID, rcvrId, AccountManagement.type) != null)
       {
           Massage m = new Massage();
           MassageManager massageManager = new MassageManager();
         out.println("Enter message:");
         String msgTxt = scanner.nextLine();
         m.setSenderId(user.getId());
         m.setReceiverId(rcvrId);
         m.setMassageText(msgTxt);
         massageManager.send(m);

       }
       else {
           out.println("User not found");
       }

    }
    public static void main(String[] args) {

        new Main().printLogin();

    }

}

