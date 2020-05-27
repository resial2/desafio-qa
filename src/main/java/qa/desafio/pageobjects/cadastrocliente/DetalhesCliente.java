package qa.desafio.pageobjects.cadastrocliente;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class DetalhesCliente {

    private AppiumDriver<MobileElement> driver;
    private String name;

    public DetalhesCliente(AppiumDriver<MobileElement> driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public DetalhesCliente(AppiumDriver<MobileElement> driver, String name){
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

    public boolean validarNome(){
        String name = edtName.getText();
        return this.name.equals(name);
    }

}
