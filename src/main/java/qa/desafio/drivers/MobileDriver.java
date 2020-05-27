package qa.desafio.drivers;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class MobileDriver {

    private final String so;
    private String platform = "android";
    private AppiumDriver<MobileElement> driver;
    private final String apkFile;



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

            capabilities.setCapability(MobileCapabilityType.APP, new File(apkFile).getAbsolutePath());
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android-Device");
            capabilities.setCapability("avd", props.getString("virtual.device.name.android"));
            capabilities.setCapability("noReset", true);
            capabilities.setCapability("autoGrantPermissions", true);
            capabilities.setCapability("uiautomator2ServerInstallTimeout", 360000);
            capabilities.setCapability("fullReset", false);
            capabilities.setCapability("platform", so);

            AppiumService.startAppiumService();
            driver = new AndroidDriver<>(AppiumService.getServiceUrl(), capabilities);

        } else if (platform.equalsIgnoreCase("ios")) {
            capabilities.setCapability(MobileCapabilityType.APP, new File(apkFile).getAbsolutePath());
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ios");
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iOS");
            capabilities.setCapability("noReset", true);
            capabilities.setCapability("avd", props.getString("virtual.device.name.ios"));
            capabilities.setCapability("platform", so);
            capabilities.setCapability("fullReset", false);

            AppiumService.startAppiumService();
            driver = new IOSDriver<>(AppiumService.getServiceUrl(), capabilities);

        }
        return driver;
    }



}
