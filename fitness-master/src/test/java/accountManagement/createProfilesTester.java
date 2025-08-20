package accountManagement;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)


@CucumberOptions(plugin = "html:outputTesting/createProfiles.html" , features = {"features/clientFeatures/createProfiles.feature"},
        monochrome = true,snippets = CucumberOptions.SnippetType.CAMELCASE, glue = {"accountManagement"} )

public class createProfilesTester {
}
