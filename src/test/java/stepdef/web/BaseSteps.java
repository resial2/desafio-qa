package stepdef.web;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.service.ExtentService;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.pt.*;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Assert;
import qa.desafio.Utils;
import qa.desafio.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import qa.desafio.pageobjects.automationpractice.Home;
import qa.desafio.pageobjects.automationpractice.Login;
import qa.desafio.pageobjects.automationpractice.UserMainPage;

import java.io.UnsupportedEncodingException;


public class BaseSteps {

    private static final String DEFAULT_PROPERTIES_FILES_PATH = "src/test/resources/properties/files.properties";
    private static RemoteWebDriver driver;
    private static PropertiesConfiguration propertiesConfig;

    @Before()
    public void init() throws UnsupportedEncodingException {
        ExtentService.getInstance().setGherkinDialect("pt");
        WebDriver webDriver = new WebDriver();
        driver = webDriver.configDriver("CHROME");
        propertiesConfig =  Utils.getProperties(DEFAULT_PROPERTIES_FILES_PATH);
    }

    @After()
    public void clean(){
        driver.quit();
    }

    private Home home;
    private Login login;
    private UserMainPage userMainPage;

    @Dada("abertura do site na página principal")
    public void abertura_do_site_na_página_principal() {
        home = new Home(driver);
        home.acessarPaginaInicial();
    }

    @Quando("clicar no botão {string}")
    public void clicarNoBotão(String button) {

        switch(button.toUpperCase()){
            case "SIGN IN":
                login = home.clicarBotaoSignIn();
                break;
            default:
                Assert.fail("Botão não configurado.");
        }

    }

    @E("preencher os campos de e-mail e senha com as informações do arquivo de {string}")
    public void preencherOsCamposDeEMailESenhaComAsInformaçõesDoArquivoDe(String file) {
        PropertiesConfiguration props = Utils.getProperties(propertiesConfig.getString(file));
        login.preencherEmail(props.getString("valid.email"))
                .preencherSenha(props.getString("valid.password"));
    }

    @E("clicar no botão {string} para entrar")
    public void clicarNoBotãoParaEntrar(String button) {
        switch (button.toUpperCase()){
            case "SIGN IN":
                userMainPage = login.clicarBotaoLogin();
                break;
            default:
                Assert.fail("Botão não configurado.");
        }
    }

    @E("preencher os campos de e-mail e senha com as informações inválidas do arquivo de {string}")
    public void preencherOsCamposDeEMailESenhaComAsInformaçõesInválidasDoArquivoDe(String file) {
        PropertiesConfiguration props = Utils.getProperties(propertiesConfig.getString(file));
        login.preencherEmail(props.getString("invalid.email"))
                .preencherSenha(props.getString("invalid.password"));
    }

    @E("não realizar o preenchimento dos campos de e-mail e senha")
    public void nãoRealizarOPreenchimentoDosCamposDeEMailESenha() {
    }

    @Entao("deverá ser exibida a pagina principal do usuário")
    public void deveráSerExibidaAPaginaPrincipalDoUsuário() {
        Assert.assertTrue("Login não realizado", userMainPage.verificarLoginComSucesso());
        System.out.println("Login realizado com êxito.");
    }

    @Entao("deverá ser exibida a mensagem {string}")
    public void deveráSerExibidaAMensagem(String mensagemEsperada) {
        String textoApresentado = login.pegarTextoAlerta();
        Assert.assertEquals("Texto esperado e Texto apresentado estão divergentes. [ Apresentado: " + textoApresentado +" ] [ Esperado: " + mensagemEsperada +" }", mensagemEsperada, textoApresentado);
    }



}
