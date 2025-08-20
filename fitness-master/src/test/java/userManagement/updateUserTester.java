package userManagement;




import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)


@CucumberOptions(plugin = "html:outputTesting/updateUser.html" , features = {"userManagementFeature/updateUser.feature"},
        monochrome = true,snippets = CucumberOptions.SnippetType.CAMELCASE, glue = {"userManagement"} )


public class updateUserTester {
}
