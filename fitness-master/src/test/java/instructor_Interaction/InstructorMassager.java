package instructor_Interaction;

import fitness.instructor_interaction.Massage;
import fitness.instructor_interaction.MassageManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InstructorMassager {
    Massage m;
    MassageManager manager;
    String status;
    int id =1001;

    @Given("I am in the messaging menu")
    public void iAmInTheMessagingMenu() {
        m = new Massage();
        manager = new MassageManager();
    }
    @When("I am an instructor With ID {string}")
    public void iAmAnInstructorWithID(String string) {
        m.setSenderId(string);
    }
    @When("I choose a client to message with {string}")
    public void iChooseAClientToMessageWith(String string) {

            m.setReceiverId(string);
    }

    @When("I write a message {string}")
    public void iWriteAMessage(String string) {
            m.setMassageText(string);
    }
    @When("I hit enter")
    public void iHitEnter() {
        status = manager.send(m);

    }
    @Then("the message should be {string}")
    public void theMessageShouldBe(String string) {
        System.out.println(status);
        assert status.equals(string);
    }









}
