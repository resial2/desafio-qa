package qa.desafio.drivers;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class DesktopDriver {

    private WindowsDriver driver;
    private String appPath;

    public DesktopDriver(String appPath){

        AppiumService.startAppiumServiceDesktop();
        this.appPath = appPath;

    }

    public WindowsDriver startDesktopDriver(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("app", appPath);
        caps.setCapability("appWorkingDir", appPath.substring(0, appPath.lastIndexOf('\\')));
        caps.setCapability("deviceName", "WindowsPC");
        caps.setCapability("platformName", "Windows");
        driver = new WindowsDriver(AppiumService.getServiceUrl(), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }

}
