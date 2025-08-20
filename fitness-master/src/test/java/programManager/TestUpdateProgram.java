package programManager;

import fitness.programmanager.Program;
import fitness.programmanager.ProgramManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestUpdateProgram {
    private Program program;
    private ProgramManager manager;
    private String status;
    @Given("instructor is in program Update page")
    public void instructor_is_in_program_update_page() {
        manager = new ProgramManager();
        program = new Program();
    }
    @When("I put  programnum {string}")
    public void i_put_programnum(String string) {
        program.setId(string);
    }
    @When("I update program-title to {string}")
    public void i_update_program_title_to(String string) {
        program.setName(string);
    }
    @When("I update duration to {string}")
    public void i_update_duration_to(String string) {
        program.setDuration(string);

    }
    @When("I update difficulty to {string}")
    public void i_update_difficulty_to(String string) {
        program.setDifficulty(string);

    }
    @When("I update goals to {string}")
    public void i_update_goals_to(String string) {
        program.setGoals(string);

    }
    @When("I update media to {string}")
    public void i_update_media_to(String string) {
        program.setMedia(string);

    }
    @When("I update price to {string}")
    public void i_update_price_to(String string) {
        program.setPrice(string);

    }
    @Then("the transaction should be executed")
    public void the_transaction_should_be_executed() {
        status = manager.updateProgram(program);

    }
    @Then("message with {string} is put")
    public void message_with_is_put(String string) {
        System.out.println(status);
        assert status.equals(string);

    }


}
