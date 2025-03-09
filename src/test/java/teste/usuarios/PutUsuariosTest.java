package teste.usuarios;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.UUID;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PutUsuariosTest {
    static ExtentReports extent;
    ExtentTest test;

    public static final String BASE_URL = "http://localhost:3000";

    @BeforeAll
    public static void report() {
        // Configura o ExtentReports
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/extent-report.html");
         extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void DadoUmPutComUsuarioExistenteQuandoCrioPutPorIdValidoEntaoObtenhoStatusCode200() {

        test = extent.createTest("Deve retornar 200 quando usuario for alterado pelo id valido");

        given()
                .basePath("/usuarios") // Define o caminho base
                .pathParam("_id", "0uxuPY0cbmQhpEz1") // Define o parâmetro de caminho
                .header("Content-Type", "application/json") // Adiciona um cabeçalho para indicar que o corpo da requisição está em formato JSON
                .body("{\"nome\": \"Fulano da silva\" , \"email\": \"fulano@qa.com\", \"password\": \"teste2\", \"administrador\": \"true\"}") // Define o corpo da requisição com os dados válidos a serem enviados no POST
                .log().all() // apresenta o log completo
                .when() // Quando: Ação a ser testada
                .put("/{_id}") // Faz a requisição GET
                .then() // Então: Validações
                .log().all() // apresenta log se a chamada falhar
                .log().status() // apresenta log do status code
                .statusCode(200) // Valida o status code
                .body("message", equalTo("Registro alterado com sucesso"));

        test.pass("Alteracao realizada pelo parametro id com sucesso");
    }

    @Test
    public void DadoUmPutComEmailExistenteQuandoCrioPutPorIdDiferenteDoExistenteEntaoObtenhoStatusCode400() {

        test = extent.createTest("Deve retornar 400 quando usuario tiver o mesmo nome e email mas id diferente do existente");

        given()
                .basePath("/usuarios") // Define o caminho base
                .pathParam("_id", "0uxuPY0cbmQhpEz11") // Define o parâmetro de caminho
                .header("Content-Type", "application/json") // Adiciona um cabeçalho para indicar que o corpo da requisição está em formato JSON
                .body("{\"nome\": \"Fulano da silva\" , \"email\": \"ciclano@qa.com\", \"password\": \"teste2\", \"administrador\": \"true\"}") // Define o corpo da requisição com os dados válidos a serem enviados no POST
                .log().all() // apresenta o log completo
                .when() // Quando: Ação a ser testada
                .put("/{_id}") // Faz a requisição GET
                .then() // Então: Validações
                .log().all() // apresenta log se a chamada falhar
                .log().status() // apresenta log do status code
                .statusCode(400) // Valida o status code
                .body("message", equalTo("Este email já está sendo usado"));

        test.pass("Alteracao nao realizada pelo valor do parametro email ja ser existente");
    }

    @Test
    public void DadoUmPutComEmailInexistenteQuandoCrioPutPorIdInexistenteEntaoObtenhoStatusCode201() {

        test = extent.createTest("Deve retornar 201 quando usuario tiver email e id diferente do existente - criacao de usuario");

        String idAleatorio = UUID.randomUUID().toString(); // Gera um ID aleatório
        String emailAleatorio = "user" + UUID.randomUUID().toString().substring(0, 5) + "@qa.com"; // Gera um email único

        given()
                .basePath("/usuarios") // Define o caminho base
                .pathParam("_id", idAleatorio) // Define o parâmetro de caminho com ID aleatório
                .header("Content-Type", "application/json") // Define o cabeçalho da requisição
                .body("{\"nome\": \"Fulano da Silva\" , \"email\": \"" + emailAleatorio + "\", \"password\": \"teste2\", \"administrador\": \"true\"}") // Define o corpo com email aleatório
                .log().all() // Exibe o log completo
                .when() // Quando: Ação a ser testada
                .put("/{_id}") // Faz a requisição PUT
                .then() // Então: Validações
                .log().all() // Exibe log apenas se a chamada falhar
                .statusCode(201) // Valida o status code
                .body("message", equalTo("Cadastro realizado com sucesso"))
                .body("_id", notNullValue());

        test.pass("Criacao de usuario realizada com sucesso");
    }

    @AfterAll
    public static void tearDown() {
        // Finaliza o relatório
        extent.flush();

    }

}
