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

public class DeleteUsuariosTest {

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
    public void DadoUmDeleteComUsuarioInexistenteQuandoCrioDeletePorIdInvalidoEntaoObtenhoStatusCode200() {

        test = extent.createTest("Deve retornar 200 quando existir tentativa de excluir usuario inexiste");

        given()
                .basePath("/usuarios") // Define o caminho base
                .pathParam("_id", "Y8SzvNaCAGuzFyHS") // Define o parâmetro de caminho
                .header("Content-Type", "application/json") // Adiciona um cabeçalho para indicar que o corpo da requisição está em formato JSON
                .log().all() // apresenta o log completo
                .when() // Quando: Ação a ser testada
                .delete("/{_id}") // Faz a requisição Delete
                .then() // Então: Validações
                .log().all() // apresenta log se a chamada falhar
                .statusCode(200) // Valida o status code
                .body("message", equalTo("Nenhum registro excluído"));

        test.pass("Exclusao nao realizada pelo parametro id inexistente com sucesso");
    }

    @Test
    public void DadoUmDeleteComUsuarioExistenteComCarrinhoQuandoCrioDeletePorIdInvalidoEntaoObtenhoStatusCode400() {

        test = extent.createTest("Deve retornar 400 quando existir tentativa de excluir usuario existente mas que possua carrinho");

        given()
                .basePath("/usuarios") // Define o caminho base
                .pathParam("_id", "0uxuPY0cbmQhpEz1") // Define o parâmetro de caminho
                .header("Content-Type", "application/json") // Adiciona um cabeçalho para indicar que o corpo da requisição está em formato JSON
                .log().all() // apresenta o log completo
                .when() // Quando: Ação a ser testada
                .delete("/{_id}") // Faz a requisição Delete
                .then() // Então: Validações
                .log().all() // apresenta log se a chamada falhar
                .statusCode(400) // Valida o status code
                .body("message", equalTo("Não é permitido excluir usuário com carrinho cadastrado"))
                .body("idCarrinho", notNullValue());

        test.pass("Exclusao nao realizada com sucesso pro conta do possuir carrinho");
    }

    @AfterAll
    public static void tearDown() {
        // Finaliza o relatório
        extent.flush();

    }
}
