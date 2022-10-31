package qa.desafio.drivers;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;

public class AppiumService {

    private static AppiumDriverLocalService service;

    public static void startAppiumService() {
        AppiumServiceBuilder asb = new AppiumServiceBuilder();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Nexus_5X_API_28");
        asb.withIPAddress("0.0.0.0").usingPort(4723).withCapabilities(caps).build();
        service = AppiumDriverLocalService.buildService(asb);
        if (!service.isRunning()) {
            service.start();
        } else {
            System.out.println("O Servidor do Appium já está executando");
        }
    }

    public static void startAppiumServiceDesktop() {
        AppiumServiceBuilder asb;
        DesiredCapabilities cap;

        cap = new DesiredCapabilities();
        cap.setCapability("noReset", "false");
        asb = new AppiumServiceBuilder();
        asb.withIPAddress("0.0.0.0");
        asb.usingPort(4723);
        asb.withCapabilities(cap);
        asb.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        asb.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
        service = AppiumDriverLocalService.buildService(asb);
        service.start();
    }

    public static void startAppiumServiceDesktop(String host, int port) {
        AppiumServiceBuilder asb;
        DesiredCapabilities cap;

        cap = new DesiredCapabilities();
        cap.setCapability("noReset", "false");
        asb = new AppiumServiceBuilder();
        asb.withIPAddress(host);
        asb.usingPort(port);
        asb.withCapabilities(cap);
        asb.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        asb.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
        service = AppiumDriverLocalService.buildService(asb);
        service.start();
    }

    public static void stopAppiumService() throws IOException {
        service.stop();
        Runtime.getRuntime().exec("taskkill /F /IM node.exe");
    }

    public static URL getServiceUrl() {
        return service.getUrl();
    }

}
