package qa.desafio.drivers;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
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

    public static void stopAppiumService() throws IOException {
        service.stop();
        Runtime.getRuntime().exec("taskkill /F /IM node.exe");
    }

    public static URL getServiceUrl(){
        return service.getUrl();
    }

}
