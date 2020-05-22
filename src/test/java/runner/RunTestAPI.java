package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/features",
        tags = {"@API"},
        strict = true,
        glue = {"stepdef/api", "pretest"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)

public class RunTestAPI {

    @BeforeClass
    public static void setup(){

    }

    @AfterClass
    public static void tearDown(){

    }

}
