package qa.desafio.pageobjects.automationpractice;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {

    private RemoteWebDriver driver;
    private static final int TIMEOUT = 20;
    private WebDriverWait wait;
    private static final String HOME_URL = "http://automationpractice.com/index.php";


    public Home(RemoteWebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(this.driver, TIMEOUT);
    }

    @FindBy(css = ".login")
    private WebElement btnSignIn;

    @FindBy(css = ".logo.img-responsive")
    private WebElement imgLogo;

    public Home acessarPaginaInicial(){
        driver.get(HOME_URL);
        wait.until(ExpectedConditions.visibilityOf(imgLogo));
        System.out.println("Página principal acessada pelo link: " + HOME_URL);
        return this;
    }

    public Login clicarBotaoSignIn(){
        wait.until(ExpectedConditions.elementToBeClickable(btnSignIn)).click();
        System.out.println("Botão Sign In clicado");
        return new Login(driver);
    }

}
