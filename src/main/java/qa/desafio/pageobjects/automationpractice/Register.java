package qa.desafio.pageobjects.automationpractice;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Register {

    private RemoteWebDriver driver;
    private static final int TIMEOUT = 20;
    private WebDriverWait wait;

    public Register(RemoteWebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(this.driver, TIMEOUT);
    }

    @FindBy(id = "id_gender1")
    private WebElement rdiTitleMr;

    @FindBy(id = "id_gender2")
    private WebElement rdiTitleMrs;

    @FindBy(id = "customer_firstname")
    private WebElement edtFirstName;

    @FindBy(id = "customer_lastname")
    private WebElement edtSecondName;

    @FindBy(id = "passwd")
    private WebElement edtPassword;

    @FindBy(id = "days")
    private WebElement slcDayOfBirth;

    @FindBy(id = "months")
    private WebElement slcMothOfBirth;

    @FindBy(id = "years")
    private WebElement slcYearOfBirth;

    @FindBy(id = "company")
    private WebElement edtCompany;

    @FindBy(id = "address1")
    private WebElement edtAddress;

    @FindBy(id = "city")
    private WebElement edtCity;

    @FindBy(id = "id_state")
    private WebElement cmbState;

    @FindBy(id = "postcode")
    private WebElement edtZipCode;

    @FindBy(id = "id_country")
    private WebElement cmbCountry;

    @FindBy(id = "other")
    private WebElement edtAdditionalInfo;

    @FindBy(id = "phone_mobile")
    private WebElement edtMobilePhone;

    @FindBy(id = "submitAccount")
    private WebElement btnSubmitAccount;

    public Register selecionarHonorifico(int title) {
        switch (title) {
            case 1:
                wait.until(ExpectedConditions.elementToBeClickable(rdiTitleMr)).click();
                System.out.println("Selecionado honorifico Mr.");
                return this;
            case 2:
                wait.until(ExpectedConditions.elementToBeClickable(rdiTitleMrs)).click();
                System.out.println("Selecionado honorifico Mrs.");
                return this;
            default:
                Assert.fail("Código de honorifico inválido");
                return null;
        }
    }

    public Register preencherNome(String nome) {
        wait.until(ExpectedConditions.elementToBeClickable(edtFirstName)).sendKeys(nome);
        System.out.println("Nome preenchido com: " + nome);
        return this;
    }

    public Register preencherSobrenome(String sobrenome) {
        wait.until(ExpectedConditions.elementToBeClickable(edtSecondName)).sendKeys(sobrenome);
        System.out.println("Sobrenome preenchido com: " + sobrenome);
        return this;
    }

    public Register preencherSenha(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(edtPassword)).sendKeys(password);
        System.out.println("Senha preenchido com: " + password);
        return this;
    }

    public Register selecionarDiaNascimento(int day) {
        Select select = new Select(slcDayOfBirth);
        select.selectByIndex(day);
        System.out.println("Selecionado o dia: " + day);
        return this;
    }

    public Register selecionarMesNascimento(int month) {
        Select select = new Select(slcMothOfBirth);
        select.selectByIndex(month);
        System.out.println("Selecionado o mês: " + month);
        return this;
    }

    public Register selecionarAnoNasciment(int year) {
        Select select = new Select(slcYearOfBirth);
        select.selectByValue(year + "");
        System.out.println("Selecionado o ano: " + year);
        return this;
    }

    public Register preencherCompania(String company) {
        wait.until(ExpectedConditions.elementToBeClickable(edtCompany)).sendKeys(company);
        System.out.println("Compania preenchido com: " + company);
        return this;
    }

    public Register preencherEndereco(String endereco) {
        wait.until(ExpectedConditions.elementToBeClickable(edtAddress)).sendKeys(endereco);
        System.out.println("Endereço preenchido com: " + endereco);
        return this;
    }

    public Register preencherCidade(String city) {
        wait.until(ExpectedConditions.elementToBeClickable(edtCity)).sendKeys(city);
        System.out.println("Cidade preenchido com: " + city);
        return this;
    }

    public Register selecionarEstado(int state) {
        Select select = new Select(cmbState);
        select.selectByIndex(state);
        System.out.println("Selecionado o estado com index: " + state);
        return this;
    }

    public Register preencherCep(String zipCode) {
        wait.until(ExpectedConditions.elementToBeClickable(edtZipCode)).sendKeys(zipCode);
        System.out.println("CEP preenchido com: " + zipCode);
        return this;
    }

    public Register selecionarPais(int country) {
        Select select = new Select(cmbCountry);
        select.selectByValue(country + "");
        System.out.println("Selecionado pais com value: " + country);
        return this;
    }

    public Register preencherInformacoesAdicionais(String additionalInfo) {
        wait.until(ExpectedConditions.elementToBeClickable(edtAdditionalInfo)).sendKeys(additionalInfo);
        System.out.println("Informações adicionais preenchidas com: " + additionalInfo);
        return this;
    }

    public Register preencherTelefoneCelular(String cellPhone) {
        wait.until(ExpectedConditions.elementToBeClickable(edtMobilePhone)).sendKeys(cellPhone);
        System.out.println("Celular preenchido com: " + cellPhone);
        return this;
    }

    public UserMainPage clicarBotaoCadastrar() {
        wait.until(ExpectedConditions.elementToBeClickable(btnSubmitAccount)).click();
        System.out.println("Botão de registrar clicado com sucesso.");
        return new UserMainPage(driver);
    }
}
