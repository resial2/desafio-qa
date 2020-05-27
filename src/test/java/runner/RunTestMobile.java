package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import qa.desafio.drivers.MobileDriver;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/features",
        tags = {"@MOBILE"},
        strict = true,
        glue = {"stepdef", "pretest"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)

public class RunTestMobile {

    @BeforeClass
    public static void setup(){

    }

    @AfterClass
    public static void tearDown(){
        MobileDriver.stopAppiumService();
    }

}
