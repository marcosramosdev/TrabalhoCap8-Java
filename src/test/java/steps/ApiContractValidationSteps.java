package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.*;

public class ApiContractValidationSteps {
    private Response response;

    @Given("que eu tenho acesso à API de cidades")
    public void que_eu_tenho_acesso_a_api_de_cidades() {
        baseURI = "http://localhost:8080";
    }

    @When("eu faço uma requisição GET para obter uma cidade por ID {string}")
    public void eu_faco_uma_requisicao_get_para_obter_uma_cidade_por_id(String id) {
        response = given()
                .pathParam("id", id)
                .when()
                .get("/cidades/{id}");
    }

    // ...existing code...

    @Then("o contrato da resposta deve estar de acordo com o schema {string}")
    public void o_contrato_da_resposta_deve_estar_de_acordo_com_o_schema(String schemaPath) {
        response.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(
                        new File("src/test/resources/schemas/" + schemaPath)));
    }
}
