package tme.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin={"json:target/cucumber.json",
                "rerun:target/rerun.txt"
        },
        features = "src/test/resources/features",
        glue = "tme/step_definitions",
        dryRun=false,
        tags= "@tmna and @smoke and @wip",
        monochrome = true

)

public class Cukes_runner {
}
