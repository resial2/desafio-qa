package qa.desafio.pageobjects.cadastrocliente;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Home {

    private AppiumDriver<MobileElement> driver;

    public Home(AppiumDriver<MobileElement> driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageButton")
    @iOSXCUITFindBy(xpath = "iosXpath")
    private MobileElement bttMenu;

    @AndroidFindBy(id = "br.com.dudstecnologia.cadastrodeclientes:id/floating_novo")
    @iOSXCUITFindBy(id = "iosId")
    private MobileElement bttCadastrarNovo;

    @AndroidFindBy(id = "br.com.dudstecnologia.cadastrodeclientes:id/editPesquisar")
    @iOSXCUITFindBy(id = "iosId")
    private MobileElement edtPesquisar;

    @AndroidFindBys(@AndroidBy(id = "br.com.dudstecnologia.cadastrodeclientes:id/listViewClientes"))
    @iOSXCUITFindBys(@iOSXCUITBy(id = "iosId"))
    private List<MobileElement> lstCadastrados;


    private String name;
    public Home preencherCampoPesquisa(String nome){
        edtPesquisar.clear();
        edtPesquisar.sendKeys(nome);
        this.name = nome;
        System.out.println("O campo de pesquisa foi preenchido com: " + nome);
        return this;
    }

    public DetalhesCliente selecionarPrimeiroResultadoPesquisa(){
        lstCadastrados.get(0).click();
        return new DetalhesCliente(driver, name);
    }


}
