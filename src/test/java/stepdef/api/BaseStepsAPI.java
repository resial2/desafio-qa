package stepdef.api;

import com.aventstack.extentreports.service.ExtentService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dada;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Assert;
import qa.desafio.Utils;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseStepsAPI {

    private RequestSpecification rSpecification;
    private ValidatableResponse vResponse;
    private ExtractableResponse eResponse;
    private Response response;

    protected static PropertiesConfiguration propertiesConfig;
    private static final String DEFAULT_PROPERTIES_FILES_PATH = "src/test/resources/properties/files.properties";

    @Before
    public void init() throws UnsupportedEncodingException {
        ExtentService.getInstance().setGherkinDialect("pt");
        propertiesConfig = Utils.getProperties(DEFAULT_PROPERTIES_FILES_PATH);
    }

    @Dada("configuração da requisição com as informações abaixo")
    public void configuracaoDaRequisicaoComAsInformacoesAbaixo(DataTable dataTable) {
        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);

        String body = "{" +
                "\"name\": \"" + table.get(0).get("NOME") + "\"," +
                "\"salary\": \"" + table.get(0).get("SALARIO") + "\"" +
                "\"age\": \"" + table.get(0).get("IDADE") + "\"" +
                "}";

        rSpecification = given()
                .relaxedHTTPSValidation()
                .body(body);
    }

    @Quando("realizar o envio da requisição para URL {string}")
    public void realizarOEnvioDaRequisiçãoParaURL(String URL) {
        response = rSpecification.post(URL);
    }

    @Entao("deverá ser retornado o status code {int}")
    public void deveráSerRetornadoOStatusCode(int statusCode) {
        vResponse = response.then()
                .statusCode(statusCode);
        System.out.println("Status code confere. [Status Code: " + vResponse.extract().statusCode() + "]");
    }

    @E("será validada a mensagem {string}")
    public void seráValidadaAMensagem(String expectedMessage) {
        eResponse = vResponse.extract();
        String response = eResponse.body().jsonPath().getString("status");
        Assert.assertEquals("As mensagens estão divergentes. [Esperada: " + expectedMessage + "] [Resposta: " + response + "]", expectedMessage, response);
        System.out.println("As mensagens coincidem. [Esperada: " + expectedMessage + "] [Resposta: " + response + "]");
    }

    @E("será salva a informação de ID para uso futuro no arquivo {string}")
    public void seráSalvaAInformaçãoDeIDParaUsoFuturo(String file) throws ConfigurationException {
        PropertiesConfiguration props = Utils.getProperties(propertiesConfig.getString(file));
        props.setProperty("id.employee", eResponse.body().jsonPath().getString("data.id"));
        props.save();
    }
}
