package instructor_Interaction;




import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)


@CucumberOptions(plugin = "html:outputTesting/comment.html" , features = {"features/instructorfeatures/instructor_forum.feature"},
        monochrome = true,snippets = CucumberOptions.SnippetType.CAMELCASE, glue = {"instructor_Interaction"} )


public class InstructorForumTester {
}
