package userManagement;

import fitness.user_management.User;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import fitness.admin_management.UserManagement;

import static org.junit.Assert.assertEquals;

public class addUserTestCases {

    private UserManagement usermanagement;
    private User user;
    private String status;

    @When("admin is in add-user page")
    public void admin_is_in_add_user_page() {
        usermanagement = new UserManagement();
        user = new User();
    }

    @When("he fills in {string} with {string} for add")
    public void he_fills_in_with_for_add(String field, String value) {
        switch (field) {
            case "ID" -> user.setId(value);
            case "username" -> user.setUsername(value);
            case "firstName" -> user.setFirstName(value);
            case "lastName" -> user.setLastName(value);
            case "phoneNumber" -> user.setPhoneNumber(value);
            case "password" -> user.setPassword(value);
            case "email" -> user.setEmail(value);
            case "userType" -> user.setUserType(value);
            default -> {
                assert (false);
            }
        }
        assert(true);

    }

    @When("he submits the add form")
    public void he_submits_the_add_form() {
        user.setActive(true);
        usermanagement.addUser(user);
        status = usermanagement.getStatus();

    }

    @Then("he should see {string} for add")
    public void he_should_see_for_add(String string) {
        assertEquals(status,string);

    }
}
