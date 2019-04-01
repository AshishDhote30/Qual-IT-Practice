
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    strict = true,
    features = "classpath:features",
    glue = "StepDefinitions",
    monochrome = false,
    tags = "~@ignore",
    plugin = {"html:target/cucumber-html-report",
            "json:target/cucumber", "pretty:target/cucumber-pretty.txt",
            "usage:target/cucumber-usage.json", "junit:target/cucumber-results.xml" }

)
public class TestRunner {
}

