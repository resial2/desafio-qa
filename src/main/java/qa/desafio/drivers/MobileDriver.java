package qa.desafio.drivers;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class MobileDriver {

    public String so;
    public String platform = "android";
    public AppiumDriver<MobileElement> driver;
    public String apkFile;

    public MobileDriver(String apkFile) {
        this.apkFile = apkFile;
        this.so = System.getProperty("os.name").toLowerCase();
    }

    public MobileDriver(String apkFile, String platform) {
        this.apkFile = apkFile;
        this.so = System.getProperty("os.name").toLowerCase();
        this.platform = platform;
    }

    public AppiumDriver<MobileElement> configDriver() {
        PropertiesConfiguration defaultPath = null;
        try {
            defaultPath = new PropertiesConfiguration("src/test/resources/properties/files.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
            Assert.fail("Falha ao ler arquivo básico de configuração do driver Mobile.");
        }

        PropertiesConfiguration props = null;
        try {
            props = new PropertiesConfiguration(defaultPath.getString("file.mobile"));
        } catch (ConfigurationException e) {
            e.printStackTrace();
            Assert.fail("Falha ao ler arquivo de configuração do driver Mobile.");
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equalsIgnoreCase("android")) {

            capabilities.setCapability("app", new File(apkFile));
            capabilities.setCapability("platform", so);
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", props.getString("device.name.android"));
            capabilities.setCapability("unicodeKeyboard", true);
            capabilities.setCapability("disableAndroidWatchers", true);
            try {
                driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Assert.fail("Falha ao configurar AVD.");
            }
        } else if (platform.equalsIgnoreCase("ios")) {
            capabilities.setCapability("app", new File(apkFile));
            capabilities.setCapability("platform", so);
            capabilities.setCapability("platformName", "ios");
            capabilities.setCapability("deviceName", props.getString("device.name.ios"));
            capabilities.setCapability("unicodeKeyboard", true);
            capabilities.setCapability("disableAndroidWatchers", true);
            try {
                driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Assert.fail("Falha ao configurar Device.");
            }
        }

        return driver;

    }

}
