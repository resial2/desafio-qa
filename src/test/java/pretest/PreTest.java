package pretest;

import com.aventstack.extentreports.service.ExtentService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.remote.RemoteWebDriver;
import qa.desafio.Utils;
import qa.desafio.WebDriver;

import java.io.UnsupportedEncodingException;

public class PreTest {

    private static final String DEFAULT_PROPERTIES_FILES_PATH = "src/test/resources/properties/files.properties";
    public static RemoteWebDriver driver;
    public static PropertiesConfiguration propertiesConfig;

    @Before()
    public void init() throws UnsupportedEncodingException {
        ExtentService.getInstance().setGherkinDialect("pt");
        propertiesConfig = Utils.getProperties(DEFAULT_PROPERTIES_FILES_PATH);
    }

    @After()
    public void clean() {
        if(driver != null)
            driver.quit();
    }

}
