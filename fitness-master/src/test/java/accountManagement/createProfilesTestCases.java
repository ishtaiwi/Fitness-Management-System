package accountManagement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import fitness.accountmanagement.AccountManagement;
import fitness.accountmanagement.AccountManagementManager;

import static org.junit.Assert.assertEquals;

public class createProfilesTestCases {

    private AccountManagementManager manager;
    private AccountManagement account;
    private String resultMessage;
    private String goals, diet;
    private int age;
    Integer  id=1001;
    @Given("the user navigates to the registration page")
    public void the_user_navigates_to_the_registration_page() {
        this.manager = new AccountManagementManager();
    }

    @When("he fills in {string} with {string} for create")
    public void he_fills_in_with_for_create(String field, String value) {
        switch (field) {

            case "age" -> age = Integer.parseInt(value);
            case "fitnessGoals" -> goals = value;
            case "dietaryPreferences" -> diet = value;

            default -> {
                assert(false);
            }

        }

        assert(true);
    }

    @When("he submits the create form")
    public void he_submits_the_create_form() {
        account = new AccountManagement(age,goals,diet,String.valueOf(id));
        account.setAge(String.valueOf(age));
        account.setFitnessGoals(goals);
        account.setDietaryPreferences(diet);
        account.setUsrId(String.valueOf(id));

        id++;
        resultMessage = manager.addUser(account);

    }

    @Then("he should see {string} for create")
    public void he_should_see_for_create(String string) {
        assertEquals(resultMessage, string);

    }






}
