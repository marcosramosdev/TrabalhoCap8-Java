package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;

public class SmartCitiesSteps {

    private Response response;

    @Given("que o sistema está funcionando")
    public void que_o_sistema_esta_funcionando() {
        baseURI = "http://seu-servidor:porta"; // Altere para o endereço correto
    }

    @When("eu envio uma requisição para criar uma cidade com os dados válidos")
    public void eu_envio_uma_requisicao_para_criar_uma_cidade_com_os_dados_validos() {
        response = given()
            .contentType("application/json")
            .body("{ \"nome\": \"Cidade Teste\", \"populacao\": 100000 }")
            .when()
            .post("/cidades");
    }

    @Then("o sistema deve retornar o status code {int}")
    public void o_sistema_deve_retornar_o_status_code(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("a cidade deve ser criada com os dados corretos")
    public void a_cidade_deve_ser_criada_com_os_dados_corretos() {
        response.then().body("nome", equalTo("Cidade Teste"))
                .body("populacao", equalTo(100000));
    }

    @When("eu envio uma requisição para criar uma cidade com dados inválidos")
    public void eu_envio_uma_requisicao_para_criar_uma_cidade_com_dados_invalidos() {
        response = given()
            .contentType("application/json")
            .body("{ \"nome\": \"\", \"populacao\": -1 }")
            .when()
            .post("/cidades");
    }

    @Then("deve exibir uma mensagem de erro apropriada")
    public void deve_exibir_uma_mensagem_de_erro_apropriada() {
        response.then().body("erro", notNullValue());
    }

    @When("eu envio uma requisição para listar todas as cidades")
    public void eu_envio_uma_requisicao_para_listar_todas_as_cidades() {
        response = when().get("/cidades");
    }

    @Then("deve exibir a lista de cidades cadastradas")
    public void deve_exibir_a_lista_de_cidades_cadastradas() {
        response.then().body("size()", greaterThan(0));
    }
}
