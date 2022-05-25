package tme.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin={"pretty",
                "json:target/cucumber.json",
                "html:target/default-html-reports",
                "rerun:target/rerun.txt"
        },
        features = "src/test/resources/features",
        glue = "tme/step_definitions",
       monochrome = true,
        dryRun=false,
        tags="@post"

)

public class Cukes_runner {
}
