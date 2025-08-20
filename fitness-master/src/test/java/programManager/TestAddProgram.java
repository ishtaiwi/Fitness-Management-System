package programManager;

import fitness.programmanager.Program;
import fitness.programmanager.ProgramManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestAddProgram {
    Program program;
    ProgramManager manager;
    String status;
    private String programDuration,programTitle,programDifficulty,programGoals,programMedia;
    int programPrice;

    @Given("instructor is in program Add page")
    public void instructor_is_in_program_add_page() {
        manager = new ProgramManager();

    }
    @When("I insert ID with {string}")
    public void i_insert_id_with(String string) {
        /*
        * nothing to do here*/
    }
    @When("I insert program-title with {string}")
    public void i_insert_program_title_with(String string) {
       programTitle=string;
    }
    @When("I insert duration as {string}")
    public void i_insert_duration_as(String string) {

        programDuration=string;
    }
    @When("I insert difficulty as {string}")
    public void i_insert_difficulty_as(String string) {

        programDifficulty=string;
    }
    @When("I insert goals as {string}")
    public void i_insert_goals_as(String string) {
        programGoals=string;
    }
    @When("I insert media as {string}")
    public void i_insert_media_as(String string) {

        programMedia=string;
    }
    @When("I insert price as {string}")
    public void i_insert_price_as(String string) {

       programPrice=Integer.parseInt(string);
    }
    @Then("the add should be executed")
    public void the_add_should_be_executed() {
        program = new Program(programTitle,programDifficulty,programDuration,programPrice,programMedia,programGoals);
        status = manager.addProgram(program);
    }
    @Then("message with {string} is displayed")
    public void message_with_is_displayed(String string) {
       System.out.println(status);
        assert(string.matches(status));

    }

}
