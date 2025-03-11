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
    public void DadoUmGetComUsuarioExistenteQuandoBuscoGetPorIdValidoEntaoObtenhoStatusCode200() {

        test = extent.createTest("Deve retornar 200 quando usuario for buscado pelo id valido");

        given()
                .basePath("/usuarios") // Define o caminho base para a requisição (endpoint de usuários)
                .pathParam("_id", "0uxuPY0cbmQhpEz1") // Define o parâmetro de caminho "_id" com o valor especificado, que será usado na URL da requisição
                .log().all() // Loga todos os detalhes da requisição antes de enviá-la, ajudando a rastrear a comunicação
                .when() // Inicia a execução da requisição
                .get("/{_id}") // Realiza uma requisição HTTP do tipo GET para o endpoint "/usuarios/{_id}", buscando o usuário com o id especificado
                .then() // Inicia as validações da resposta da requisição
                .log().ifValidationFails() // Caso a validação falhe, exibe os detalhes da resposta no log
                .log().status() // Loga o código de status da resposta para facilitar o diagnóstico em caso de falha
                .statusCode(200); // Valida que o código de status da resposta é 200 (OK), indicando que a requisição foi bem-sucedida

        test.pass("Busca realizada pelo parametro id com sucesso");
    }

    @Test
    public void DadoUmGetComUsuarioInexistenteQuandoBuscoGetPorIdInValidoEntaoObtenhoStatusCode400() {

        test = extent.createTest("Deve retornar 400 quando usuario for buscado pelo id Inválido");

        given()
                .basePath("/usuarios") // Define o caminho base para a requisição (endpoint de usuários)
                .pathParam("_id", "0uxuPY0cbmQhpEz11") // Define o parâmetro de caminho "_id" com o valor especificado (ID do usuário a ser buscado)
                .log().all() // Loga todos os detalhes da requisição antes de enviá-la, facilitando o rastreamento e diagnóstico
                .when() // Inicia a execução da requisição
                .get("/{_id}") // Realiza uma requisição HTTP do tipo GET para o endpoint "/usuarios/{_id}", buscando o usuário com o ID especificado
                .then() // Inicia as validações da resposta da requisição
                .log().ifValidationFails() // Caso a validação falhe, exibe os detalhes da resposta no log
                .log().status() // Loga o código de status da resposta para análise em caso de falha
                .statusCode(400) // Valida que o código de status da resposta é 400 (Bad Request), indicando que a requisição foi mal-sucedida
                .body("message", equalTo("Usuário não encontrado")); // Valida que a mensagem retornada no corpo da resposta é "Usuário não encontrado", indicando que o usuário não existe


        test.pass("Busca nao realizada conforme esperado quando id é invalido");
    }

    @Test
    public void DadoUmGetComUsuarioExistenteQuandoBuscoGetPorNomeValidoEntaoObtenhoStatusCode200() {
        test = extent.createTest("Deve retornar 200 quando usuario for buscado pelo parametro nome");

        given()
                .basePath("/usuarios") // Define o caminho base para a requisição (endpoint de usuários)
                .pathParam("_id", "0uxuPY0cbmQhpEz1") // Define o parâmetro de caminho "_id" com o valor especificado (ID do usuário a ser buscado)
                .queryParam("nome", "Fulano da Silva") // Adiciona o parâmetro de consulta "nome" com o valor "Fulano da Silva" à URL da requisição
                .log().all() // Loga todos os detalhes da requisição antes de enviá-la, facilitando o rastreamento e diagnóstico
                .when() // Inicia a execução da requisição
                .get("/{_id}") // Realiza uma requisição HTTP do tipo GET para o endpoint "/usuarios/{_id}", buscando o usuário com o ID especificado
                .then() // Inicia as validações da resposta da requisição
                .log().ifValidationFails() // Caso a validação falhe, exibe os detalhes da resposta no log
                .log().status() // Loga o código de status da resposta para análise em caso de falha
                .statusCode(200) // Valida que o código de status da resposta é 200 (OK), indicando sucesso na requisição
                .body("nome", equalTo("Fulano da silva")); // Valida que o campo "nome" no corpo da resposta é igual a "Fulano da Silva", garantindo que o nome retornado está correto


        test.pass("Busca realizada pelo parametro nome com sucesso");
    }

    @Test
    public void DadoUmGetComUsuarioExistenteQuandoBuscoGetPorEmailValidoEntaoObtenhoStatusCode200() {
        test = extent.createTest("Deve retornar 200 quando usuario for buscado pelo parametro email");

        given()
                .basePath("/usuarios") // Define o caminho base para a requisição (endpoint de usuários)
                .pathParam("_id", "0uxuPY0cbmQhpEz1") // Define o parâmetro de caminho "_id" com o valor especificado (ID do usuário a ser buscado)
                .queryParam("email", "fulano@qa.com") // Adiciona o parâmetro de consulta "email" com o valor "fulano@qa.com" à URL da requisição
                .log().all() // Loga todos os detalhes da requisição antes de enviá-la, facilitando o rastreamento e diagnóstico
                .when() // Inicia a execução da requisição
                .get("/{_id}") // Realiza uma requisição HTTP do tipo GET para o endpoint "/usuarios/{_id}", buscando o usuário com o ID especificado
                .then() // Inicia as validações da resposta da requisição
                .log().ifValidationFails() // Caso a validação falhe, exibe os detalhes da resposta no log
                .log().status() // Loga o código de status da resposta para análise em caso de falha
                .statusCode(200) // Valida que o código de status da resposta é 200 (OK), indicando sucesso na requisição
                .body("nome", equalTo("Fulano da silva")); // Valida que o campo "nome" no corpo da resposta é igual a "Fulano da Silva", garantindo que o nome retornado está correto


        test.pass("Busca realizada pelo parametro email com sucesso");
    }

    @Test
    public void DadoUmGetComUsuarioExistenteQuandoBuscoGetPorPasswordEntaoObtenhoStatusCode200() {
        test = extent.createTest("Deve retornar 200 quando usuario for buscado pelo parametro password");

        given()
                .basePath("/usuarios") // Define o caminho base para a requisição (endpoint de usuários)
                .pathParam("_id", "0uxuPY0cbmQhpEz1") // Define o parâmetro de caminho "_id" com o valor especificado (ID do usuário a ser buscado)
                .queryParam("password", "teste") // Adiciona o parâmetro de consulta "password" com o valor "teste" à URL da requisição
                .log().all() // Loga todos os detalhes da requisição antes de enviá-la, facilitando o rastreamento e diagnóstico
                .when() // Inicia a execução da requisição
                .get("/{_id}") // Realiza uma requisição HTTP do tipo GET para o endpoint "/usuarios/{_id}", buscando o usuário com o ID especificado
                .then() // Inicia as validações da resposta da requisição
                .log().ifValidationFails() // Caso a validação falhe, exibe os detalhes da resposta no log
                .log().status() // Loga o código de status da resposta para análise em caso de falha
                .statusCode(200) // Valida que o código de status da resposta é 200 (OK), indicando sucesso na requisição
                .body("nome", equalTo("Fulano da silva")); // Valida que o campo "nome" no corpo da resposta é igual a "Fulano da Silva", garantindo que o nome retornado está correto


        test.pass("Busca realizada pelo parametro password com sucesso");
    }


    @AfterAll
    public static void tearDown() {
        // Finaliza o relatório
        extent.flush();

    }
}
