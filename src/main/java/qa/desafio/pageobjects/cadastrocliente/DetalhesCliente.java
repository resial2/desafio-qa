package qa.desafio.pageobjects.cadastrocliente;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;

public class DetalhesCliente {

    private AppiumDriver<MobileElement> driver;
    private String name;

    public DetalhesCliente(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public DetalhesCliente(AppiumDriver<MobileElement> driver, String name) {
        this.driver = driver;
        this.name = name;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    @AndroidFindBy(id = "android:id/message")
    @iOSXCUITFindBy(id = "iosId")
    private MobileElement msgStatusCadastro;

    @AndroidFindBy(id = "br.com.dudstecnologia.cadastrodeclientes:id/editNome")
    @iOSXCUITFindBy(id = "iosId")
    private MobileElement edtName;

    @AndroidFindBy(id = "br.com.dudstecnologia.cadastrodeclientes:id/btnExcluir")
    @iOSXCUITFindBy(id = "iosId")
    private MobileElement btnExcluir;

    @AndroidFindBy(id = "android:id/button1")
    @iOSXCUITFindBy(id = "iosId")
    private MobileElement btnSim;

    @AndroidFindBy(id = "br.com.dudstecnologia.cadastrodeclientes:id/editRg")
    @iOSXCUITFindBy(id = "iosId")
    private MobileElement edtRG;

    @AndroidFindBy(id = "br.com.dudstecnologia.cadastrodeclientes:id/editCpf")
    @iOSXCUITFindBy(id = "iosId")
    private MobileElement edtCPF;

    @AndroidFindBy(id = "br.com.dudstecnologia.cadastrodeclientes:id/editData")
    @iOSXCUITFindBy(id = "iosId")
    private MobileElement edtDataNascimento;

    @AndroidFindBy(id = "br.com.dudstecnologia.cadastrodeclientes:id/editEndereco")
    @iOSXCUITFindBy(id = "iosId")
    private MobileElement edtEndereco;

    @AndroidFindBy(id = "br.com.dudstecnologia.cadastrodeclientes:id/editNumero")
    @iOSXCUITFindBy(id = "iosId")
    private MobileElement edtNumero;

    @AndroidFindBy(id = "br.com.dudstecnologia.cadastrodeclientes:id/editBairro")
    @iOSXCUITFindBy(id = "iosId")
    private MobileElement edtBairro;

    @AndroidFindBy(id = "br.com.dudstecnologia.cadastrodeclientes:id/editCep")
    @iOSXCUITFindBy(id = "iosId")
    private MobileElement edtCep;

    @AndroidFindBy(id = "br.com.dudstecnologia.cadastrodeclientes:id/editCidade")
    @iOSXCUITFindBy(id = "iosId")
    private MobileElement edtCidade;

    @AndroidFindBy(id = "br.com.dudstecnologia.cadastrodeclientes:id/editTelefone1")
    @iOSXCUITFindBy(id = "iosId")
    private MobileElement edtTelefone1;

    @AndroidFindBy(id = "br.com.dudstecnologia.cadastrodeclientes:id/editEmail")
    @iOSXCUITFindBy(id = "iosId")
    private MobileElement edtEmail;

    @AndroidFindBy(id = "br.com.dudstecnologia.cadastrodeclientes:id/btnSalvar")
    @iOSXCUITFindBy(id = "iosId")
    private MobileElement btnSalvar;


    public boolean validarNome() {
        String name = edtName.getText();
        return this.name.equals(name);
    }

    public DetalhesCliente clicarBotaoExcluir() {
        btnExcluir.click();
        System.out.println("Clicado botão de excluir");
        return this;
    }

    public Home clicarBotaoSim() {
        btnSim.click();
        System.out.println("Clicado botão Sim");
        return new Home(driver);
    }

    public DetalhesCliente preencherNome(String nome) {
        edtName.sendKeys(nome);
        System.out.println("Campo de nome preenchido com: " + nome);

        try {
            PropertiesConfiguration files = new PropertiesConfiguration("src/test/resources/properties/files.properties");
            PropertiesConfiguration clienteProps = new PropertiesConfiguration(files.getString("file.cliente"));
            clienteProps.setProperty("cliente.nome", nome);
            clienteProps.save();
        } catch (ConfigurationException e) {
            Assert.fail("Falha ao salvar o nome no arquivo de informações do cliente.");
        }

        return this;
    }

    public DetalhesCliente preencherRG(int numRG) {
        edtRG.sendKeys(numRG + "");
        System.out.println("Campo RG preenchido com: " + numRG);
        return this;
    }

    public DetalhesCliente preencherCPF(int CPF) {
        edtCPF.sendKeys(CPF + "");
        System.out.println("Campo CPF preenchido com: " + CPF);
        return this;
    }

    public DetalhesCliente preencherDataNascimento(String data) {
        edtDataNascimento.sendKeys(data);
        System.out.println("Campo de Data de nascimento preenchido com: " + data);
        return this;
    }

    public DetalhesCliente preencherEndereco(String endereco) {
        edtEndereco.sendKeys(endereco);
        System.out.println("Campo de endereço preenchido com: " + endereco);
        return this;
    }

    public DetalhesCliente preencherNumero(int numero) {
        edtNumero.sendKeys(numero + "");
        System.out.println("Campo de número preenchido com: " + numero);
        return this;
    }

    public DetalhesCliente preencherBairro(String bairro) {
        edtBairro.sendKeys(bairro);
        System.out.println("Campo de bairro preenchido com: " + bairro);
        return this;
    }

    public DetalhesCliente preencherCidade(String cidade){
        edtCidade.sendKeys(cidade);
        System.out.println("Campo cidade preenchido com: " + cidade);
        return this;
    }

    public DetalhesCliente preencherCEP(int CEP) {
        edtCep.sendKeys(CEP + "");
        System.out.println("Campo de CEP preenchido com: " + CEP);
        return this;
    }

    public DetalhesCliente preencherTelefone1(int telefone) {
        edtTelefone1.sendKeys(telefone + "");
        System.out.println("Campo de telefone 1 preenchido com: " + telefone);
        return this;
    }

    public DetalhesCliente preencherEmail(String email) {
        edtEmail.sendKeys(email);
        System.out.println("Campo de E-mail preenchido com: " + email);
        return this;
    }

    public DetalhesCliente clicarSalvar(){
        btnSalvar.click();
        System.out.println("Botão de Salvar clicado com êxito.");
        return this;
    }

    public String pegarMensagemCadastro(){
        return msgStatusCadastro.getText();
    }
}
