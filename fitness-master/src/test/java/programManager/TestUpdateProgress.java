package programManager;

import fitness.clientenroll.Enrollment;
import fitness.instructor_interaction.DataBase;
import fitness.accountmanagement.AccountManagement;
import fitness.programmanager.Program;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class TestUpdateProgress {
    DataBase <Enrollment> db;
    DataBase<AccountManagement> clientsDb;
    DataBase <Program> programsDb;
    private Boolean clientFound;
    String status = null;
    Enrollment[] clientEnrolls;
    Enrollment e = null;
    @Given("I am an instructor on the client progress page")
    public void i_am_an_instructor_on_the_client_progress_page() {

        db = new DataBase<>();
        clientsDb = new DataBase<>();
        programsDb = new DataBase<>();
        clientEnrolls = new Enrollment[0];
    }
    @When("I insert client Id with {string}")
    public void i_insert_client_id_with(String string) {
        clientEnrolls = db.searchAllByField("Enrolls.json","clientId",string,Enrollment.type,clientEnrolls);
        if(clientEnrolls.length == 0)
        {
            status = "Client ID is incorrect";
            clientFound = false;


        }
        else{
            clientFound = true;
        }
    }

    @When("I select a program that the client is enrolled in {string}")
    public void i_select_a_program_that_the_client_is_enrolled_in(String string) {
        if(clientFound){

       for (Enrollment enrollment : clientEnrolls) {
           if (enrollment.getProgramId().equals(string))
           {
            e = enrollment;
           }
       }
       if(e == null)
       {
           status = "program not found";
       }

        }

    }
    @When("I put the client completion rate {string}")
    public void i_put_the_client_completion_rate(String string) {
        double completionRate = Double.parseDouble(string);
        if(status == null){
        if(completionRate > 1 || completionRate < 0)
            status ="Progress rate is incorrect";
        else {
           e.setProgress(completionRate);}
        }
    }
    @When("I put the client attendance rate {string}")
    public void i_put_the_client_attendance_rate(String string) {
        if (status == null){
        double attendanceRate = Double.parseDouble(string);
        if(attendanceRate > 1 || attendanceRate < 0){
            status ="Attendance rate is incorrect";
        }
        else {
            e.setAttendance(attendanceRate);
        }
        }
    }
    @Then("the transaction should be set with confirm message {string}")
    public void the_transaction_should_be_set_with_confirm_message(String string) {
        if(status == null)
        {
            db.deleteByField("Enrolls.json","enrollmentId",e.getEnrollmentId(),Enrollment.type);
            db.saveObjectToFile("Enrolls.json",e);
            status = "Progress updated successfully";
        }
        System.out.println(status);
        assert (status.equals(string));


    }

}
