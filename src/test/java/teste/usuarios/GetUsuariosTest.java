package teste.usuarios;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class GetUsuariosTest {

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
    public void DadoUmGetComUsuarioExistenteQuandoBuscoGetPorIdValidoEntaoObtenhoStatusCode200() {

        test = extent.createTest("Deve retornar 200 quando usuario for buscado pelo id valido");

        given()
                .basePath("/usuarios") // Define o caminho base
                .pathParam("_id", "0uxuPY0cbmQhpEz1") // Define o parâmetro de caminho
                .log().all() // apresenta o log completo
                .when() // Quando: Ação a ser testada
                .get("/{_id}") // Faz a requisição GET
                .then() // Então: Validações
                .log().ifValidationFails() // apresenta log se a chamada falhar
                .log().status() // apresenta log do status code
                .statusCode(200); // Valida o status code

        test.pass("Busca realizada pelo parametro id com sucesso");
    }

    @Test
    public void DadoUmGetComUsuarioInexistenteQuandoBuscoGetPorIdInValidoEntaoObtenhoStatusCode400() {

        test = extent.createTest("Deve retornar 400 quando usuario for buscado pelo id Inválido");

        given()
                .basePath("/usuarios") // Define o caminho base
                .pathParam("_id", "0uxuPY0cbmQhpEz11") // Define o parâmetro de caminho (ID do post)
                .log().all()
                .when() // Quando: Ação a ser testada
                .get("/{_id}") // Faz a requisição GET
                .then() // Então: Validações
                .log().ifValidationFails()
                .log().status()
                .statusCode(400)// Valida o status code
                .body("message", equalTo("Usuário não encontrado"));

        test.pass("Busca nao realizada conforme esperado quando id é invalido");
    }

    @Test
    public void DadoUmGetComUsuarioExistenteQuandoBuscoGetPorNomeValidoEntaoObtenhoStatusCode200() {
        test = extent.createTest("Deve retornar 200 quando usuario for buscado pelo parametro nome");

        given()
                .basePath("/usuarios") // Define o caminho base
                .pathParam("_id", "0uxuPY0cbmQhpEz1")
                .queryParam("nome", "Fulano da Silva")
                .log().all()
                .when() // Quando: Ação a ser testada
                .get("/{_id}") // Faz a requisição GET
                .then() // Então: Validações
                .log().ifValidationFails()
                .log().status()
                .statusCode(200)// Valida o status code
                .body("nome", equalTo("Fulano da silva"));

            test.pass("Busca realizada pelo parametro nome com sucesso");
    }

    @Test
    public void DadoUmGetComUsuarioExistenteQuandoBuscoGetPorEmailValidoEntaoObtenhoStatusCode200() {
        test = extent.createTest("Deve retornar 200 quando usuario for buscado pelo parametro email");

        given()
                .basePath("/usuarios") // Define o caminho base
                .pathParam("_id", "0uxuPY0cbmQhpEz1")
                .queryParam("email", "fulano@qa.com")
                .log().all()
                .when() // Quando: Ação a ser testada
                .get("/{_id}") // Faz a requisição GET
                .then() // Então: Validações
                .log().ifValidationFails()
                .log().status()
                .statusCode(200)// Valida o status code
                .body("nome", equalTo("Fulano da silva"));

        test.pass("Busca realizada pelo parametro email com sucesso");
    }

    @Test
    public void DadoUmGetComUsuarioExistenteQuandoBuscoGetPorPasswordEntaoObtenhoStatusCode200() {
        test = extent.createTest("Deve retornar 200 quando usuario for buscado pelo parametro password");

        given()
                .basePath("/usuarios") // Define o caminho base
                .pathParam("_id", "0uxuPY0cbmQhpEz1")
                .queryParam("password", "teste")
                .log().all()
                .when() // Quando: Ação a ser testada
                .get("/{_id}") // Faz a requisição GET
                .then() // Então: Validações
                .log().ifValidationFails()
                .log().status()
                .statusCode(200)// Valida o status code
                .body("nome", equalTo("Fulano da silva"));

        test.pass("Busca realizada pelo parametro password com sucesso");
    }


    @AfterAll
    public static void tearDown() {
        // Finaliza o relatório
        extent.flush();

    }
}
