package stepdef.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.pt.Dada;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Assert;
import qa.desafio.drivers.MobileDriver;
import qa.desafio.pageobjects.cadastrocliente.DetalhesCliente;
import qa.desafio.pageobjects.cadastrocliente.Home;

import static pretest.PreTest.propertiesConfig;

public class BaseStepsMobile {

    private AppiumDriver<MobileElement> driver;

    private Home home;
    private DetalhesCliente detalhesCliente;

    @Dada("abertura do aplicativo {string}")
    public void aberturaDoAplicativo(String aplicativo) throws ConfigurationException, InterruptedException {
        PropertiesConfiguration appFile = new PropertiesConfiguration(propertiesConfig.getString("file.app"));
        MobileDriver mobileDriver = new MobileDriver(appFile.getString(aplicativo), "android");
        driver = mobileDriver.configDriver();
    }

    @Quando("realizar a pesquisa pelo nome {string}")
    public void realizarAPesquisaPeloNome(String name) {
        home = new Home(driver);
        home.preencherCampoPesquisa(name);
        detalhesCliente = home.selecionarPrimeiroResultadoPesquisa();
    }

    @Entao("deverá ser exibida a tela de informações do usuário contendo o nome pesquisado")
    public void deveráSerExibidaATelaDeInformaçõesDoUsuárioContendoONomePesquisado() {
        Assert.assertTrue("Os nomes estão divergentes.", detalhesCliente.validarNome());
    }

    @E("encerrarei o aplicativo")
    public void encerrareiOAplicativo() {
        driver.quit();
    }

    @Quando("clicar no botão de Menu")
    public void clicarNoBotãoDeMenu() {
    }

    @E("selecionar a opção Cadastrar Novo")
    public void selecionarAOpçãoCadastrarNovo() {
    }
}
