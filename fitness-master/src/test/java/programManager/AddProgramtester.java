package programManager;



import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)


@CucumberOptions(plugin = "html:outputTesting/addProgram.html" , features = {"C:\\Users\\sshay\\IdeaProjects\\fitness\\features\\instructorfeatures\\instructor_manage_program.feature"},
        monochrome = true,snippets = CucumberOptions.SnippetType.CAMELCASE, glue = {"programManager"} )


public class AddProgramtester {
}
