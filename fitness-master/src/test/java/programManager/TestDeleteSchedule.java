package programManager;

import fitness.programmanager.ScheduleManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestDeleteSchedule {
    ScheduleManager manager;
    private String status;

    @Given("instructor is in Schedule Delete page")
    public void instructor_is_in_schedule_delete_page() {
        manager = new ScheduleManager();
    }
    @When("I enter a {string}")
    public void i_enter_a(String string) {
       status = manager.deleteSchedule(string);
    }
    @Then("{string} should be excuted")
    public void should_be_excuted(String string) {
        assert status.equals(string);
    }
}
