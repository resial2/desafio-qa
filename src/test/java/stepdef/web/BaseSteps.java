package stepdef.web;

import com.aventstack.extentreports.service.ExtentService;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.pt.*;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Assert;
import qa.desafio.Utils;
import qa.desafio.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import qa.desafio.pageobjects.automationpractice.Home;
import qa.desafio.pageobjects.automationpractice.Login;
import qa.desafio.pageobjects.automationpractice.Register;
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
        propertiesConfig = Utils.getProperties(DEFAULT_PROPERTIES_FILES_PATH);
    }

    @After()
    public void clean() {
        driver.quit();
    }

    private Home home;
    private Login login;
    private UserMainPage userMainPage;
    private Register register;

    @Dada("abertura do site na página principal")
    public void abertura_do_site_na_página_principal() {
        home = new Home(driver);
        home.acessarPaginaInicial();
    }

    @Quando("clicar no botão {string}")
    public void clicarNoBotão(String button) {

        switch (button.toUpperCase()) {
            case "SIGN IN":
                login = home.clicarBotaoSignIn();
                break;
            case "CREATE AN ACCOUNT":
                register = login.clicarBotaoCriarConta();
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
        switch (button.toUpperCase()) {
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
        Assert.assertEquals("Texto esperado e Texto apresentado estão divergentes. [ Apresentado: " + textoApresentado + " ] [ Esperado: " + mensagemEsperada + " }", mensagemEsperada, textoApresentado);
    }


    @E("preencher o campo de e-mail para cadastro com um e-mail gerado aleatóriamente, salvando a informação no arquivo de {string}")
    public void preencherOCampoDeEMailParaCadastroComUmEMailGeradoAleatóriamente(String file) throws ConfigurationException {
        String email = login.preencherCampoEmailRegister();
        PropertiesConfiguration props = Utils.getProperties(propertiesConfig.getString(file));
        props.setProperty("valid.email", email);
        props.save();
    }

    @E("realizar o preenchimento do formulário para cadastro com uma senha gerada aleatóriamente que será salva no arquivo {string}")
    public void realizarOPreenchimentoDoFormulárioParaCadastroComUmaSenhaGeradaAleatóriamenteQueSeráSalvaNoArquivo(String file) throws ConfigurationException {
        PropertiesConfiguration props = Utils.getProperties(propertiesConfig.getString(file));
        int password = Utils.gerarNumeroRandom(100000, 100000000);
        props.setProperty("valid.password", password);
        props.save();
        userMainPage = register.selecionarHonorifico(Utils.gerarNumeroRandom(1, 2))
                .preencherNome(Utils.gerarLetrasAleatorias(8))
                .preencherSobrenome(Utils.gerarLetrasAleatorias(8))
                .preencherSenha(password + "")
                .selecionarDiaNascimento(Utils.gerarNumeroRandom(1, 28))
                .selecionarMesNascimento(Utils.gerarNumeroRandom(1, 12))
                .selecionarAnoNasciment(Utils.gerarNumeroRandom(1980, 1999))
                .preencherCompania(Utils.gerarLetrasAleatorias(15))
                .preencherEndereco(Utils.gerarLetrasAleatorias(12))
                .preencherCidade(Utils.gerarLetrasAleatorias(14))
                .selecionarEstado(Utils.gerarNumeroRandom(1, 50))
                .preencherCep(Utils.gerarNumeroRandom(10000, 99999) + "")
                .selecionarPais(21)
                .preencherInformacoesAdicionais(Utils.gerarLetrasAleatorias(18))
                .preencherTelefoneCelular(Utils.gerarNumeroRandom(10000000, 99999999) + "")
                .clicarBotaoCadastrar();
    }
}
