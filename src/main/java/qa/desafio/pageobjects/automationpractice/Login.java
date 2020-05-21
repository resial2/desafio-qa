package qa.desafio.pageobjects.automationpractice;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {

    private RemoteWebDriver driver;
    private static final int TIMEOUT = 20;
    private WebDriverWait wait;

    public Login(RemoteWebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(this.driver, TIMEOUT);
    }

    @FindBy(id = "email")
    private WebElement edtEmail;

    @FindBy(id = "passwd")
    private WebElement edtPassword;

    @FindBy(id = "SubmitLogin")
    private WebElement btnLogin;

    @FindBy(css = ".alert.alert-danger ol li")
    private WebElement txtAlert;

    public Login preencherEmail(String email){
        wait.until(ExpectedConditions.elementToBeClickable(edtEmail)).sendKeys(email);
        System.out.println("O campo de e-mail foi preenchido com o texto: " + email);
        return this;
    }

    public Login preencherSenha(String password){
        wait.until(ExpectedConditions.elementToBeClickable(edtPassword)).sendKeys(password);
        System.out.println("O campo de senha foi preenchido com o texto: " + password);
        return this;
    }

    public UserMainPage clicarBotaoLogin(){
        wait.until(ExpectedConditions.elementToBeClickable(btnLogin)).click();
        System.out.println("O botão de login foi clicado.");
        return new UserMainPage(driver);
    }

    public String pegarTextoAlerta(){
        return wait.until(ExpectedConditions.visibilityOf(txtAlert)).getText();
    }


}
