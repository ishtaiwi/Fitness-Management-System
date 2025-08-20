package programManager;

import fitness.clientenroll.Enrollment;
import fitness.clientenroll.EnrollmentManager;
import fitness.instructor_interaction.DataBase;
import fitness.programmanager.Program;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestEnrollClient {
    Enrollment enrollment = new Enrollment();
    EnrollmentManager manager = EnrollmentManager.getInstance();
    String status;
    private String clientID;
    private String programID;
    @Given("iam a client in enroll in programs page with ID {string}")
    public void iamAClientInEnrollInProgramsPageWithID(String string) {
        clientID = string;

    }
    @When("i choose a program to enroll in with {string}")
    public void iChooseAProgramToEnrollInWith(String string) {
        programID = string;

    }
    @Then("the instruction will be made and {string} will be printed")
    public void theInstructionWillBeMadeAndWillBePrinted(String string) {
        enrollment.setClientId(clientID);
        enrollment.setProgramId(programID);
        enrollment.setProgress(0.0);
        enrollment.setAttendance(0.0);
        status = manager.add(enrollment);
        assert status.equals(string);

    }






}
