package programManager;

import fitness.programmanager.ProgramManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestDeleteProgram {

    ProgramManager pM;
    String status;

    @Given("instructor is in program delete page")
    public void instructor_is_in_program_delete_page() {

        pM=new ProgramManager();


    }

    @When("I enter a  program number {string}")
    public void i_enter_a_program_number(String string) {
       status= pM.deleteProgram(string);
    }
    @Then("a confirm message should be printed {string} on screen")
    public void a_confirm_message_should_be_printed_on_screen(String string) {
       assert (status.matches(string));
    }

}
