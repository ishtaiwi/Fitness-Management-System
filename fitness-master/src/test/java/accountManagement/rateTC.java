package accountManagement;

import fitness.helper.DataBasee;
import fitness.Review;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class rateTC {

    private Review newReview;
    private DataBasee dataBasee;

    @Given("the client navigates to the review page")
    public void the_client_navigates_to_the_review_page() {
        newReview = new Review();
        dataBasee = new DataBasee();
    }

    @When("the client fills in {string} with {string} for review")
    public void the_client_fills_in_with_for_review(String field, String value) {
        switch (field) {
            case "clientID" -> newReview.setClientId(value);
            case "clientUserName" -> newReview.setClientUserName(value);
            case "programName" -> newReview.setProgramName(value);
            case "rating" -> newReview.setRating(value);
            case "feedbackText", "feedBackText" -> newReview.setFeedBackText(value); // Handle both variants
            case "suggestion" -> newReview.setSuggestion(value);
            default -> throw new IllegalArgumentException("Invalid field: " + field);
        }
    }


    @When("the client submits the review form")
    public void the_client_submits_the_review_form() {
        dataBasee.saveReviewToFile("Reviews.json", newReview);
    }

    @Then("the client should see {string} for review")
    public void the_client_should_see_for_review(String expectedStatus) {
        assertEquals(expectedStatus, newReview.getStatus());
    }
}
