package qa.desafio.pageobjects.automationpractice;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserMainPage {

    private RemoteWebDriver driver;
    private static final int TIMEOUT = 20;
    private WebDriverWait wait;

    public UserMainPage(RemoteWebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver, TIMEOUT);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(css = ".account")
    private WebElement txtUser;

    public boolean verificarLoginComSucesso(){
        return wait.until(ExpectedConditions.visibilityOf(txtUser)).isDisplayed();
    }

}
