package stepdef.api;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dada;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Assert;
import qa.desafio.Utils;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static pretest.PreTest.propertiesConfig;

public class BaseStepsAPI {

    private RequestSpecification rSpecification;
    private ValidatableResponse vResponse;
    private ExtractableResponse eResponse;
    private Response response;

    private PropertiesConfiguration props;
    private String idEmployee;

    @Dada("configuração da requisição com as informações abaixo")
    public void configuracaoDaRequisicaoComAsInformacoesAbaixo(DataTable dataTable) {
        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);

        String body = "{" +
                "\"name\": \"" + table.get(0).get("NOME") + "\"," +
                "\"salary\": \"" + table.get(0).get("SALARIO") + "\"," +
                "\"age\": \"" + table.get(0).get("IDADE") + "\"" +
                "}";

        rSpecification = given()
                .relaxedHTTPSValidation()
                .body(body);
    }

    @Quando("realizar o envio da requisição POST para URL {string}")
    public void realizarOEnvioDaRequisiçãoParaURL(String URL) {
        response = rSpecification.post(URL);
    }

    @Entao("deverá ser retornado o status code {int}")
    public void deveráSerRetornadoOStatusCode(int statusCode) {
        response.getBody().prettyPrint();

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
        props = Utils.getProperties(propertiesConfig.getString(file));
        props.setProperty("id.employee", eResponse.body().jsonPath().getString("data.id"));
        props.setProperty("name.employee", eResponse.body().jsonPath().getString("data.name"));
        props.setProperty("salary.employee", eResponse.body().jsonPath().getString("data.salary"));
        props.setProperty("age.employee", eResponse.body().jsonPath().getString("data.age"));
        props.save();
    }

    @Dada("configuração da requisição com a informação armazenada no arquivo {string}")
    public void configuraçãoDaRequisiçãoComAInformaçãoArmazenadaNoArquivo(String file) {
        props = Utils.getProperties(propertiesConfig.getString(file));
        idEmployee = props.getString("id.employee");
        rSpecification = given()
                .contentType("application/json;charset=utf-8");
    }

    @Quando("realizar o envio da requisição GET para URL {string}")
    public void realizarOEnvioDaRequisiçãoGETParaURL(String url) {
        String urlRequest = url;
        if (url.endsWith("/")) {
            urlRequest = urlRequest + idEmployee;
        } else {
            idEmployee = url.substring(url.lastIndexOf("/") + 1);
        }
        rSpecification.cookies(rSpecification.get(urlRequest).detailedCookies());
        response = rSpecification.get(urlRequest);

        System.out.println("ID do funcinário: " + idEmployee);
    }

    @Quando("realizar o envio da requisição DELETE para URL {string}")
    public void realizarOEnvioDaRequisiçãoDELETEParaURL(String url) {
        String urlRequest = url;
        if (url.endsWith("/")) {
            urlRequest = urlRequest + idEmployee;
        } else {
            idEmployee = url.substring(url.lastIndexOf("/") + 1);
        }
        String test = "ezoadgid_133674=-1; ezoref_133674=; ezoab_133674=mod54-c; PHPSESSID=9db78a621b64e886220bf8b6d3e99bdb; active_template::133674=pub_site.1590161357";
        rSpecification.cookie(test);
        response = rSpecification.delete(urlRequest);

        System.out.println("ID do funcinário: " + idEmployee);
    }
}
