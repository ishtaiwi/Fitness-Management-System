package userManagement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import fitness.admin_management.UserManagement;
import io.cucumber.java.en.When;

public class updateActiveTestCases {

    boolean status;
    UserManagement usermanagement;
    @Given("the admin is on the Update Activation page")
    public void the_admin_is_on_the_update_activation_page() {
       usermanagement = new UserManagement();
    }

    @When("the admin enters the {string} status for {string}")
    public void the_admin_enters_the_status_for(String string, String string2) {
        string2 = "osama12";
        boolean active = false;
        status = usermanagement.updateActive(string2 , active);
    }

    @Then("the activation status of username will be updated successfully")
    public void the_activation_status_of_username_will_be_updated_successfully() {
        assert(status);
    }


}
