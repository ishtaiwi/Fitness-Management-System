package instructor_Interaction;



import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)


@CucumberOptions(plugin = "html:outputTesting/sendMessage.html" , features = {"features/instructorfeatures/instructor_client_interaction.feature"},
        monochrome = true,snippets = CucumberOptions.SnippetType.CAMELCASE, glue = {"instructor_Interaction"} )


public class SendMessage {
}
