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

    public static final String BASE_URL = "https://serverest.dev";

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
                .basePath("/usuarios") // Define o caminho base para a requisição (endpoint de usuários)
                .pathParam("_id", "Y8SzvNaCAGuzFyHS") // Define o parâmetro de caminho "_id" com o valor especificado, que será usado na URL da requisição
                .header("Content-Type", "application/json") // Adiciona um cabeçalho "Content-Type" indicando que o corpo da requisição está no formato JSON
                .log().all() // Loga todos os detalhes da requisição antes de enviá-la, ajudando a rastrear a comunicação
                .when() // Inicia a execução da requisição
                .delete("/{_id}") // Realiza uma requisição HTTP do tipo DELETE para o endpoint "/usuarios/{_id}", removendo o usuário com o id especificado
                .then() // Inicia as validações da resposta da requisição
                .log().all() // Loga os detalhes da resposta caso a validação falhe
                .statusCode(200) // Valida que o código de status da resposta é 200 (OK), indicando que a requisição foi bem-sucedida
                .body("message", equalTo("Nenhum registro excluído")); // Valida se a mensagem do corpo da resposta é "Nenhum registro excluído", confirmando que o usuário não foi excluído


        test.pass("Exclusao nao realizada pelo parametro id inexistente com sucesso");
    }

    @Test
    public void DadoUmDeleteComUsuarioExistenteComCarrinhoQuandoCrioDeletePorIdInvalidoEntaoObtenhoStatusCode400() {

        test = extent.createTest("Deve retornar 400 quando existir tentativa de excluir usuario existente mas que possua carrinho");

        given()
                .basePath("/usuarios") // Define o caminho base para a requisição (endpoint de usuários)
                .pathParam("_id", "0uxuPY0cbmQhpEz1") // Define o parâmetro de caminho "_id" com o valor especificado, que será usado na URL da requisição
                .header("Content-Type", "application/json") // Adiciona um cabeçalho "Content-Type" indicando que o corpo da requisição está no formato JSON
                .log().all() // Loga todos os detalhes da requisição antes de enviá-la, ajudando a rastrear a comunicação
                .when() // Inicia a execução da requisição
                .delete("/{_id}") // Realiza uma requisição HTTP do tipo DELETE para o endpoint "/usuarios/{_id}", tentando excluir o usuário com o id especificado
                .then() // Inicia as validações da resposta da requisição
                .log().all() // Loga os detalhes da resposta caso a validação falhe
                .statusCode(400) // Valida que o código de status da resposta é 400 (Bad Request), indicando que houve um erro ao tentar excluir o usuário
                .body("message", equalTo("Não é permitido excluir usuário com carrinho cadastrado")) // Valida se a mensagem da resposta é a esperada, indicando que não é possível excluir o usuário por ter um carrinho cadastrado
                .body("idCarrinho", notNullValue()); // Valida que o campo "idCarrinho" na resposta não é nulo, confirmando que o usuário tem um carrinho associado


        test.pass("Exclusao nao realizada com sucesso pro conta do possuir carrinho");
    }

    @AfterAll
    public static void tearDown() {
        // Finaliza o relatório
        extent.flush();

    }
}
