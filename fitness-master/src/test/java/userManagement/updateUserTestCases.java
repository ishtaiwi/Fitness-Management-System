package userManagement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import fitness.admin_management.UserManagement;
public class updateUserTestCases {
    UserManagement usermanagement;
    boolean status;
    @Given("the admin is in update password page")
    public void the_admin_is_in_update_password_page() {
       usermanagement = new UserManagement();
    }

    @When("admin enter {string} and {string}")
    public void admin_enter_and(String string, String string2) {
        string = "1234**Ff";
        string2 = "osama1";
       status = usermanagement.changePassword(string,string2);
    }

    @Then("password will change")
    public void password_will_change() {
        assert(status);
    }

}
