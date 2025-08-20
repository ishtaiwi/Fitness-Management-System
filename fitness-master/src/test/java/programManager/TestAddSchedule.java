package programManager;

import fitness.programmanager.Notification;
import fitness.programmanager.Schedule;
import fitness.programmanager.ScheduleManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import fitness.programmanager.NotificationManager;

import static org.junit.Assert.assertEquals;

public class TestAddSchedule {
    ScheduleManager manager;
    Schedule sc;
    String status;
    String status2;
    Notification notification;
    NotificationManager notificationManager;

    // Variables for schedule details
    private String location;
    private Integer maxParticipants;
    private String time;
    private String date;
    private String type;
    private String programId;
    private String num;

    @Given("I am an instructor in add schedule")
    public void i_am_an_instructor_in_add_schedule() {
        manager = new ScheduleManager();
    }

    @When("I set the schedule num as {string}")
    public void iSetTheScheduleNumAs(String num) {
        this.num = num;
    }

    @And("create a new group session for {string}")
    public void createANewGroupSessionFor(String programId) {
        this.programId = programId;
    }

    @And("I set the session type as {string}")
    public void iSetTheSessionTypeAs(String type) {
        this.type = type;
    }

    @And("I set the session date as {string}")
    public void iSetTheSessionDateAs(String date) {
        this.date = date;
    }

    @And("I set the session time as {string}")
    public void iSetTheSessionTimeAs(String time) {
        this.time = time;
    }

    @And("I set the maximum participants to {string}")
    public void iSetTheMaximumParticipantsTo(String maxParticipants) {
        this.maxParticipants = maxParticipants.isEmpty() ? 0 : Integer.parseInt(maxParticipants);
    }

    @And("I set the location as {string}")
    public void iSetTheLocationAs(String location) {
        this.location = location;
    }

    @Then("the session should be processed")
    public void theSessionShouldBeProcessed() {
        // Create the Schedule object using the constructor
        sc = new Schedule(location, maxParticipants, time, date, type, programId, num);
        status = manager.add(sc);
    }

    @And("message with {string} is printed on")
    public void message_with_is_printed_on(String string) {
        assertEquals(string, status);
    }

    @And("participants will be notified.")
    public void participantsWillBeNotified() {
        notification = new Notification();
        notificationManager = new NotificationManager();
        notification.setNotificationText(
                "Program:" + sc.getProgramId() +
                        " Schedule has been updated \nDate:" + sc.getDate() +
                        "\nLocation:" + sc.getLocation() +
                        "\nTime:" + sc.getTime()
        );
        notification.setSeen(false);
        status2 = notificationManager.sendToAllParticipants(notification, sc.getProgramId());
        assertEquals("participants notified", status2);
        System.out.println(notification.toString());
    }
}
