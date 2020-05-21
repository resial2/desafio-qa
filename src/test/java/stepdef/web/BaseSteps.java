package stepdef.web;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.pt.*;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import qa.desafio.Utils;
import qa.desafio.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


public class BaseSteps {

    private static final String DEFAULT_PROPERTIES_FILES_PATH = "src/test/resources/properties/files.properties";
    private static RemoteWebDriver driver;
    private static PropertiesConfiguration propertiesConfig;

    @Before()
    public void config(){
        WebDriver webDriver = new WebDriver();
        driver = webDriver.configDriver("CHROME");
        propertiesConfig =  Utils.getProperties(DEFAULT_PROPERTIES_FILES_PATH);
    }

    @After()
    public void clean(){
        driver.quit();
    }

    @Dada("abertura do site na página principal")
    public void abertura_do_site_na_página_principal() {

    }

    @Quando("clicar no botão {string}")
    public void clicarNoBotão(String button) {
    }

    @E("preencher os campos de e-mail e senha com as informações do arquivo de {string}")
    public void preencherOsCamposDeEMailESenhaComAsInformaçõesDoArquivoDe(String file) throws ConfigurationException {
        PropertiesConfiguration props = Utils.getProperties(propertiesConfig.getString(file));

    }

    @E("clicar no botão {string} para entrar")
    public void clicarNoBotãoParaEntrar(String button) {
    }

    @E("preencher os campos de e-mail e senha com as informações inválidas do arquivo de {string}")
    public void preencherOsCamposDeEMailESenhaComAsInformaçõesInválidasDoArquivoDe(String file) {
        PropertiesConfiguration props = Utils.getProperties(propertiesConfig.getString(file));
    }

    @E("não realizar o preenchimento dos campos de e-mail e senha")
    public void nãoRealizarOPreenchimentoDosCamposDeEMailESenha() {
    }

    @Entao("deverá ser exibida a pagina principal do usuário")
    public void deveráSerExibidaAPaginaPrincipalDoUsuário() {
    }

    @Entao("deverá ser exibida a mensagem {string}")
    public void deveráSerExibidaAMensagem(String expectedMessage) {
    }



}
