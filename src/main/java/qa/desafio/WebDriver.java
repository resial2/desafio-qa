package qa.desafio;

import org.junit.Assert;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;

public class WebDriver {

    private RemoteWebDriver driver;
    private String so;
    private static final String WEBDRIVER_CHROME = "webdriver.chrome.driver";

    public WebDriver(){
        this.so = System.getProperty("os.name").toLowerCase();
    }

    public RemoteWebDriver configDriver(String browser){

        switch (browser.toUpperCase()){
            case "CHROME":
                startChrome();
                return this.driver;
            default:
                Assert.fail("Navegador não configurado. Utilize: \"CHROME\"");
        }
        return null;

    }

    private void startChrome(){

        DesiredCapabilities caps = new DesiredCapabilities();

        if (so.contains("linux")) {
            System.setProperty(WEBDRIVER_CHROME, "./src/test/resources/drivers/chromedriver");
            caps.setPlatform(Platform.LINUX);
        } else if (so.startsWith("windows")) {
            System.setProperty(WEBDRIVER_CHROME, "./src/test/resources/drivers/chromedriver.exe");
            caps.setPlatform(Platform.WINDOWS);
        } else if (so.contains("mac")){
            System.setProperty(WEBDRIVER_CHROME, "./src/test/resources/drivers/chromedriver");
            caps.setPlatform(Platform.MAC);
        }

        ChromeOptions chromeOptions = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();

        chromeOptions.addArguments("--start-maximized", "--ignore-ssl-errors", "--ignore-certificate-errors");
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);

        driver = new ChromeDriver(chromeOptions);

    }

}
