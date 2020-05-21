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
        glue = "stepdef",
        plugin = {}
)

public class RunTest {

    @BeforeClass
    public static void setup(){

    }

    @AfterClass
    public static void tearDown(){

    }

}
