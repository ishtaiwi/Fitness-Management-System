package programManager;



import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)


@CucumberOptions(plugin = "html:outputTesting/deleteSchedule.html" , features = {"features/instructorfeatures/insructor_Delete_schedule.feature"},
        monochrome = true,snippets = CucumberOptions.SnippetType.CAMELCASE, glue = {"programManager"} )



public class DeleteScheduletester {
}
