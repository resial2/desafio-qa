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
import qa.desafio.Utils;
import qa.desafio.drivers.MobileDriver;
import qa.desafio.pageobjects.cadastrocliente.DetalhesCliente;
import qa.desafio.pageobjects.cadastrocliente.Home;

import static pretest.PreTest.propertiesConfig;

public class BaseStepsMobile {

    private AppiumDriver<MobileElement> driver;

    private Home home;
    private DetalhesCliente detalhesCliente;

    @Dada("abertura do aplicativo {string}")
    public void aberturaDoAplicativo(String aplicativo) throws ConfigurationException {
        PropertiesConfiguration appFile = new PropertiesConfiguration(propertiesConfig.getString("file.app"));
        MobileDriver mobileDriver = new MobileDriver(appFile.getString(aplicativo), "android");
        driver = mobileDriver.configDriver();
    }

    @Quando("realizar a pesquisa pelo nome do arquivo {string}")
    public void realizarAPesquisaPeloNome(String name) throws ConfigurationException {
        home = new Home(driver);
        PropertiesConfiguration props = new PropertiesConfiguration(propertiesConfig.getString(name));
        home.preencherCampoPesquisa(props.getString("cliente.nome"));
        detalhesCliente = home.selecionarPrimeiroResultadoPesquisa();
    }

    @Entao("deverá ser exibida a tela de informações do usuário contendo o nome pesquisado")
    public void deveráSerExibidaATelaDeInformaçõesDoUsuárioContendoONomePesquisado() {
        Assert.assertTrue("Os nomes estão divergentes.", detalhesCliente.validarNome());
        System.out.println("Usuário localizado.");
    }

    @E("encerrarei o aplicativo")
    public void encerrareiOAplicativo() {
        driver.quit();
    }

    @Quando("clicar no botão de Menu")
    public void clicarNoBotãoDeMenu() {
        home = new Home(driver);
        home.clicarBotaoMenu();
    }

    @E("selecionar a opção Cadastrar Novo")
    public void selecionarAOpçãoCadastrarNovo() {
        detalhesCliente = home.clicarBotaoCadastrarNovo();
    }

    private String mensagemCadastro;
    @Entao("será realizado o preenchimento das informações aleatórias necessárias")
    public void seráRealizadoOPreenchimentoDasInformaçõesAleatóriasNecessárias() {
        mensagemCadastro = detalhesCliente
                .preencherNome(Utils.gerarLetrasAleatorias(7) + " " + Utils.gerarLetrasAleatorias(10))
                .preencherRG(Utils.gerarNumeroRandom(100000000, 999999999))
                .preencherCPF(Utils.gerarNumeroRandom(1000000000, 1999999999))
                .preencherDataNascimento(Utils.gerarNumeroRandom(1, 28) + String.valueOf(Utils.gerarNumeroRandom(1, 12)) + Utils.gerarNumeroRandom(1980, 2002))
                .preencherEndereco(Utils.gerarLetrasAleatorias(14))
                .preencherNumero(Utils.gerarNumeroRandom(1, 999))
                .preencherBairro(Utils.gerarLetrasAleatorias(20))
                .preencherCEP(Utils.gerarNumeroRandom(10000000, 99999999))
                .preencherCidade(Utils.gerarLetrasAleatorias(12))
                .preencherTelefone1(Utils.gerarNumeroRandom(100000000, 999999999))
                .preencherEmail(Utils.gerarLetrasAleatorias(6) + "@" + Utils.gerarLetrasAleatorias(8) + ".com")
                .clicarSalvar()
                .pegarMensagemCadastro();

    }

    @E("deverá ser exibida a mensagem de {string}")
    public void deveráSerExibidaAMensagemDe(String mensagemEsperada) {
        Assert.assertEquals("As mensagens estão divergentes", mensagemEsperada, mensagemCadastro);
        System.out.println("Cadastro realizado com êxito.");
    }

    @Entao("será realizada a exclusão do usuário")
    public void seráRealizadaAExclusãoDoUsuário() {
        home = detalhesCliente.clicarBotaoExcluir().clicarBotaoSim();
    }

    @E("deverá ser exibida a mensagem de exclusão {string}")
    public void deveráSerExibidaAMensagemDeExclusão(String mensagemEsperada) {
        String mensagem = home.pegarMensagemExclusão();
        Assert.assertEquals("As mensagens estão divergentes", mensagemEsperada, mensagem);
        System.out.println("Exclusão realizada com êxito.");
    }
}
