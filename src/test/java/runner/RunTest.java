package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/features",
        tags = {},
        strict = true,
        glue = {"stepdef", "pretest"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)

public class RunTest {

    @BeforeClass
    public static void setup(){

    }

    @AfterClass
    public static void tearDown(){

    }

}
