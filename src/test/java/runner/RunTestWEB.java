package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/features",
        tags = {"@LOGIN"},
        strict = true,
        glue = {"stepdef/web"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)

public class RunTestWEB {

    @BeforeClass
    public static void setup(){

    }

    @AfterClass
    public static void tearDown(){

    }

}
