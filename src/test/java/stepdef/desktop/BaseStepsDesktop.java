package stepdef.desktop;

import io.appium.java_client.windows.WindowsDriver;
import io.cucumber.java.an.Dada;
import io.cucumber.java.pt.Entao;
import org.openqa.selenium.By;
import qa.desafio.drivers.AppiumService;
import qa.desafio.drivers.DesktopDriver;

import java.io.IOException;

public class BaseStepsDesktop {

    private WindowsDriver driver;
    @Dada("abertura do driver desktop")
    public void aberturaDoDriverDesktop(){
        DesktopDriver driver = new DesktopDriver("C:\\Windows\\System32\\notepad.exe");
        this.driver = driver.startDesktopDriver();
    }

    @Entao("encerrarei o driver")
    public void encerrareiODriver() throws IOException, InterruptedException {
        driver.findElement(By.name("Editor de Texto")).sendKeys("O amo a Malia Mallety :3 <3");
        Thread.sleep(3000);
        driver.findElement(By.name("Editor de Texto")).clear();
        driver.quit();
        AppiumService.stopAppiumService();
    }
}
