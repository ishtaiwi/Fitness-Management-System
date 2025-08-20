package instructor_Interaction;

import fitness.instructor_interaction.Comment;
import fitness.instructor_interaction.Forum;
import fitness.instructor_interaction.ForumManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InstructorForum {
    ForumManager manager;
    Forum f;
    String instructorId;
    String programId;
    String commentText;
    String forumStatus;
    String postStatus;

    @Given("I am an instructor with {string}")
    public void i_am_an_instructor_with(String id) {
        this.instructorId = id;
    }

    @Given("I am in the discussion forums menu")
    public void i_am_in_the_discussion_forums_menu() {
        manager = new ForumManager();
        f = new Forum();
    }

    @Given("I open the forum for {string}")
    public void i_open_the_forum_for(String programId) {
     /*nothing to do here*/
    }
    @Given("{string}")
    public void string(String string) {
        this.programId =  string;
        f.setProgramID(programId);
    }
    @When("I write a comment {string}")
    public void i_write_a_comment(String commentText) {
        this.commentText = commentText;
    }

    @When("I post the comment")
    public void i_post_the_comment() {
        Comment c = new Comment(commentText, instructorId);


        forumStatus = f.addComment(c);

        postStatus = manager.add(f);
    }

    @Then("the comment should be {string}")
    public void the_comment_should_be(String expectedStatus) {
        assert expectedStatus.equals(forumStatus);
        assert postStatus.equals("success");
    }
}
